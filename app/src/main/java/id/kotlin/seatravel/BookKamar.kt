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
import java.text.SimpleDateFormat
import java.util.*

class BookKamar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_kamar)


        val spinnerA = findViewById(R.id.spKamar) as Spinner
        val spinnerB = findViewById(R.id.spJmlKamar) as Spinner
        var checkin = findViewById(R.id.idCheckIn) as EditText
        var checkout = findViewById(R.id.idCheckOut) as EditText
        val NamaPemesan = findViewById(R.id.txtNama) as EditText


        ArrayAdapter.createFromResource(
            this,
            R.array.kamar,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // specify the layout to use when the list of choice appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Applay the adapter to the spinner
            spinnerA.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.jumlah_kamar,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // specify the layout to use when the list of choice appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Applay the adapter to the spinner
            spinnerB.adapter = adapter
        }

        checkin.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, day ->

                    val formattedMonth = if (month < 10) "0$month" else "$month"
                    val formattedDayOfMonth = if (day < 10) "0$day" else "$day"
                    checkin.setText("$formattedDayOfMonth/$formattedMonth/$year")

                    // Mendapatkan nilai dari date picker
                    val selectedDate = "$formattedDayOfMonth/$formattedMonth/$year"
                }, year, month, day
            )
            datePickerDialog.show()
        }

        checkout.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, day ->

                    val formattedMonth = if (month < 10) "0$month" else "$month"
                    val formattedDayOfMonth = if (day < 10) "0$day" else "$day"
                    checkout.setText("$formattedDayOfMonth/$formattedMonth/$year")

                    // Mendapatkan nilai dari date picker
                    val selectedDate = "$formattedDayOfMonth/$formattedMonth/$year"
                }, year, month, day
            )
            datePickerDialog.show()
        }

        val book_btn = findViewById<Button>(R.id.book)
        book_btn.setOnClickListener {
            val checkin = checkin.text.toString()
            val checkout = checkout.text.toString()
            val NamaPemesan = NamaPemesan.text.toString()
            val spinnerA = spinnerA.selectedItem.toString()
            val spinnerB = spinnerB.selectedItem.toString()


            val intent = Intent(this@BookKamar, HomeActivity::class.java)
            intent.putExtra("Nama Pemesan", NamaPemesan)
            intent.putExtra("Kamar", spinnerA)
            intent.putExtra("Jumlah Kamar", spinnerB)
            intent.putExtra("Check In", checkin)
            intent.putExtra("Check Out", checkout)


            startActivity(intent)
        }
    }

}