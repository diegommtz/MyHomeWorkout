package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MonitoreaProceso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitorea_progreso_fotos)


        val btnTrain=findViewById<Button>(R.id.btnTrain)
        val btnProfile=findViewById<Button>(R.id.btnProfile)
        val btnProgress=findViewById<Button>(R.id.btnProgress)

        btnTrain.setOnClickListener {

            val intent = Intent(this@MonitoreaProceso,HaciendoEjercicio::class.java)
            startActivity(intent)

        }

        btnProfile.setOnClickListener{

            val intent = Intent(this@MonitoreaProceso, ProfileAct::class.java)
            startActivity(intent)

        }

        btnProgress.setOnClickListener{
            val intent = Intent(this@MonitoreaProceso, MonitoreaProceso::class.java)
            startActivity(intent)
        }

    }
}