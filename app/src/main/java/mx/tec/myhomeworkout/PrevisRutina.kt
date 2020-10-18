package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_previs_rutina.*
import mx.tec.myhomeworkout.elemento.adaptador.CustomAdapterParent
import mx.tec.myhomeworkout.elemento.modelo.ElementChild
import mx.tec.myhomeworkout.elemento.modelo.ElementParent

class PrevisRutina : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previs_rutina)

        comenzarRutina.setOnClickListener {
            val intent = Intent(this@PrevisRutina, HaciendoEjercicio::class.java)
            startActivity(intent)
        }

        val dataChild1 = listOf(
            ElementChild("Squats", R.drawable.img_mujer_plancking, "10 repeticiones"),
            ElementChild("Plancking", R.drawable.img_mujer_plancking, "2 min"),
            ElementChild("Sentadillas", R.drawable.img_mujer_plancking, "25 repeticiones")
        )

        val dataChild2 = listOf(
            ElementChild("Pesas", R.drawable.img_mujer_plancking, "10 repeticiones"),
            ElementChild("Abdominales", R.drawable.img_mujer_plancking, "25 repeticiones"),
            ElementChild("Abdominales bajas", R.drawable.img_mujer_plancking, "25 repeticiones")
        )

        val dataParent = listOf(
            ElementParent("Circuito 1", "3 sets", "10 min descanso", dataChild1),
            ElementParent("Circuito 2", "4 sets", "15 min descanso", dataChild2)
        )

        val adaptador = CustomAdapterParent(this@PrevisRutina, R.layout.layout_parent, dataParent, 0)

        rvParent.layoutManager = LinearLayoutManager(this@PrevisRutina, LinearLayoutManager.VERTICAL, false)

        rvParent.adapter = adaptador
    }
}