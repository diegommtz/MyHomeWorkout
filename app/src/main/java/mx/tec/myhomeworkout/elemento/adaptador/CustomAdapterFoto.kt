package mx.tec.myhomeworkout.elemento.adaptador

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mx.tec.myhomeworkout.R
import mx.tec.myhomeworkout.model.Foto
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.util.*

class CustomAdapterFoto(
    private val context: Context,
    private val layout: Int,
    private val dataSource: List<Foto>,
    private val animation: Int
)
    : RecyclerView.Adapter<CustomAdapterFoto.ElementoViewHolder>() {

    inner class ElementoViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        layout: Int
    )
        : RecyclerView.ViewHolder(inflater.inflate(layout, parent, false)){

        //Viewholder es la clase que se encarga de manipular los controles de los elementos
        var imagenFrente: ImageView? = null
        var imagenLado: ImageView? = null
        var imagenEspalda: ImageView? = null
        var txtFecha: TextView? = null

        init{
            imagenFrente = itemView.findViewById(R.id.imgFront)
            imagenLado = itemView.findViewById(R.id.imgSide)
            imagenEspalda = itemView.findViewById(R.id.imgBack)
            txtFecha = itemView.findViewById(R.id.txtFecha)
        }

        //establece los valores
        fun bindData(elemento: Foto){

            val sc = elemento.fecha?.get("_seconds")?.asLong
            val fechaStr = sc?.let { getDate(it, "dd/MM/yyyy") }

            txtFecha!!.text = fechaStr
            Picasso.get().load(elemento.fotoDerecha).into(imagenLado)
            Picasso.get().load(elemento.fotoEspalda).into(imagenEspalda)
            Picasso.get().load(elemento.fotoFrontal).into(imagenFrente)
        }
    }

    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds * 1000
        return formatter.format(calendar.time)
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