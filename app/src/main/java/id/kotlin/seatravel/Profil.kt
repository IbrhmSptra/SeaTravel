package id.kotlin.seatravel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class Profil : AppCompatActivity() {

    lateinit var etRegisEmail : TextView
    lateinit var etNama : EditText
    lateinit var etJk : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        etRegisEmail = findViewById(R.id.lblName)


        val sharedPreference =  getSharedPreferences(
            "app_preference", Context.MODE_PRIVATE
        )

        var name = sharedPreference.getString("email", "[No email found]").toString()
        etRegisEmail.text = name

    }

}