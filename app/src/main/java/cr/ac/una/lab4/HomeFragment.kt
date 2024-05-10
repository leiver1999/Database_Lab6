package cr.ac.una.lab4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import cr.ac.una.lab4.dao.MovimientoDAO
import cr.ac.una.lab4.db.AppDatabase
import cr.ac.una.lab4.entities.Movimiento
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 **/
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null


    private lateinit var movimientos: List<Movimiento>
    private lateinit var adapter: MovimientoAdapter
    private lateinit var listView: ListView

    private lateinit var vista: MovimientoService
    private lateinit var movimientoDao: MovimientoDAO


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        vista = MovimientoService(requireContext())

        movimientoDao = vista.movimientoDAO

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        listView = view.findViewById(R.id.listaMovimientos)



        lifecycleScope.launch {
            withContext(Dispatchers.Default) {

                movimientos = movimientoDao.getAll() as List<Movimiento>

                Log.d("movimientos", movimientos.toString())

                adapter = MovimientoAdapter(
                    requireContext(),
                    movimientos,
                    { movimiento ->

                        deleteMovimiento(movimiento)

                        parentFragmentManager.beginTransaction()
                            .replace(R.id.home_content, HomeFragment())
                            .commit()

                    }, { movimiento ->


                        parentFragmentManager.beginTransaction()
                            .replace(R.id.home_content, CameraFragment.newInstance(movimiento))
                            .commit()
                    }
                )

                withContext(Dispatchers.Main) {
                    listView.adapter = adapter
                }
            }

        }

        val botonNuevo = view.findViewById<Button>(R.id.botonNuevo)

        botonNuevo.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.home_content, CameraFragment())
                .commit()
        }



        return view

    }

    private fun deleteMovimiento(movimiento: Movimiento) {
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Default) {
                movimientoDao.delete(movimiento)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.

         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String) =
//            HomeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                }
//            }
    }
}