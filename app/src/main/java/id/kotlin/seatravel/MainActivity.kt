package id.kotlin.seatravel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import id.kotlin.seatravel.R
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Handler().postDelayed({
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        } , 3000)

        val sharedPreference =  getSharedPreferences(
            "app_preference", Context.MODE_PRIVATE
        )

        var email = sharedPreference.getString("email", "").toString()
        if (email.isNotEmpty()) {
//            txtMessageLoading.text = "Hey, how are you? $email \uD83D\uDE09"
        }


        Timer().schedule(3000) {
            if (email.isNotEmpty()) {
                goToHomePage()
            } else {
                goToSignInPage()
            }
        }

    }
    private fun goToSignInPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToHomePage() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}

