package mx.tec.myhomeworkout

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_horario.*
import kotlinx.android.synthetic.main.activity_nacimiento.*
import kotlinx.android.synthetic.main.activity_nacimiento.btnNext
import kotlinx.android.synthetic.main.activity_nacimiento.four
import kotlinx.android.synthetic.main.activity_nacimiento.one
import kotlinx.android.synthetic.main.activity_nacimiento.three
import kotlinx.android.synthetic.main.activity_nacimiento.two
import kotlinx.android.synthetic.main.activity_peso.*
import mx.tec.myhomeworkout.data.IHorario
import mx.tec.myhomeworkout.model.HorarioModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Date

class Horario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horario)

        val boolHorario:Array<Boolean> = arrayOf(false,false,false,false,false,false,false)

        var d:Boolean=false
        var l:Boolean=false
        var m:Boolean=false
        var mm:Boolean=false
        var j:Boolean=false
        var v:Boolean=false
        var s:Boolean=false

//        val dia = timePicker1.dayOfMonth.toString()
//        val mes = (timePicker1.month + 1).toString()
//        val anio = timePicker1.year.toString()

        val toggleD: ToggleButton = findViewById(R.id.toggleButton)
        val togglel: ToggleButton = findViewById(R.id.toggleButton2)
        val togglem: ToggleButton = findViewById(R.id.toggleButton3)
        val togglemm: ToggleButton = findViewById(R.id.toggleButton4)
        val togglej: ToggleButton = findViewById(R.id.toggleButton5)
        val togglev: ToggleButton = findViewById(R.id.toggleButton6)
        val toggles: ToggleButton = findViewById(R.id.toggleButton7)

        toggleD.setOnCheckedChangeListener { _, isChecked ->
            boolHorario[0]=isChecked
        }
        togglel.setOnCheckedChangeListener { _, isChecked ->
            boolHorario[1]=isChecked
        }
        togglem.setOnCheckedChangeListener { _, isChecked ->
            boolHorario[2]=isChecked
        }
        togglemm.setOnCheckedChangeListener { _, isChecked ->
            boolHorario[3]=isChecked
        }
        togglej.setOnCheckedChangeListener { _, isChecked ->
            boolHorario[4]=isChecked
        }
        togglev.setOnCheckedChangeListener { _, isChecked ->
            boolHorario[5]=isChecked
        }
        toggles.setOnCheckedChangeListener { _, isChecked ->
            boolHorario[6]=isChecked
        }



        btnNext.setOnClickListener {
            val tHorario = HorarioModel("0",1,1,d,l,m,mm,j,v,s)
            val intent = Intent(this@Horario, PrevPhotos::class.java)
            intent.putExtra("numbers",boolHorario);
            startActivity(intent)
        }


        // SET VISIBILITY ELEMENTS
        val invisible = intent.getStringExtra("invisible")
        if (invisible == "true"){
            btnNext.visibility = View.INVISIBLE
        }
    }

}