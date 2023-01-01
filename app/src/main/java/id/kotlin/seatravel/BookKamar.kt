package id.kotlin.seatravel

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import id.kotlin.seatravel.Api.RetrofitHelper
import id.kotlin.seatravel.book.API_BookKamar
import id.kotlin.seatravel.book.bookkamarAPI
import id.kotlin.seatravel.spinnerKamar.API_kamar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class BookKamar : AppCompatActivity() , DatePickerDialog.OnDateSetListener {

    //komponen
    lateinit var etnama : EditText
    lateinit var spkamar : Spinner
    lateinit var spjmlkamar : Spinner
    lateinit var btncheckin : CardView
    lateinit var tvtglcheckin : TextView
    lateinit var btncheckout : CardView
    lateinit var tvtglcheckout : TextView
    lateinit var btnbook : Button

    //variabel input
    var inputnama : String? = null
    var inputspkamar : String? = null
    var inputspjmlkamar : String?= null
    var inputtglCI : String? = null
    var inputtglCO : String?= null

    //untuk spinner
    lateinit var kamar : ArrayList<String>
    var number = arrayOf("1" , "2" , "3", "4")

    // untuk date picker check in
    var selectedYearCI = 0
    var selectedMonthCI = 0
    var selectedDayCI = 0

    //untuk membagi 2 datepicker
    var calendar = ""

    // untuk date picker checkout
    var selectedYearCO = 0
    var selectedMonthCO = 0
    var selectedDayCO = 0

    //API
    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqamlscHlza2F3ZHRrdWRncmRjIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzExMzQ1NTAsImV4cCI6MTk4NjcxMDU1MH0.3o6R1Z5XEEC9wMgBWKMPsUaOfhrDUMbLxITwyI2H_mo"
    val token = "Bearer $apiKey"
    val apispkamar = RetrofitHelper.getInstance().create(API_kamar::class.java)
    val apibook = RetrofitHelper.getInstance().create(API_BookKamar::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_kamar)

        //init
        etnama = findViewById(R.id.et_nama)
        spkamar = findViewById(R.id.spKamar)
        spjmlkamar = findViewById(R.id.spJmlKamar)
        btncheckin = findViewById(R.id.btntglcheckin)
        tvtglcheckin = findViewById(R.id.tvtglcheckin)
        btncheckout = findViewById(R.id.btntglcheckout)
        tvtglcheckout = findViewById(R.id.tvtglcheckout)
        btnbook = findViewById(R.id.book)



        //set spinner kamar
        CoroutineScope(Dispatchers.Main).launch {
            kamar = ArrayList()
            val response = apispkamar.get(token = token , apiKey = apiKey)
            response.body()?.forEach {
                kamar.add(it.kamar)
            }
            //adapter spKamar
            val spkamarAdapter = ArrayAdapter<String>(applicationContext , android.R.layout.simple_spinner_dropdown_item , kamar)
            spkamar.adapter = spkamarAdapter
            spkamar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    inputspkamar = kamar[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }

        //set spinner jmlkamar
        val spjmlkamarAdapter = ArrayAdapter<String>(applicationContext , android.R.layout.simple_spinner_dropdown_item , number)
        spjmlkamar.adapter = spjmlkamarAdapter
        spjmlkamar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                inputspjmlkamar = number[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        //memunculkan calender pada btn check in
        btncheckin.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, this, year, month, day)
            datePickerDialog.show()
            calendar = "CI"
        }

        //memunculkan calender pada btn checkout
        btncheckout.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, this, year, month, day)
            datePickerDialog.show()
            calendar = "CO"
        }



        btnbook.setOnClickListener {
            //get value et nama
            inputnama = etnama.text.toString()


            //validasi untuk tanggal yang belum di set
            if(tvtglcheckin.text.equals("Set Tanggal")) {
                Toast.makeText(this, "Set Tanggal Terlebih Dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(tvtglcheckout.text.equals("Set Tanggal")) {
                Toast.makeText(this, "Set Tanggal Terlebih Dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //validasi jika etnama kosong
            if (inputnama.isNullOrEmpty()) {
                Toast.makeText(this, "Isi Nama Terlebih Dahulu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //post to supabase
            CoroutineScope(Dispatchers.Main).launch {
                //book tiket kamar
                val datakamar  = bookkamarAPI(
                    NamaPemesan = inputnama!!,
                    Kamar = inputspkamar!!,
                    JmlKamar = inputspjmlkamar!!,
                    CheckIn = inputtglCI!!,
                    CheckOut = inputtglCO!!
                )
                apibook.createtiketkamar(apiKey = apiKey , token = token , data = datakamar)
                Toast.makeText(applicationContext, "Tiket Kamar Berhasil Di Booking", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        // Tentukan date picker mana yang dipilih oleh pengguna
        if (calendar.equals("CI")) {
            // Simpan tanggal, bulan, dan tahun yang dipilih oleh pengguna untuk date picker Checkin
            selectedYearCI = year
            selectedMonthCI = month + 1
            selectedDayCI = dayOfMonth

            //get value date
            inputtglCI = "$selectedDayCI/$selectedMonthCI/$selectedYearCI"
            tvtglcheckin.text = inputtglCI

        } else if (calendar.equals("CO")) {
            // Simpan tanggal, bulan, dan tahun yang dipilih oleh pengguna untuk date picker Checkout
            selectedYearCO = year
            selectedMonthCO = month + 1
            selectedDayCO = dayOfMonth

            //get value date
            inputtglCO = "$selectedDayCO/$selectedMonthCO/$selectedYearCO"
            tvtglcheckout.text = inputtglCO
        }
    }
}