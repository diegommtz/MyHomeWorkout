package mx.tec.myhomeworkout.elemento.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_previs_rutina.*
import kotlinx.android.synthetic.main.layout_parent.view.*
import mx.tec.myhomeworkout.PrevisRutina
import mx.tec.myhomeworkout.R
import mx.tec.myhomeworkout.elemento.modelo.ElementParent

class CustomAdapterParent (private val context: Context,
                           private val layout: Int,
                           private val dataSource: List<ElementParent>,
                           private val animation:  Int)
    : RecyclerView.Adapter<CustomAdapterParent.ElementoViewHolder>(){
    private val viewPool = RecyclerView.RecycledViewPool()

    class ElementoViewHolder(inflater: LayoutInflater,
                             parent: ViewGroup,
                             layout: Int)
        : RecyclerView.ViewHolder(inflater.inflate(layout, parent, false)){


        //Viewholder es la clase que se encarga de manipular los controles de los elementos
        var nombre: TextView? = null
        var sets: TextView? = null
        var descanso: TextView? = null

        init{
            nombre = itemView.findViewById(R.id.tvNombreCircuito)
            sets = itemView.findViewById(R.id.tvCatidadSets)
            descanso = itemView.findViewById(R.id.tvDescansoPorSet)

        }

        //establece los valores
        fun bindData(elemento: ElementParent){
            nombre!!.text = elemento.nombre
            sets!!.text = elemento.sets
            descanso!!.text = elemento.descanso

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementoViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ElementoViewHolder(inflater, parent, layout)
    }

    override fun onBindViewHolder(holder: ElementoViewHolder, position: Int) {
        val elemento = dataSource[position]
        holder.bindData(elemento)
        val childLayoutManager = LinearLayoutManager(holder.itemView.rvHijo.context, RecyclerView.HORIZONTAL, false)

        holder.itemView.rvHijo.apply {
            layoutManager = childLayoutManager
            adapter = CustomAdapterChild(R.layout.layout_child, dataSource.get(position))
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

}

