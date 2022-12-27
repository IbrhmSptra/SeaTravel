package id.kotlin.seatravel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class Riwayat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

//        val actionBar = supportActionBar
//        actionBar!!.title = "Riwayat"
//        actionBar.setDisplayHomeAsUpEnabled(true)

        var intent = intent

        var tanggals = intent.getStringExtra("tanggal")
        var asals = intent.getStringExtra("asal")
        var tujuans = intent.getStringExtra("tujuan")
        var dewasas = intent.getStringExtra("dewasa")
        var anaks = intent.getStringExtra("anak")

        var namas = intent.getStringExtra("Nama Pemesan")
        var kamars = intent.getStringExtra("Kamar")
        var jmlKamars = intent.getStringExtra("Jumlah Kamar")
        var checkIns = intent.getStringExtra("Check In")
        var checkOuts = intent.getStringExtra("Check Out")

        val tanggal = findViewById<TextView>(R.id.tanggal)
        val asal = findViewById<TextView>(R.id.asal)
        val tujuan = findViewById<TextView>(R.id.tujuan)
        val dewasa = findViewById<TextView>(R.id.dewasa)
        val anak = findViewById<TextView>(R.id.anak)
        val nama = findViewById<TextView>(R.id.nama)
        val kamar = findViewById<TextView>(R.id.kamar)
        val jumlahKamar = findViewById<TextView>(R.id.jmlKamar)
        val checkIn = findViewById<TextView>(R.id.`in`)
        val checkOut = findViewById<TextView>(R.id.out)

        asal.text = ": "+asals
        tujuan.text = ": "+tujuans
        tanggal.text = ": "+tanggals
        dewasa.text = ": "+dewasas
        anak.text = ": "+anaks

        nama.text = ": "+namas
        kamar.text = ": "+kamars
        jumlahKamar.text = ": "+jmlKamars
        checkIn.text = ": "+checkIns
        checkOut.text = ": "+checkOuts


    }
}