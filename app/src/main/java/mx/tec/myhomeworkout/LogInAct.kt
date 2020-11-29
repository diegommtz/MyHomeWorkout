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
        val intent = Intent(this@LogInAct, Genero::class.java)
        startActivity(intent)
    }

    fun crearCuenta(view: View) {
        val intent = Intent(this@LogInAct, Registro::class.java)
        startActivity(intent)
    }
}