package mx.tec.myhomeworkout

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.Image
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import kotlinx.android.synthetic.main.activity_peso.*
import kotlinx.android.synthetic.main.activity_prev_photos.*
import kotlinx.android.synthetic.main.activity_prev_photos.btnNext
import mx.tec.myhomeworkout.model.Foto
import kotlinx.android.synthetic.main.activity_prev_photos.tvAltura
import mx.tec.myhomeworkout.model.HorarioModel
import mx.tec.myhomeworkout.model.Persona
import mx.tec.myhomeworkout.services.IFoto
import mx.tec.myhomeworkout.services.IHorario
import mx.tec.myhomeworkout.services.IPersona
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class PrevPhotos : AppCompatActivity() {

    val storage = FirebaseStorage.getInstance()
    val selectFile: Int = 0
    val requestCamera = 1
    var index = 0;
    val imagesTitles =
        arrayListOf(R.string.foto_frontal, R.string.foto_espalda, R.string.foto_perfil)
    val imageList =
        ArrayList<SlideModel>() // Create image list. https://github.com/denzcoskun/ImageSlideshow
    lateinit var uriStorage: Uri

    var frentePhotoUrl: String = ""
    var espaldaPhotoUrl: String= ""
    var ladoPhotoUrl: String= ""
    lateinit var idUsuario: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prev_photos)
        val sp = getSharedPreferences("mhw", Context.MODE_PRIVATE)

        if (savedInstanceState != null) return

        //replaceFragment(MarcoFrente(), R.string.foto_frontal)

        tvAltura.setText(imagesTitles[0])

        imageList.add(SlideModel(R.drawable.img_frente, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.img_espalda, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.img_perfil, ScaleTypes.CENTER_CROP))

        slider.setImageList(imageList)

        slider.setItemChangeListener(object : ItemChangeListener {
            override fun onItemChanged(position: Int) {
                index = position;
                tvAltura.setText(imagesTitles[index])
            }
        })

        pickerFoto.setOnClickListener {
            SelectImage()
        }

        btnNext.setOnClickListener {


            //POST DE PERSONA
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


                    val horario = intent.getSerializableExtra("Horario") as? HorarioModel
                    val retrofitHorario: Retrofit = Retrofit.Builder()
                        .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val serviceHorario = retrofitHorario.create(IHorario::class.java)

                    idUsuario = response.body()!!
                    val altura = persona?.altura

                    serviceHorario.createHorario(idUsuario, horario!!).enqueue(object :
                        Callback<String> {
                        override fun onFailure(call: Call<String>, t: Throwable) {
                            t.message?.let { Log.e("RESTLIBS", it) }
                        }

                        override fun onResponse(
                            call: Call<String>,
                            response: retrofit2.Response<String>
                        ) {
                            CreateFotos()
                        }
                    })


                }
            })

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

        // SET VISIBILITY ELEMENTS
        val invisible = intent.getStringExtra("invisible")

        if (invisible == "true"){
            //hide bext button
            btnNext.visibility = View.INVISIBLE
            //show OK button and send to home
            btnOk.visibility = View.VISIBLE
        }else{
            //show bext button
            btnNext.visibility = View.VISIBLE
            //hide OK button and send to home
            btnOk.visibility = View.INVISIBLE
        }
        btnOk.setOnClickListener {
            //TODO CRGAR FOTO A DB

            val intent = Intent(this@PrevPhotos, PaginaInicial::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
            Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    fun CreateFotos() {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://${getString(R.string.ipAddress)}:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(IFoto::class.java)

        val foto = Foto("", null, ladoPhotoUrl, frentePhotoUrl, espaldaPhotoUrl)

        service.CreateFoto(idUsuario, foto).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                val sp = getSharedPreferences("mhw", Context.MODE_PRIVATE)
                val intent = Intent(this@PrevPhotos, PaginaInicial::class.java)
                Toast.makeText(this@PrevPhotos, "¡Tu perfil se ha creado!", Toast.LENGTH_SHORT).show()
                with(sp.edit()) {
                    putString("idUsuario", idUsuario)
                    commit()
                }
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                t.message?.let { Log.e("RESTLIBS", it) }
            }

        })


    }

    fun UploadPhoto() {

        val randUuid = UUID.randomUUID()
        val path = "cuerpo/$randUuid.png"
        val firePathRef = storage.getReference(path)

        firePathRef.putFile(uriStorage)
            .addOnSuccessListener {
                val bucket: String = storage.reference.bucket
                val fileName: String = it.metadata?.reference?.name.toString()

                if(index == 0){
                    frentePhotoUrl = "https://firebasestorage.googleapis.com/v0/b/$bucket/o/cuerpo%2F$fileName?alt=media&token=$randUuid"
                    Log.e("entro", "aqui");
                }
                if(index == 1)
                    espaldaPhotoUrl = "https://firebasestorage.googleapis.com/v0/b/$bucket/o/cuerpo%2F$fileName?alt=media&token=$randUuid"
                if(index == 2)
                    ladoPhotoUrl = "https://firebasestorage.googleapis.com/v0/b/$bucket/o/cuerpo%2F$fileName?alt=media&token=$randUuid"

            }
            .addOnFailureListener{
                Log.e("Firebase", it.message.toString())
            }
    }

    /*
    private fun replaceFragment(fragment:Fragment, idText: Int){
        tvFotoHint.setText(idText)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //fragmentTransaction.replace(R.id.marco, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }*/

    fun SelectImage() {
        val items = arrayOf("Camera", "Gallery", "Cancel")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Elige una opción")
        builder.setItems(items) { dialog, which ->
            when (items[which]) {
                "Camera" -> {
                    val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(i, requestCamera)
                }
                "Gallery" -> {
                    val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    i.type = "image/*"
                    startActivityForResult(
                        Intent.createChooser(i, "Selecciona una imagen"),
                        selectFile
                    )
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
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == requestCamera) {
                val bundle: Bundle = data?.extras!!
                val bmp: Bitmap = bundle.get("data") as Bitmap
                //imagen.setImageBitmap(bmp)
                //imageList[index] = SlideModel(bmp, "Foto cambiada")

            } else if (requestCode == selectFile) {
                val uri = data?.data

                uriStorage = uri!!
                UploadPhoto()
                Log.e("UPDATED", "UPDATED")

                imageList[index] = SlideModel(uri.toString())
                slider.setImageList(imageList)
            }
        }
    }
}


