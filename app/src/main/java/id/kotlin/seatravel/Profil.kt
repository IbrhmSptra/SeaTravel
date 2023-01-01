package id.kotlin.seatravel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class Profil : AppCompatActivity() {

    lateinit var tvEmail : TextView
    lateinit var tvgender : TextView
    lateinit var tvname : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        tvEmail = findViewById(R.id.lblEmail)
        tvgender = findViewById(R.id.lbJenisKelamin)
        tvname = findViewById(R.id.lblName)


        val sharedPreference =  getSharedPreferences(
            "app_preference", Context.MODE_PRIVATE
        )

        var email = sharedPreference.getString("email", "[No email found]").toString()
        var nama = sharedPreference.getString("nama", "").toString()
        var gender = sharedPreference.getString("gender" , "").toString()
        tvEmail.text = email
        tvname.text = nama
        tvgender.text = gender

    }

}