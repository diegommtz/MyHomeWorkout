package mx.tec.myhomeworkout.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import mx.tec.myhomeworkout.R


class ProfileActFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.activity_profile, container, false)

        // Here notify the fragment that it should participate in options menu handling.
        setHasOptionsMenu(true);
        return view
    }

    //el menu carga
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // First clear current all the menu items
        menu.clear()
        // Add the new menu items
        inflater.inflate(R.menu.menu_perfil, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //el menu ejecuta
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }


}
