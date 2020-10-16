package mx.tec.myhomeworkout

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        tvCreateAccount.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    fun genero(view: View){
        val intent = Intent(this@LogInAct, Genero::class.java)
        startActivity(intent)
    }
}