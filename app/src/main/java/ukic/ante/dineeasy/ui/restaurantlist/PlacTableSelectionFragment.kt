package ukic.ante.dineeasy.ui.restaurantlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ukic.ante.dineeasy.data.TableRepositoryImpl
import ukic.ante.dineeasy.databinding.FragmentTableSelectionPlacBinding
import ukic.ante.dineeasy.model.Reservation

class PlacTableSelectionFragment: Fragment() {
    private lateinit var binding: FragmentTableSelectionPlacBinding
    private var tableRepository = TableRepositoryImpl()
    private var greenTables = mutableListOf<TextView>()
    private var redTables = mutableListOf<TextView>()
    private lateinit var reservation: Reservation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTableSelectionPlacBinding.inflate(layoutInflater)

        reservation = PlacTableSelectionFragmentArgs.fromBundle(requireArguments()).reservation
        setUpTables()
        tableRepository.getAllTables(reservation).observe(viewLifecycleOwner){
            var counter = 0
            for (i in it){
                if (i == "NE") {
                    greenTables[counter].visibility = View.INVISIBLE
                    redTables[counter].visibility = View.VISIBLE
                }
                counter++
            }
        }
        binding.table1.setOnClickListener { reserveTable("Stol1") }
        binding.table2.setOnClickListener { reserveTable("Stol2") }
        binding.table3.setOnClickListener { reserveTable("Stol3") }
        binding.table4.setOnClickListener { reserveTable("Stol4") }
        binding.table5.setOnClickListener { reserveTable("Stol5") }
        binding.table6.setOnClickListener { reserveTable("Stol6") }
        binding.table7.setOnClickListener { reserveTable("Stol7") }
        return binding.root
    }

    private fun reserveTable(table: String) {
        reservation.reservedTable = table

        val action = PlacTableSelectionFragmentDirections.actionPlacTableSelectionFragmentToOrderConfirmationFragment(reservation)
        findNavController().navigate(action)
    }

    private fun setUpTables() {
        greenTables.add(binding.table1)
        greenTables.add(binding.table2)
        greenTables.add(binding.table3)
        greenTables.add(binding.table4)
        greenTables.add(binding.table5)
        greenTables.add(binding.table6)
        greenTables.add(binding.table7)

        redTables.add(binding.redtable1)
        redTables.add(binding.redtable2)
        redTables.add(binding.redtable3)
        redTables.add(binding.redtable4)
        redTables.add(binding.redtable5)
        redTables.add(binding.redtable6)
        redTables.add(binding.redtable7)
    }
}