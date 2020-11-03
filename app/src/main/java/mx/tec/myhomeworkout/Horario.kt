package mx.tec.myhomeworkout

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_nacimiento.*
import kotlinx.android.synthetic.main.activity_nacimiento.btnNext
import kotlinx.android.synthetic.main.activity_nacimiento.four
import kotlinx.android.synthetic.main.activity_nacimiento.one
import kotlinx.android.synthetic.main.activity_nacimiento.three
import kotlinx.android.synthetic.main.activity_nacimiento.two
import kotlinx.android.synthetic.main.activity_peso.*

class Horario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horario)

        btnNext.setOnClickListener {
            val intent = Intent(this@Horario, PrevPhotos::class.java)
            startActivity(intent)
        }


        // SET VISIBILITY ELEMENTS
        val invisible = intent.getStringExtra("invisible")
        if (invisible == "true"){
            btnNext.visibility = View.INVISIBLE

        }
    }

}