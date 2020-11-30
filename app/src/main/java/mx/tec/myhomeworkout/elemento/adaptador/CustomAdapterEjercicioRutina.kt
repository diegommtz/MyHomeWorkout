package mx.tec.myhomeworkout.elemento.adaptador

import android.app.AlertDialog
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View.inflate
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mx.tec.myhomeworkout.R
import mx.tec.myhomeworkout.elemento.modelo.ElementChild
import mx.tec.myhomeworkout.model.Ejercicio

class CustomAdapterEjercicioRutina(
    private val context: Context,
    private val layout: Int,
    private val dataSource: List<Ejercicio>,
    private val animation: Int,
    private val layoutInflater: LayoutInflater
)
    : RecyclerView.Adapter<CustomAdapterEjercicioRutina.ElementoViewHolder>(){

    inner class ElementoViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        layout: Int
    )
        : RecyclerView.ViewHolder(inflater.inflate(layout, parent, false)){

        //Viewholder es la clase que se encarga de manipular los controles de los elementos
        var nombre: TextView? = null
        var reps: TextView? = null
        var imagen: ImageView? = null
        var btnDetalles: ImageButton? = null

        init{
            nombre = itemView.findViewById(R.id.tvNombreEjercicio)
            reps = itemView.findViewById(R.id.tvRepeticionesTiempo)
            imagen = itemView.findViewById(R.id.ivEjercicio)
            btnDetalles = itemView.findViewById(R.id.btnEjercicioDetalles)

        }

        //establece los valores
        fun bindData(elemento: Ejercicio){
            nombre!!.text = elemento.nombre
            if (elemento.repeticiones == true){
                reps!!.text = elemento.cantidadTiempo.toString() + " repeticiones"
            }else{
                reps!!.text = elemento.cantidadTiempo.toString() + " min."
            }
            Picasso.get().load(elemento.foto).into(imagen)

            btnDetalles!!.setOnClickListener {
                val dialog = AlertDialog.Builder(context)
                val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)

                dialog.setPositiveButton("Entendido") { dialog, which ->
                    dialog.dismiss()
                }

                var tvNombreDiag = dialogView.findViewById<TextView>(R.id.tvNombreDiag)

                //VIDEO
                var video = dialogView.findViewById<VideoView>(R.id.vvDialog)
                val onlineUri = Uri.parse(elemento.video)
                video!!.setVideoURI(onlineUri)
                video!!.setMediaController(null)
                video!!.setOnPreparedListener {
                    video!!.start()
                }
                dialog.setView(dialogView)
                dialog.setCancelable(false)
                dialog.show()
                video!!.start()

                video!!.setOnClickListener{
                    video!!.start()
                }
                /*
                builder.setTitle("Androidly Alert")
                builder.setMessage("We have a message")

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->

                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->

                }

                builder.setNeutralButton("Maybe") { dialog, which ->

                }
                builder.show()*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapterEjercicioRutina.ElementoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ElementoViewHolder(inflater, parent, layout)
    }


    override fun onBindViewHolder(holder: ElementoViewHolder, position: Int) {
        val elemento = dataSource[position]
        holder.bindData(elemento)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}

