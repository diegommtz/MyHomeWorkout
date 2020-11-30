package mx.tec.myhomeworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*
import mx.tec.myhomeworkout.model.Persona

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
    }

    fun logIn(view: View){
        var nombre = txtNombreRegistro.text.toString()
        var contrasena = txtContrasenaRegistro.text.toString()
        var contrasena2 = txtContrasenaRegistro2.text.toString()

        if(nombre != "" && contrasena != "" && contrasena2!= ""){
           if(contrasena==contrasena2)
           {
               val intent = Intent(this@Registro, Genero::class.java)
               val persona = Persona(0,nombre,contrasena,0, "","","",0f)
               intent.putExtra("Persona", persona)
               startActivity(intent)
           }else{
               Toast.makeText(this@Registro, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
           }
        }else{
            Toast.makeText(this@Registro, "Favor de completar los campos faltantes", Toast.LENGTH_SHORT).show()
        }

    }

}