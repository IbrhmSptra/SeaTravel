package id.kotlin.seatravel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.kotlin.seatravel.Api.RetrofitHelper
import id.kotlin.seatravel.Api.UserApi
import id.kotlin.seatravel.Api.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject



class LoginActivity : AppCompatActivity() {

    lateinit var btnSignIn: Button
    lateinit var btnMoveToSignUp: Button
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText




    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqamlscHlza2F3ZHRrdWRncmRjIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzExMzQ1NTAsImV4cCI6MTk4NjcxMDU1MH0.3o6R1Z5XEEC9wMgBWKMPsUaOfhrDUMbLxITwyI2H_mo"
    val token = "Bearer $apiKey"

    val todoApi = RetrofitHelper.getInstance().create(UserApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnSignIn = findViewById(R.id.btn_login)
        btnMoveToSignUp = findViewById(R.id.btn_sign_up)
        etEmail = findViewById(R.id.Email)
        etPassword = findViewById(R.id.Password)




        btnSignIn.setOnClickListener {
            signIn(etEmail.text.toString(), etPassword.text.toString())

        }

        btnMoveToSignUp.setOnClickListener {
            val intent = Intent(this, Daftar::class.java)
            startActivity(intent)
        }
    }

    private fun signIn(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val data = Users(email = email, password = password)
            val response = todoApi.signIn(token = token, apiKey = apiKey, data = data)

            val bodyResponse = if (response.code() == 200) {
                response.body()?.string()
            } else {
                response.errorBody()?.string().toString()
            }

            var failed = false
            val jsonResponse = JSONObject(bodyResponse)
            if (jsonResponse.keys().asSequence().toList().contains("error")) {
                failed = true
            }

            var msg = ""
            if (!failed) {
                var email = jsonResponse.getJSONObject("user").get("email").toString()
                msg = "Berhasil masuk! Selamat datang kembali: $email"


                val sharedPreference = getSharedPreferences(
                    "app_preference", Context.MODE_PRIVATE
                )

                var editor = sharedPreference.edit()
                editor.putString("email", email)
                editor.commit()

            } else {
                msg += jsonResponse.get("error_description").toString()
            }

            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    applicationContext,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()

                if (!failed) {
                    goToHome();
                }
            }
        }
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}