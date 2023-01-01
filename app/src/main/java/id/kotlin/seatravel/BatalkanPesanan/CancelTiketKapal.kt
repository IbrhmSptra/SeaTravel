package id.kotlin.seatravel.BatalkanPesanan

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
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

class CancelTiketKapal : AppCompatActivity() {

    //komponen
    lateinit var tvasal : TextView
    lateinit var tvtujuan : TextView
    lateinit var tvtgl : TextView
    lateinit var tvdewasa : TextView
    lateinit var tvanak : TextView
    lateinit var btnbatal : Button

    //API
    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqamlscHlza2F3ZHRrdWRncmRjIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzExMzQ1NTAsImV4cCI6MTk4NjcxMDU1MH0.3o6R1Z5XEEC9wMgBWKMPsUaOfhrDUMbLxITwyI2H_mo"
    val token = "Bearer $apiKey"
    val api = RetrofitHelper.getInstance().create(API_Batalkan::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel_tiket_kapal)

        //get value yang sudah di intent
        val id = intent.getStringExtra("id")
        val asal = intent.getStringExtra("asal")
        val tujuan = intent.getStringExtra("tujuan")
        val tgl = intent.getStringExtra("tgl")
        val dewasa = intent.getStringExtra("dewasa")
        val anak = intent.getStringExtra("anak")

        //init
        tvasal = findViewById(R.id.tvasal)
        tvtujuan = findViewById(R.id.tvtujuan)
        tvtgl = findViewById(R.id.tvtgl)
        tvdewasa = findViewById(R.id.tvdewasa)
        tvanak = findViewById(R.id.tvanak)
        btnbatal = findViewById(R.id.batalkan)

        //set semua textview agar sesuai sama data yang sudah di intent
        tvasal.text = asal
        tvtujuan.text = tujuan
        tvtgl.text = tgl
        tvdewasa.text = dewasa
        tvanak.text = anak

        btnbatal.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Batalkan Tiket")
            alert.setMessage("Apakah anda yakin ingin Membatalkan Tiket Ini?")
            alert.setPositiveButton("Iya" , { dialog : DialogInterface?, which : Int ->
                val query = "eq.$id"
                CoroutineScope(Dispatchers.Main).launch {
                    api.deletetiketkapal(apiKey = apiKey , token = token , idquery = query)
                    Toast.makeText(applicationContext, "Berhasil Membatalkan Tiket", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
            alert.setNegativeButton("Tidak" , { dialog : DialogInterface?, which : Int ->})
            alert.show()
        }

    }
}