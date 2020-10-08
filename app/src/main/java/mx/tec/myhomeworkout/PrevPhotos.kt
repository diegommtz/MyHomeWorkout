package mx.tec.myhomeworkout

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_prev_photos.*
import java.lang.Exception

class PrevPhotos : AppCompatActivity() {

    val selectPhoto: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prev_photos)

        pickerFoto.setOnClickListener{
            val i = Intent(Intent.ACTION_PICK)
            i.type = "image/*"
            startActivityForResult(i, selectPhoto)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == selectPhoto && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val uri = data.data
            try {
                val bitMap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                imagen.setImageBitmap(bitMap)
            } catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
    }
}