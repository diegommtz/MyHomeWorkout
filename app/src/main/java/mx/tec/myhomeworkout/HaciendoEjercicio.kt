package mx.tec.myhomeworkout


import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_haciendo_ejercicio.*
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*

class HaciendoEjercicio : AppCompatActivity() {
    private val VIDEO_NAME = "cuphead"
    var itera=0

    //contador
    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haciendo_ejercicio)

        val dayArray = resources.getStringArray(R.array.dayArray)//tomamos los valores que tenemos en los recursos
        //val btnQuestion=findViewById<Button>(R.id.btnQuestion)


        val calendar = Calendar.getInstance(TimeZone.getDefault())
        //getTime() returns the current date in default time zone
        //getTime() returns the current date in default time zone
        val date = calendar.time
        val day = calendar[Calendar.DATE]

        val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK)
        val dayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val dayOfYear: Int = calendar.get(Calendar.DAY_OF_YEAR)
        var todayIs:String=""

        //INFO OBTENIDA DE:
        //https://cursokotlin.com/capitulo-22-fragments-en-kotlin/
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById<ViewGroup>(R.id.layInfo) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            //Para Init Fragments
            fragmentTransaction.add(R.id.layInfo, VideoFragment())
            fragmentTransaction.commit()

            //al hacer clic abre un cuadro de diálogo con 3 nuevos botones
            btnQuestion.setOnClickListener {
                //No supe de qué otra forma hacer esto
                //Con el mismo botón abre la info y sale de la info
                itera++

                if (itera % 2 != 0) {
                    replaceFragment(InfoFragment())
                } else {
                    replaceFragment(VideoFragment())
                }

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

        /*
        var time=20.toLong()
        val timer = object: CountDownTimer(time*1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txtTiempoFaltante.setText(time--.toString())
            }

            override fun onFinish() {txtTiempoFaltante.setText("time out")}
        }
        timer.start()
         */

        //COUNTDOWN con boton pausa:
        //https://android--code.blogspot.com/2018/04/android-kotlin-countdowntimer-start.html
        val millisInFuture:Long = 15000 //tiempo descendente
        val countDownInterval:Long = 1000 //cada segundo

        //Con un mismo botón: inicio, pause, resumo
        btnPause.setOnClickListener{
            if(itera==0){// Start the timer
                timer(millisInFuture, countDownInterval).start()
                //it.isEnabled = false
                btnPause.setImageResource(R.drawable.pause)
                isCancelled = false
                isPaused = false}
            else if(itera%2!=0) {
                isPaused = true
                isCancelled = false
                btnPause.setImageResource(R.drawable.play)
                //it.isEnabled = false
            }
            else{
                // Resume the timer
                timer(resumeFromMillis,countDownInterval).start()
                isPaused = false
                isCancelled = false
                btnPause.setImageResource(R.drawable.pause)
                //it.isEnabled = false
            }
            itera++
        }
    }

    //Función para cargar varios fragments
    //Recibe: Fragment
    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layInfo, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun replaceContenidoFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layContenido, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    // Method to configure and return an instance of CountDownTimer object
    private fun timer(millisInFuture:Long,countDownInterval:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                //val timeRemaining = "Tiempo Faltante\n${millisUntilFinished/1000}"
                val timeRemaining = "${millisUntilFinished/1000} Seg"

                if (isPaused){
                    //txtTiempoFaltante.text = "${txtTiempoFaltante.text}\nPausa"
                    txtTiempoFaltante.text = "${txtTiempoFaltante.text}"
                    // To ensure start timer from paused time
                    resumeFromMillis = millisUntilFinished
                    cancel()
                }
                else{
                    txtTiempoFaltante.text = timeRemaining
                }
            }

            override fun onFinish() {
                txtTiempoFaltante.text = "Done"
                //replaceContenidoFragment(InfoFragment())
            }
        }

    }

}