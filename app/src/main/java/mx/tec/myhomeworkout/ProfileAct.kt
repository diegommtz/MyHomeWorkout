package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import mx.tec.myhomeworkout.services.IPersona
import mx.tec.myhomeworkout.model.Persona
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class ProfileAct : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_perfil, menu) // reads the xml to see what elements it will add to the activity
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val idPersona = intent.getStringExtra("idPersona")
        lateinit var persona: Persona
        //Cargar datos persona
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.100.9:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IPersona::class.java)

        if (idPersona != null) {
            service.getPersona(idPersona.toInt()).enqueue(object : Callback<Persona> {
                override fun onFailure(call: Call<Persona>, t: Throwable) {
                    t.message?.let { Log.e("RESTLIBS", it) }
                }

                override fun onResponse(
                    call: Call<Persona>,
                    response: retrofit2.Response<Persona>
                ) {
                    persona= response.body()!!
                    txtNombre.text = persona.nombre.toString()
                    tvMeta.text = persona.objetivo.toString()
                    tvPesoInicial.text = (persona.peso.toString())
                }
            })
        }
        //--------------------------------------------


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