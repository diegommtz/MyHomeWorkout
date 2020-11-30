package mx.tec.myhomeworkout

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_log_in.*
import mx.tec.myhomeworkout.model.Musculo
import mx.tec.myhomeworkout.model.Persona
import mx.tec.myhomeworkout.services.IMusculo
import mx.tec.myhomeworkout.services.IPersona
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogInAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        tvCreateAccount.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    fun genero(view: View) {

        //val persona = intent.getSerializableExtra("Persona") as? Persona
        val usuario = editTextTextPersonName.text.toString()
        val contrasena = editTextTextPassword.text.toString()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IPersona::class.java)


        service.login(usuario, contrasena).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { Log.e("RESTLIBS", it) }
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val idUsuario = response.body().toString()

                Log.e("aaaaaa", idUsuario.toString())

                if (idUsuario != "El registro no existe") {
                    val sp = getSharedPreferences("mhw", Context.MODE_PRIVATE)
                    with(sp.edit()) {
                        putString("idUsuario", idUsuario)
                        commit()
                    }
                    val intent = Intent(this@LogInAct, PaginaInicial::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LogInAct, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun crearCuenta(view: View) {
        val intent = Intent(this@LogInAct, Registro::class.java)
        startActivity(intent)
    }
}

//if (persona.objetivo == perderpeso)
//        GetRutina/idRutinaPerderPeso
