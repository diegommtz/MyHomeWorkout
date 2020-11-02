package mx.tec.myhomeworkout.fragments

    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import mx.tec.myhomeworkout.R

    class MarcoEspalda: Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_marco_espalda, container, false)
            return view
        }
    }