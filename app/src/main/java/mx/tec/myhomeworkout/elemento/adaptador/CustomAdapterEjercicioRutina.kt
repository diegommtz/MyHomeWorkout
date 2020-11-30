package mx.tec.myhomeworkout.elemento.adaptador

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tec.myhomeworkout.R
import mx.tec.myhomeworkout.elemento.modelo.ElementChild
import mx.tec.myhomeworkout.model.Ejercicio

class CustomAdapterEjercicioRutina(
    private val context: Context,
    private val layout: Int,
    private val dataSource: List<Ejercicio>,
    private val animation: Int
)
    : RecyclerView.Adapter<CustomAdapterEjercicioRutina.ElementoViewHolder>(){

    class ElementoViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        layout: Int
    )
        : RecyclerView.ViewHolder(inflater.inflate(layout, parent, false)){

        //Viewholder es la clase que se encarga de manipular los controles de los elementos
        var nombre: TextView? = null
        var reps: TextView? = null
        var imagen: ImageView? = null

        init{
            nombre = itemView.findViewById(R.id.tvNombreEjercicio)
            reps = itemView.findViewById(R.id.tvRepeticionesTiempo)
            imagen = itemView.findViewById(R.id.ivEjercicio)
        }

        //establece los valores
        fun bindData(elemento: Ejercicio){
            nombre!!.text = elemento.nombre
            if (elemento.repeticiones == true){
                reps!!.text = elemento.cantidadTiempo.toString() + " repeticiones"
            }else{
                reps!!.text = elemento.cantidadTiempo.toString() + " min."
            }

            
            imagen!!.setImageResource(R.drawable.entrenador_nava)
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

