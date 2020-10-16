package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_genero.*

class Genero : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genero)
    }

    fun nacimiento(view: View){
        val intent = Intent(this@Genero, Nacimiento::class.java)
        startActivity(intent)
    }
}