package mx.tec.myhomeworkout.elemento.adaptador

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import mx.tec.myhomeworkout.R
import mx.tec.myhomeworkout.model.Ejercicio


class CustomAdapterSimpleExercise(
    private val context: Context,
    private val layout: Int,
    private val dataSource: List<Ejercicio>,
    private val animation: Int
)
    : RecyclerView.Adapter<CustomAdapterSimpleExercise.ElementoViewHolder>(){

    inner class ElementoViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        layout: Int
    )
        : RecyclerView.ViewHolder(inflater.inflate(layout, parent, false)){

        //Viewholder es la clase que se encarga de manipular los controles de los elementos
        var imagen: ImageView? = null
        final var video: VideoView? = null
        var nombre: TextView? = null

        init{
            imagen = itemView.findViewById(R.id.ivSimpleExercise)
            video = itemView.findViewById(R.id.vvSimpleExercise)
            nombre = itemView.findViewById(R.id.tvSimpleExercise)
        }

        //establece los valores
        fun bindData(elemento: Ejercicio){
            //imagen!!.setImageResource(elemento.imagen)
            nombre!!.text = elemento.nombre
            val onlineUri = Uri.parse(elemento.video)
            video!!.setVideoURI(onlineUri)
            video!!.setMediaController(null)
            video!!.setOnPreparedListener { mp ->
                mp.isLooping = true
                video!!.start()
            }
            video!!.setOnCompletionListener {
                it.isLooping = true
                video!!.start()
            }
            video!!.start()
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

