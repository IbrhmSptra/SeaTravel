package id.kotlin.seatravel

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import id.kotlin.seatravel.Adapter.AsalAdapter
import id.kotlin.seatravel.Api.*
import id.kotlin.seatravel.Data.Anak
import id.kotlin.seatravel.Data.Asal
import id.kotlin.seatravel.Data.Dewasa
import id.kotlin.seatravel.Data.Tujuan
import java.util.*

class BookKapal : AppCompatActivity() {


    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqamlscHlza2F3ZHRrdWRncmRjIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzExMzQ1NTAsImV4cCI6MTk4NjcxMDU1MH0.3o6R1Z5XEEC9wMgBWKMPsUaOfhrDUMbLxITwyI2H_mo"
    val token = "Bearer $apiKey"

    val Items_asal = ArrayList<Asal>()
    val AsalAPI = RetrofitHelper.getInstance().create(AsalApi::class.java)

    val Items_tujuan = ArrayList<Tujuan>()
    val TujuanAPI = RetrofitHelper.getInstance().create(TujuanApi::class.java)

    val Items_dewasa = ArrayList<Dewasa>()
    val DewasaAPI = RetrofitHelper.getInstance().create(DewasaApi::class.java)

    val Items_anak = ArrayList<Anak>()
    val AnakAPI = RetrofitHelper.getInstance().create(AnaklApi::class.java)




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_kapal)


        val spinners = findViewById(R.id.asal) as Spinner
        val spinner1 = findViewById(R.id.tujuan) as Spinner
        val spinner2 = findViewById(R.id.dewasa) as Spinner
        val spinner3 = findViewById(R.id.anak) as Spinner
        val tanggalBerangkat = findViewById(R.id.etTanggal) as EditText

//        CoroutineScope(Dispatchers.Main).launch {
//            val response = AsalAPI.get(token=token, apiKey=apiKey)
//
//            response.body()?.forEach {
//                Items_asal.add(
//                    Asal(
//                        asal = it.asal,
//                    )
//                )
//            }
//
//            DataAsal(Items_asal)
//        }


//
// Create an ArrayAdapter using the string array an a deafult spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.asal,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // specify the layout to use when the list of choice appears
            adapter. setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Applay the adapter to the spinner
            spinners.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.tujuan,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Menentukan pilihan saat daftar pilihan muncul
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Pemasangan adapter ke dalam spinner
            spinner1.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.jumlah_dewasa,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // specify the layout to use when the list of choice appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Applay the adapter to the spinner
            spinner2.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.jumlah_anak,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // specify the layout to use when the list of choice appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Applay the adapter to the spinner
            spinner3.adapter = adapter
        }

//        val Items = ArrayList<Asal>()
//        Items.add(Asal("Tj. Priok, Jakarta"))


        tanggalBerangkat.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, day ->

                    val formattedMonth = if (month < 10) "0$month" else "$month"
                    val formattedDayOfMonth = if (day < 10) "0$day" else "$day"
                    tanggalBerangkat.setText("$formattedDayOfMonth/$formattedMonth/$year")

                    // Mendapatkan nilai dari date picker
                    val selectedDate = "$formattedDayOfMonth/$formattedMonth/$year"
                }, year, month, day
            )
            datePickerDialog.show()
        }


        val book_btn = findViewById<Button>(R.id.book)

        book_btn.setOnClickListener {
            val tanggalBerangkat = tanggalBerangkat.text.toString()
            val spinners = spinners.selectedItem.toString()
            val spinner1 = spinner1.selectedItem.toString()
            val spinner2 = spinner2.selectedItem.toString()
            val spinner3 = spinner3.selectedItem.toString()


            val intent = Intent(this@BookKapal,HomeActivity::class.java)
            intent.putExtra("tanggal", tanggalBerangkat)
            intent.putExtra("asal", spinners)
            intent.putExtra("tujuan", spinner1)
            intent.putExtra("dewasa", spinner2)
            intent.putExtra("anak", spinner3)


            startActivity(intent)


        }

    }


//    private fun DataAsal(itemsAsal: ArrayList<Asal>) {
//        val adapter =
//
//    }
}
