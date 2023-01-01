package id.kotlin.seatravel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kotlin.seatravel.Api.RetrofitHelper
import id.kotlin.seatravel.recycleviewTiketKamar.API_tiketkamar
import id.kotlin.seatravel.recycleviewTiketKamar.dataTiketKamar
import id.kotlin.seatravel.recycleviewTiketKamar.tiketkamarAdapter
import id.kotlin.seatravel.recycleviewTiketKapal.API_tiketkapal
import id.kotlin.seatravel.recycleviewTiketKapal.datatTiketKapal
import id.kotlin.seatravel.recycleviewTiketKapal.tiketkapalAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class mytiket : AppCompatActivity() {

    //recyleview tiket kapal
    lateinit var rctiketkapal: RecyclerView
    lateinit var kapaladapter: tiketkapalAdapter
    lateinit var datatiketkapal : ArrayList<datatTiketKapal>

    //recycleview tiket kamar
    lateinit var rctiketkamar : RecyclerView
    lateinit var kamaradapter : tiketkamarAdapter
    lateinit var datatiketkamar : ArrayList<dataTiketKamar>

    //API
    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqamlscHlza2F3ZHRrdWRncmRjIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzExMzQ1NTAsImV4cCI6MTk4NjcxMDU1MH0.3o6R1Z5XEEC9wMgBWKMPsUaOfhrDUMbLxITwyI2H_mo"
    val token = "Bearer $apiKey"
    val apikapal = RetrofitHelper.getInstance().create(API_tiketkapal::class.java)
    val apikamar = RetrofitHelper.getInstance().create(API_tiketkamar::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mytiket)

        //set recycleview tiket kapal
        rctiketkapal = findViewById(R.id.rctiketkapal)
        rctiketkapal.setHasFixedSize(true)
        rctiketkapal.layoutManager = LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false)
        CoroutineScope(Dispatchers.Main).launch {
            //add data tiket kapal dari Supabase
            val response = apikapal.get(token = token, apiKey = apiKey)
            datatiketkapal = ArrayList()
            response.body()?.forEach {
                datatiketkapal.add(
                    datatTiketKapal(
                        id = it.id,
                        asal = it.Asal,
                        tujuan = it.Tujuan,
                        tanggal = it.Tanggal,
                        dewasa = it.JmlDewasa,
                        anak = it.JmlAnak
                )
                )
            }
            //set adapter dengan data ke recycleview
            kapaladapter = tiketkapalAdapter(datatiketkapal)
            rctiketkapal.adapter = kapaladapter
        }

        //set recycleview tiket kamar
        rctiketkamar = findViewById(R.id.rctiketkamar)
        rctiketkamar.setHasFixedSize(true)
        rctiketkamar.layoutManager = LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false)
        CoroutineScope(Dispatchers.Main).launch {
            //add data tiket kamar dari Supabase
            val response = apikamar.get(token = token, apiKey = apiKey)
            datatiketkamar = ArrayList()
            response.body()?.forEach {
                datatiketkamar.add(
                    dataTiketKamar(
                        id = it.id,
                        nama = it.NamaPemesan,
                        kamar = it.Kamar,
                        jmlKamar = it.JmlKamar,
                        checkin = it.CheckIn,
                        checkout = it.CheckOut
                    )
                )
            }
            //set adapter dengan data ke recycleview
            kamaradapter = tiketkamarAdapter(datatiketkamar)
            rctiketkamar.adapter = kamaradapter
        }

    }

    //refresh setelah delete
    override fun onResume() {
        super.onResume()


        //recycleview tiket kapal
        CoroutineScope(Dispatchers.Main).launch {
            //add data tiket kapal dari Supabase
            val response = apikapal.get(token = token, apiKey = apiKey)
            datatiketkapal = ArrayList()
            response.body()?.forEach {
                datatiketkapal.add(
                    datatTiketKapal(
                        id = it.id,
                        asal = it.Asal,
                        tujuan = it.Tujuan,
                        tanggal = it.Tanggal,
                        dewasa = it.JmlDewasa,
                        anak = it.JmlAnak
                    )
                )
            }
            //set adapter dengan data ke recycleview
            kapaladapter = tiketkapalAdapter(datatiketkapal)
            rctiketkapal.adapter = kapaladapter
        }

        //recycleview tiket kamar
        CoroutineScope(Dispatchers.Main).launch {
            //add data tiket kamar dari Supabase
            val response = apikamar.get(token = token, apiKey = apiKey)
            datatiketkamar = ArrayList()
            response.body()?.forEach {
                datatiketkamar.add(
                    dataTiketKamar(
                        id = it.id,
                        nama = it.NamaPemesan,
                        kamar = it.Kamar,
                        jmlKamar = it.JmlKamar,
                        checkin = it.CheckIn,
                        checkout = it.CheckOut
                    )
                )
            }
            //set adapter dengan data ke recycleview
            kamaradapter = tiketkamarAdapter(datatiketkamar)
            rctiketkamar.adapter = kamaradapter
        }
    }
}