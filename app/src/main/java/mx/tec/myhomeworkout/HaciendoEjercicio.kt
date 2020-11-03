package mx.tec.myhomeworkout


import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ViewGroup
import android.net.Uri
import android.os.Build
import android.view.View
import android.webkit.URLUtil
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_haciendo_ejercicio.*
import mx.tec.myhomeworkout.fragments.MonitoreoProgresoGraficasFragment
import mx.tec.myhomeworkout.fragments.PaginaInicialFragment
import mx.tec.myhomeworkout.fragments.ProfileActFragment
import java.util.*

class HaciendoEjercicio : AppCompatActivity() {
    val videoNames = arrayOf("rutina1", "rutina2", "rutina3", "rutina4")
    var itera = 0
    var i = 0
    var play = false
    var millisInFuture: Long = 15000 //tiempo descendente
    var countDownInterval: Long = 1000 //cada segundo

    //contador
    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haciendo_ejercicio)

        val dayArray =
            resources.getStringArray(R.array.dayArray)//tomamos los valores que tenemos en los recursos
        //val btnQuestion=findViewById<Button>(R.id.btnQuestion)


        val calendar = Calendar.getInstance(TimeZone.getDefault())
        //getTime() returns the current date in default time zone
        //getTime() returns the current date in default time zone
        val date = calendar.time
        val day = calendar[Calendar.DATE]

        val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK)
        var todayIs: String = ""

        if (findViewById<ViewGroup>(R.id.layInfo) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            //al hacer clic abre un cuadro de diálogo con 3 nuevos botones
            btnQuestion.setOnClickListener {
                //No supe de qué otra forma hacer esto
                //Con el mismo botón abre la info y sale de la info
                replaceFragment(InfoFragment())
            }

            //Depende el "dayOfWeek" se asigna un día
            //"dayOfWeek" está dado en numérico, por eso el "when"
            when (dayOfWeek) {
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

            //Asigno información a los txtView
            txtDay.setText(todayIs)
            txtTiempo.setText("30" + " " + "Minutos")
            txtExercise.setText("FULL-BODY")
            //txtTiempoFaltante.setText("29")
        }

        //COUNTDOWN con boton pausa:
        //https://android--code.blogspot.com/2018/04/android-kotlin-countdowntimer-start.html


        //Con un mismo botón: inicio, pause, resumo
        btnPause.setOnClickListener {
            if (itera == 0) {// Start the timer
                timer(millisInFuture, countDownInterval).start()
                //it.isEnabled = false
                btnPause.setImageResource(R.drawable.pause)
                isCancelled = false
                isPaused = false
            } else if (itera % 2 != 0) {
                isPaused = true
                isCancelled = false
                btnPause.setImageResource(R.drawable.play)
                //it.isEnabled = false
            } else {
                // Resume the timer
                timer(resumeFromMillis, countDownInterval).start()
                isPaused = false
                isCancelled = false
                btnPause.setImageResource(R.drawable.pause)
                //it.isEnabled = false
            }
            itera++
        }

        val controller = MediaController(this)
        controller.setMediaPlayer(videoView)
        videoView.setMediaController(controller)
    }

    //Función para cargar varios fragments
    //Recibe: Fragment
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layInfo, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    // Method to configure and return an instance of CountDownTimer object
    private fun timer(millisInFuture: Long, countDownInterval: Long): CountDownTimer {
        var countDownTimer: CountDownTimer
        countDownTimer =
            return object : CountDownTimer(millisInFuture, countDownInterval) {
                override fun onTick(millisUntilFinished: Long) {
                    //val timeRemaining = "Tiempo Faltante\n${millisUntilFinished/1000}"
                    val timeRemaining = "${millisUntilFinished / 1000} Seg"

                    if (isPaused) {
                        //txtTiempoFaltante.text = "${txtTiempoFaltante.text}\nPausa"
                        txtTiempoFaltante.text = "${txtTiempoFaltante.text}"
                        // To ensure start timer from paused time
                        resumeFromMillis = millisUntilFinished
                        cancel()
                    } else {
                        txtTiempoFaltante.text = timeRemaining
                    }
                }

                override fun onFinish() {
                    txtTiempoFaltante.text = "Done"
                    //replaceContenidoFragment(InfoFragment())
                }
            }

    }

    private fun getURI(videoname: String): Uri {
        if (URLUtil.isValidUrl(videoname)) {
            //  an external URL
            return Uri.parse(videoname);
        } else { //  a raw resource
            return Uri.parse(
                "android.resource://" + getPackageName() +
                        "/raw/" + videoname
            );
        }
    }

    fun changeVideo(view: View) {
        if (i + 1 >= videoNames.size) {
            val intent = Intent(this@HaciendoEjercicio, PosRutina::class.java)
            startActivity(intent)
        } else {
            i++
            initPlayer()
        }

    }

    fun changeVideoBack(view: View) {
        if (i - 1 >= 0) {
            i--
            initPlayer()

        }
    }

    private fun initPlayer() {
        var videoUri: Uri = getURI(videoNames[i])
        videoView.setVideoURI(videoUri)
        videoView.setMediaController(null)
        videoView.setOnPreparedListener { mp -> mp.isLooping = true }
        videoView.start()
    }

    private fun releasePlayer() {
        videoView.stopPlayback()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onStart() {
        super.onStart()
        initPlayer()
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoView.pause()
        }
    }

}