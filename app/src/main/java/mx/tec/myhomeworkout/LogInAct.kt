package mx.tec.myhomeworkout

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_log_in.*
import mx.tec.myhomeworkout.model.Musculo
import mx.tec.myhomeworkout.services.IMusculo
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

        val retrofit = Retrofit.Builder()
            .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IMusculo::class.java)

        service.GetAllMusculos().enqueue(object : Callback<List<Musculo>> {
            override fun onFailure(call: Call<List<Musculo>>, t: Throwable) {
                Log.e("Workout-API", "Error obteniendo datos")
                Log.e("Workout-API", t.message!!)
            }

            override fun onResponse(call: Call<List<Musculo>>, response: Response<List<Musculo>>) {
                val musculos = response.body()!!
                Log.e("Workout-API", musculos.toString())
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
