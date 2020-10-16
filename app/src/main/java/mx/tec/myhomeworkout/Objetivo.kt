package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_objetivo.*

class Objetivo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objetivo)

        btnNext.setOnClickListener {
            val intent = Intent(this@Objetivo, Horario::class.java)
            startActivity(intent)
        }
    }
}