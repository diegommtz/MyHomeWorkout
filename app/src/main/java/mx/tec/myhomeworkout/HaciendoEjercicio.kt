package mx.tec.myhomeworkout


import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_haciendo_ejercicio.*
import java.util.*

class HaciendoEjercicio : AppCompatActivity() {
    private val VIDEO_NAME = "cuphead"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haciendo_ejercicio)

        val dayArray = resources.getStringArray(R.array.dayArray)//tomamos los valores que tenemos en los recursos

        val calendar = Calendar.getInstance(TimeZone.getDefault())
        //getTime() returns the current date in default time zone
        //getTime() returns the current date in default time zone
        val date = calendar.time
        val day = calendar[Calendar.DATE]

        val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK)
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val dayOfYear: Int = calendar.get(Calendar.DAY_OF_YEAR)
        var todayIs:String=""

        when(dayOfWeek) {
            1 -> todayIs = dayArray[0]
            2 -> todayIs = dayArray[1]
            3 -> todayIs = dayArray[2]
            4 -> todayIs = dayArray[3]
            5 -> todayIs = dayArray[4]
            6 -> todayIs = dayArray[5]
            7 -> todayIs = dayArray[6]
            else -> { // Note the block
                print("x ")
            }
        }

        txtDay.setText(todayIs)
        txtTiempo.setText("30"+""+"Minutos")
        txtExercise.setText("FULL-BODY")
        txtTiempoFaltante.setText("29")


        val controller = MediaController(this)
        controller.setMediaPlayer(videoView)
        videoView.setMediaController(controller)


    }

    private fun getURI(videoname:String): Uri{
        if (URLUtil.isValidUrl(videoname)) {
            //  an external URL
            return Uri.parse(videoname);
        } else { //  a raw resource
            return Uri.parse("android.resource://" + getPackageName() +
                    "/raw/" + videoname);
        }
    }

    private fun initPlayer() {
        var videoUri:Uri = getURI(VIDEO_NAME)
        videoView.setVideoURI(videoUri)
        videoView.start()
    }

    private fun releasePlayer(){
        videoView.stopPlayback()
    }

    protected override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    protected override fun onStart() {
        super.onStart()
        initPlayer()
    }

    protected override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoView.pause()
        }
    }

}