package id.kotlin.seatravel.BatalkanPesanan

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import id.kotlin.seatravel.Api.RetrofitHelper
import id.kotlin.seatravel.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CancelTiketKamar : AppCompatActivity() {

    //komponen
    lateinit var tvnama : TextView
    lateinit var tvkamar : TextView
    lateinit var tvjmlkamar : TextView
    lateinit var tvcheckin : TextView
    lateinit var tvcheckout : TextView
    lateinit var btnbatal :  Button

    //API
    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqamlscHlza2F3ZHRrdWRncmRjIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzExMzQ1NTAsImV4cCI6MTk4NjcxMDU1MH0.3o6R1Z5XEEC9wMgBWKMPsUaOfhrDUMbLxITwyI2H_mo"
    val token = "Bearer $apiKey"
    val api = RetrofitHelper.getInstance().create(API_Batalkan::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel_tiket_kamar)

        //get value yang sudah di intent
        val id = intent.getStringExtra("id")
        val nama = intent.getStringExtra("nama")
        val kamar = intent.getStringExtra("kamar")
        val jmlkamar = intent.getStringExtra("jmlkamar")
        val checkin = intent.getStringExtra("checkin")
        val checkout = intent.getStringExtra("checkout")

        //init
        tvnama = findViewById(R.id.tvnama)
        tvkamar = findViewById(R.id.tvkamar)
        tvjmlkamar = findViewById(R.id.tvjmlkamar)
        tvcheckin = findViewById(R.id.tvcheckin)
        tvcheckout = findViewById(R.id.tvcheckout)
        btnbatal = findViewById(R.id.batalkan)

        //set semua textview agar sesuai sama yang di intent
        tvnama.text = nama
        tvkamar.text = kamar
        tvjmlkamar.text = jmlkamar
        tvcheckin.text = checkin
        tvcheckout.text = checkout

        btnbatal.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Batalkan Tiket")
            alert.setMessage("Apakah anda yakin ingin Membatalkan Tiket Ini?")
            alert.setPositiveButton("Iya" , { dialog : DialogInterface?, which : Int ->
                val query = "eq.$id"
                CoroutineScope(Dispatchers.Main).launch {
                    api.deletetiketkamar(apiKey = apiKey , token = token , idquery = query)
                    Toast.makeText(applicationContext, "Berhasil Membatalkan Tiket", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
            alert.setNegativeButton("Tidak" , { dialog : DialogInterface?, which : Int ->})
            alert.show()
        }

    }
}