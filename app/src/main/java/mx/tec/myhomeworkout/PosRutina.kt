package mx.tec.myhomeworkout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_pos_rutina.*

class PosRutina : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_rutina)

        val sp = getSharedPreferences("mhw", Context.MODE_PRIVATE)
        val rutinaNombre = sp.getString("rutinaNombre", "Cuerpo Completo")
        val ejerciciosSize = sp.getString("rutinaEjerciciosSize", "8")

        tvRutinaNombre.setText(rutinaNombre)
        tvEjerciciosSize.setText(ejerciciosSize + " ejercicios")
        tvMusculosTrabajados.setText("Gluteo, Espalda y Brazos")

        btnTomarFotos.setOnClickListener {
            val intent = Intent(this@PosRutina, PrevPhotos::class.java)
            //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
            //Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("invisible", "true")
            startActivity(intent)
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationCrack)
    }


    private val navigationCrack = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_profileAct -> {
                val intent = Intent(this@PosRutina, ProfileAct::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                //Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_monitoreoProgresoGraficas -> {
                val intent = Intent(this@PosRutina, MonitoreaProceso::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                //Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_paginaInicial -> {
                val intent = Intent(this@PosRutina, PaginaInicial::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                //        Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                //finish()
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

}