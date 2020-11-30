package mx.tec.myhomeworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_profile.*
import mx.tec.myhomeworkout.model.Persona
import mx.tec.myhomeworkout.services.IPersona
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.mnuConfiguracion -> {
                val preferences = getSharedPreferences("mhw",  Context.MODE_PRIVATE)
                preferences.edit().remove("idUsario").apply()
                val intent = Intent(this@ProfileAct, LogInAct::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val sp = getSharedPreferences("mhw", Context.MODE_PRIVATE)
        lateinit var persona: Persona

        var idPersona: String? = ""

            //Cargar datos persona
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(IPersona::class.java)
            idPersona = sp.getString("idUsuario", "")

            service.getPersona(idPersona).enqueue(object : Callback<Persona> {
                override fun onFailure(call: Call<Persona>, t: Throwable) {
                    t.message?.let { Log.e("RESTLIBS----", it) }
                }

                override fun onResponse(
                    call: Call<Persona>,
                    response: retrofit2.Response<Persona>
                ) {
                    persona = response.body()!!
                    txtNombre.text = persona.nombre.toString()
                    tvMeta.text = persona.objetivo?.nombre.toString()
                    tvPesoInicial.text = (persona.peso.toString())
                    tvEntrenamientos.text = persona.entrenamientos.toString()
                    tvIMC.text = ((persona.altura?.div(100f))?.times(
                        (persona.altura?.div(
                            100f
                        )!!)
                    )?.let {
                        persona.peso?.div(
                            it
                        )
                    }).toString() + "%"
                    tvPesoIdeal.text = ((persona.altura)?.minus(100)).toString() + "Kg."
                }
            })

            //--------------------------------------------
        btnMeta.setOnClickListener{
            //Toast.makeText(this@ProfileAct, "MAIN usuario  password", Toast.LENGTH_LONG).show();
            val intent = Intent(this@ProfileAct, ObjetivoAct::class.java)
            intent.putExtra("persistObjetivo", persona.objetivo?.nombre.toString())
            intent.putExtra("personaObjetivo", persona)
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
            intent.putExtra("persistPeso", persona.peso)
            intent.putExtra("personaObjetivo", persona)
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
                val intent = Intent(this@ProfileAct, MonitoreaProceso::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_paginaInicial -> {
                val intent = Intent(this@ProfileAct, PaginaInicial::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                //finish()
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }


}