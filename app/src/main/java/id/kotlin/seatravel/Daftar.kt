package id.kotlin.seatravel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import id.kotlin.seatravel.Api.RetrofitHelper
import id.kotlin.seatravel.Api.UserApi
import id.kotlin.seatravel.Api.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class Daftar : AppCompatActivity() {
    lateinit var btnSignUp : Button
    lateinit var etEmail : EditText
    lateinit var etPassword : EditText
    lateinit var etnama : EditText
    lateinit var etgender : EditText



    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqamlscHlza2F3ZHRrdWRncmRjIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzExMzQ1NTAsImV4cCI6MTk4NjcxMDU1MH0.3o6R1Z5XEEC9wMgBWKMPsUaOfhrDUMbLxITwyI2H_mo"
    val token = "Bearer $apiKey"

    val todoApi = RetrofitHelper.getInstance().create(UserApi::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)


        btnSignUp = findViewById(R.id.btn_sign_up)
        etEmail = findViewById(R.id.et_regis_email)
        etPassword = findViewById(R.id.et_regis_password)
        etnama = findViewById(R.id.etnama)
        etgender = findViewById(R.id.etgender)


        btnSignUp.setOnClickListener {
            signUp(etEmail.text.toString(), etPassword.text.toString())
        }

    }


    private fun signUp(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {

            var data = Users(email = email, password = password)
            var response = todoApi.signUp(token = token, apiKey = apiKey, data = data)

            val bodyResponse = if(response.code() == 200) {
                response.body()?.string()
            } else {
                response.errorBody()?.string().toString()
            }

            var failed = false
            val jsonResponse = JSONObject(bodyResponse)
            if(jsonResponse.keys().asSequence().toList().contains("error")) {
                failed = true
            }

            var msg = ""
            if (!failed) {
                msg = "Pendaftaran Berhasil!"


                //panggil shared pref
                val sharedPreference = getSharedPreferences(
                    "app_preference", Context.MODE_PRIVATE
                )
                //masukan nama dan gender ke shared pref
                var editor = sharedPreference.edit()
                editor.putString("nama", etnama.text.toString())
                editor.putString("gender" , etgender.text.toString())
                editor.commit()

            } else {
                var errorMessage = jsonResponse.get("error_description")
                msg += errorMessage
            }

            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    applicationContext,
                    msg,
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}