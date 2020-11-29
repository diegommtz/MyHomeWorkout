package mx.tec.myhomeworkout

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.android.synthetic.main.activity_prev_photos.*
import kotlinx.android.synthetic.main.activity_prev_photos.btnNext
import mx.tec.myhomeworkout.model.Persona
import mx.tec.myhomeworkout.services.IPersona
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PrevPhotos : AppCompatActivity() {
    val selectFile: Int = 0
    val requestCamera = 1
    var index = 0;
    val imagesTitles = arrayListOf(R.string.foto_frontal, R.string.foto_espalda, R.string.foto_perfil)
    val imageList = ArrayList<SlideModel>() // Create image list. https://github.com/denzcoskun/ImageSlideshow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prev_photos)

        if(savedInstanceState != null) return

        //replaceFragment(MarcoFrente(), R.string.foto_frontal)

        tvAltura.setText(imagesTitles[0])

        imageList.add(SlideModel(R.drawable.img_frente, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.img_espalda, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.img_perfil, ScaleTypes.CENTER_CROP))

        slider.setImageList(imageList)

        slider.setItemChangeListener(object : ItemChangeListener{
            override fun onItemChanged(position: Int) {
                index = position;
                tvAltura.setText(imagesTitles[index])
            }
        })

        pickerFoto.setOnClickListener{
            SelectImage()
        }

        btnNext.setOnClickListener {

            //TODO: POST DE HORARIO


            //TODO: POST DE PERSONA
            val persona = intent.getSerializableExtra("Persona") as? Persona

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(IPersona::class.java)

            service.createPersona(persona).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    t.message?.let { Log.e("RESTLIBS", it) }
                }

                override fun onResponse(call: Call<String>, response: retrofit2.Response<String>) {
                    val intent = Intent(this@PrevPhotos, PaginaInicial::class.java)
                    Toast.makeText(this@PrevPhotos, "¡Tu perfil se ha creado!", Toast.LENGTH_SHORT).show()
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            })
            //--------------
        }

        /*
        btnEspalda.setOnClickListener {
            replaceFragment(MarcoEspalda(), R.string.foto_espalda)
        }
        btnFrente.setOnClickListener {
            replaceFragment(MarcoFrente(), R.string.foto_frontal)
        }
        btnPerfil.setOnClickListener {
            replaceFragment(MarcoPerfil(), R.string.foto_perfil)
        }
        * */
    }

    /*
    private fun replaceFragment(fragment:Fragment, idText: Int){
        tvFotoHint.setText(idText)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //fragmentTransaction.replace(R.id.marco, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }*/

    fun SelectImage()
    {
        val items = arrayOf("Camera", "Gallery", "Cancel")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Elige una opción")
        builder.setItems(items) { dialog, which ->
            when(items[which]) {
                "Camera" -> {
                    val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(i, requestCamera)
                }
                "Gallery" -> {
                    val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    i.type = "image/*"
                    startActivityForResult(Intent.createChooser(i, "Selecciona una imagen"), selectFile)
                }
                "Cancel" -> {
                    dialog.dismiss()
                }
            }
        }
        builder.show()
     }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK)
        {
            if(requestCode == requestCamera){
                val bundle: Bundle = data?.extras!!
                val bmp: Bitmap = bundle.get("data") as Bitmap
                //imagen.setImageBitmap(bmp)
                //imageList[index] = SlideModel(bmp, "Foto cambiada")

            }
            else if (requestCode == selectFile)
            {
                val uri = data?.data
                imageList[index] = SlideModel(uri.toString())
                slider.setImageList(imageList)
            }
        }
    }
}