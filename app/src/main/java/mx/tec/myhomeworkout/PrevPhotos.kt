package mx.tec.myhomeworkout

import android.app.ActionBar
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.android.synthetic.main.activity_nacimiento.*
import kotlinx.android.synthetic.main.activity_prev_photos.*
import kotlinx.android.synthetic.main.activity_prev_photos.btnNext
import mx.tec.myhomeworkout.fragments.MarcoEspalda
import mx.tec.myhomeworkout.fragments.MarcoFrente
import mx.tec.myhomeworkout.fragments.MarcoPerfil
import java.lang.Exception

class PrevPhotos : AppCompatActivity() {

    val selectFile: Int = 0
    val requestCamera = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prev_photos)

        if(savedInstanceState != null) return

        //replaceFragment(MarcoFrente(), R.string.foto_frontal)

        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel(R.drawable.frontal, "Foto de frente"))
        imageList.add(SlideModel(R.drawable.frontal, "Foto de perfil"))
        imageList.add(SlideModel(R.drawable.frontal, "Foto de espalda", ScaleTypes.CENTER_INSIDE))

        slider.setImageList(imageList)

        slider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                Toast.makeText(this@PrevPhotos, "¡CLICK!", Toast.LENGTH_SHORT).show()
                imageList[position] = SlideModel(R.drawable.logo, "Foto cambiada")
                slider.setImageList(imageList)
            }
        })

        pickerFoto.setOnClickListener{
            SelectImage()
        }

        btnNext.setOnClickListener {
            val intent = Intent(this@PrevPhotos, PaginaInicial::class.java)
            Toast.makeText(this@PrevPhotos, "¡Tu perfil se ha creado!", Toast.LENGTH_SHORT).show()
            startActivity(intent)
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

    private fun replaceFragment(fragment:Fragment, idText: Int){
        tvFotoHint.setText(idText)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //fragmentTransaction.replace(R.id.marco, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

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
                imagen.setImageBitmap(bmp)
            }
            else if (requestCode == selectFile)
            {
                val uri = data?.data
                imagen.setImageURI(uri)
            }
        }
    }
}