package mx.tec.myhomeworkout

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_nacimiento.*
import java.util.*

class Nacimiento : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    var day = 0
    var month = 0
    var year = 0
    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nacimiento)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        /*btnCalendar.setOnClickListener{
            GetCalendarDate()
            DatePickerDialog(this@Nacimiento, this, year, month, day ).show()
        }*/

    }

    fun GetCalendarDate(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_WEEK)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month + 1 // no se porque te selecciona el mes anterior, por eso esta el +1
        savedYear = year
        GetCalendarDate()

        tvNacimiento.text = "" + savedDay + "-" + savedMonth + "-" + savedYear
    }

    fun altura(view: View){
        val intent = Intent(this@Nacimiento, Altura::class.java)
        startActivity(intent)
    }

}