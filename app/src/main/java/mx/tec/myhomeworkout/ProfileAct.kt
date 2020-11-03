package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileAct : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_perfil, menu) // reads the xml to see what elements it will add to the activity
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btnMeta.setOnClickListener{
            //Toast.makeText(this@ProfileAct, "MAIN usuario  password", Toast.LENGTH_LONG).show();
            val intent = Intent(this@ProfileAct, Objetivo::class.java)
            intent.putExtra("invisible", "true")
            startActivity(intent)
        }
        btnHorario.setOnClickListener{
            val intent = Intent(this@ProfileAct, Horario::class.java)
            intent.putExtra("invisible", "true")
            startActivity(intent)
        }
        btnPesoInicial.setOnClickListener{
            val intent = Intent(this@ProfileAct, Peso::class.java)
            intent.putExtra("invisible", "true")
            startActivity(intent)
        }

    }
}