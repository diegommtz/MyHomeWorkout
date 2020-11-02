package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button

class ProfileAct : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_perfil, menu) // reads the xml to see what elements it will add to the activity
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val btnTrain=findViewById<Button>(R.id.btnTrain)
        val btnProfile=findViewById<Button>(R.id.btnProfile)
        val btnProgress=findViewById<Button>(R.id.btnProgress)

        btnTrain.setOnClickListener {

            val intent = Intent(this@ProfileAct,HaciendoEjercicio::class.java)
            startActivity(intent)

        }

        btnProfile.setOnClickListener{

            val intent = Intent(this@ProfileAct, ProfileAct::class.java)
            startActivity(intent)

        }

        btnProgress.setOnClickListener{
            val intent = Intent(this@ProfileAct, MonitoreaProceso::class.java)
            startActivity(intent)
        }

    }
}