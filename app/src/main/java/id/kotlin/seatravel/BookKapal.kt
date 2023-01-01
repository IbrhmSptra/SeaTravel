package id.kotlin.seatravel

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import id.kotlin.seatravel.Api.*
import id.kotlin.seatravel.book.API_BookKapal
import id.kotlin.seatravel.book.bookkapalAPI
import id.kotlin.seatravel.spinnerAsal.API_asal
import id.kotlin.seatravel.sptujuan.API_tujuan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class BookKapal : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    //API
    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqamlscHlza2F3ZHRrdWRncmRjIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzExMzQ1NTAsImV4cCI6MTk4NjcxMDU1MH0.3o6R1Z5XEEC9wMgBWKMPsUaOfhrDUMbLxITwyI2H_mo"
    val token = "Bearer $apiKey"
    val apiasal = RetrofitHelper.getInstance().create(API_asal::class.java)
    val apitujuan = RetrofitHelper.getInstance().create(API_tujuan::class.java)
    val apibook = RetrofitHelper.getInstance().create(API_BookKapal::class.java)

    //komponen
    lateinit var spAsal : Spinner
    lateinit var spTujuan : Spinner
    lateinit var btntgl : CardView
    lateinit var tvtgl : TextView
    lateinit var spanak : Spinner
    lateinit var spdewasa : Spinner
    lateinit var btnbook : Button

    //untuk spinner
    lateinit var asal : ArrayList<String>
    lateinit var tujuan : ArrayList<String>
    val number = arrayOf("1" , "2" , "3" , "4")

    //get value date
    var selectedYear = 0
    var selectedMonth = 0
    var selectedDay = 0

    //all input user
    var inputAsal: String? = null
    var inputTujuan : String?= null
    var inputTgl : String?= null
    var inputanak : String?= null
    var inputdewasa : String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_kapal)



        //init
        spAsal = findViewById(R.id.spasal)
        spTujuan = findViewById(R.id.sptujuan)
        btntgl = findViewById(R.id.btntgl)
        tvtgl = findViewById(R.id.tvtgl)
        spanak = findViewById(R.id.spanak)
        spdewasa = findViewById(R.id.spdewasa)
        btnbook = findViewById(R.id.book)


        //get asal from database
        CoroutineScope(Dispatchers.Main).launch {
            asal = ArrayList()
            val response = apiasal.get(token = token , apiKey = apiKey)
            response.body()?.forEach {
                asal.add(it.tempat_asal)
            }
            //adapter spAsal
            val spAsalAdapter = ArrayAdapter<String>(applicationContext , android.R.layout.simple_spinner_dropdown_item , asal)
            spAsal.adapter = spAsalAdapter
            spAsal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    inputAsal = asal[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }

        //get tujuan from database
        CoroutineScope(Dispatchers.Main).launch {
            tujuan = ArrayList()
            val response = apitujuan.get(token = token , apiKey = apiKey)
            response.body()?.forEach {
                tujuan.add(it.tempat_tujuan)
            }
            //adapter spTujuan
            val spTujuanAdapter = ArrayAdapter<String>(applicationContext , android.R.layout.simple_spinner_dropdown_item , tujuan)
            spTujuan.adapter = spTujuanAdapter
            spTujuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    inputTujuan = tujuan[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }

        //button untuk memunculkan date
        btntgl.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, this, year, month, day)
            datePickerDialog.show()
        }


        //set sp Anak dan sp dewasa
        val spjmlhAdapter = ArrayAdapter<String>(applicationContext , android.R.layout.simple_spinner_dropdown_item , number)
        spanak.adapter = spjmlhAdapter
        spdewasa.adapter = spjmlhAdapter
        spanak.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                inputanak = number[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        spdewasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                inputdewasa = number[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }




        btnbook.setOnClickListener {
            //validasi untuk tanggal yang belum di set
            if(tvtgl.text.equals("Set Tanggal")) {
                Toast.makeText(this, "Set Tanggal Terlebih Dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //post to supabase
            CoroutineScope(Dispatchers.Main).launch {
                val data = bookkapalAPI(
                    Asal = inputAsal!! ,
                    Tujuan = inputTujuan!!,
                    Tanggal = inputTgl!!,
                    JmlDewasa = inputdewasa!!,
                    JmlAnak = inputanak!!
                )
                apibook.createtiketkapal(token = token , apiKey = apiKey , data = data)
                Toast.makeText(applicationContext, "Tiket Kapal Berhasil Di Booking", Toast.LENGTH_SHORT).show()
                finish()
            }


        }



    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        // Simpan tanggal, bulan, dan tahun yang dipilih oleh pengguna
        selectedYear = year
        selectedMonth = month + 1
        selectedDay = dayOfMonth

        //getvalue date
        inputTgl = "$selectedDay/$selectedMonth/$selectedYear"
        tvtgl.text = inputTgl
    }

}
