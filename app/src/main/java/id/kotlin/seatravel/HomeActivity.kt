package id.kotlin.seatravel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity() {

    lateinit var profile : CardView
    lateinit var riwayat : CardView
    lateinit var boooking : CardView
    lateinit var kamar : CardView
    lateinit var btnOut:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedPreference =  getSharedPreferences(
            "app_preference", Context.MODE_PRIVATE
        )

        profile=findViewById(R.id.profile)
        profile.setOnClickListener {
            startActivity(Intent(this, Profil::class.java))
        }
        riwayat=findViewById(R.id.historyMenu)
        riwayat.setOnClickListener {
            startActivity(Intent(this, Riwayat::class.java))
        }
        boooking=findViewById(R.id.Booking)
        boooking.setOnClickListener {
            startActivity(Intent(this, BookKapal::class.java))
        }
        kamar=findViewById(R.id.Kamar)
        kamar.setOnClickListener {
            startActivity(Intent(this, BookKamar::class.java))
        }
        btnOut = findViewById(R.id.out)
        btnOut.setOnClickListener {
//            startActivity(Intent(this, LoginActivity::class.java))
            var editor = sharedPreference.edit()
            editor.clear()
            editor.remove("email")
            editor.commit()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish()
        }

    }

}