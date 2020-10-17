package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_objetivo.*

class Objetivo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objetivo)

        btnPerderPeso.setOnClickListener {
            val intent = Intent(this@Objetivo, Horario::class.java)
            startActivity(intent)
        }
        btnEstarEnForma.setOnClickListener {
            val intent = Intent(this@Objetivo, Horario::class.java)
            startActivity(intent)
        }
        btnGanarMusculo.setOnClickListener {
            val intent = Intent(this@Objetivo, Horario::class.java)
            startActivity(intent)
        }
    }
}