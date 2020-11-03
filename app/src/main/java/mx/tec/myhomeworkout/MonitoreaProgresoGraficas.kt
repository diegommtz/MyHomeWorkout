package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MonitoreaProgresoGraficas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitorea_progreso_graficas)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationCrack)

    }


    private val navigationCrack = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_profileAct -> {
                val intent = Intent(this@MonitoreaProgresoGraficas, ProfileAct::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                //        Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                //finish()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_monitoreoProgresoGraficas -> {
                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_paginaInicial -> {
                val intent = Intent(this@MonitoreaProgresoGraficas, PaginaInicial::class.java)
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