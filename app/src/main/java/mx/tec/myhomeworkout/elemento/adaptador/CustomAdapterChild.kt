package mx.tec.myhomeworkout.elemento.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tec.myhomeworkout.R
import mx.tec.myhomeworkout.elemento.modelo.ElementChild
import mx.tec.myhomeworkout.elemento.modelo.ElementParent

class CustomAdapterChild(
    //private val context: Context,
    private val layout: Int,
    //private val dataSource: List<ElementChild>,
    //private val animation: Int
    private val elementParent: ElementParent
)
    : RecyclerView.Adapter<CustomAdapterChild.ElementoViewHolder>(){

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
            reps = itemView.findViewById(R.id.tvRepeticiones)
            imagen = itemView.findViewById(R.id.ivEjercicio)
        }

        //establece los valores
        fun bindData(elemento: ElementChild){
            nombre!!.text = elemento.nombre
            reps!!.text = elemento.repeticion
            imagen!!.setImageResource(elemento.imagen)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ElementoViewHolder(inflater, parent, layout)
    }

    override fun onBindViewHolder(holder: ElementoViewHolder, position: Int) {
        val elemento = elementParent.ejercicios[position]
        holder.bindData(elemento)
    }

    override fun getItemCount(): Int {
        return elementParent.ejercicios.size
    }

}

