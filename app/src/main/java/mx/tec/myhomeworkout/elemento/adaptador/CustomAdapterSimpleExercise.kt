package mx.tec.myhomeworkout.elemento.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tec.myhomeworkout.R
import mx.tec.myhomeworkout.elemento.modelo.ElementSimpleExercise

class CustomAdapterSimpleExercise (private val context: Context,
                                   private val layout: Int,
                                   private val dataSource: List<ElementSimpleExercise>,
                                   private val animation:  Int)
    : RecyclerView.Adapter<CustomAdapterSimpleExercise.ElementoViewHolder>(){

    class ElementoViewHolder(inflater: LayoutInflater,
                             parent: ViewGroup,
                             layout: Int)
        : RecyclerView.ViewHolder(inflater.inflate(layout, parent, false)){

        //Viewholder es la clase que se encarga de manipular los controles de los elementos
        var imagen: ImageView? = null
        var nombre: TextView? = null

        init{
            imagen = itemView.findViewById(R.id.ivSimpleExercise)
            nombre = itemView.findViewById(R.id.tvSimpleExercise)
        }

        //establece los valores
        fun bindData(elemento:ElementSimpleExercise){
            imagen!!.setImageResource(elemento.imagen)
            nombre!!.text = elemento.nombre
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementoViewHolder {
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

