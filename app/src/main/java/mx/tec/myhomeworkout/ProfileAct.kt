package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileAct : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_perfil, menu) // reads the xml to see what elements it will add to the activity
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        //bottomNavigation.setSelectedItemId(R.id.home)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationCrack)

    }

    private val navigationCrack = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_profileAct -> {

                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_monitoreoProgresoGraficas -> {
                val intent = Intent(this@ProfileAct, MonitoreaProgresoGraficas::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                //Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_paginaInicial -> {
                val intent = Intent(this@ProfileAct, PaginaInicial::class.java)
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