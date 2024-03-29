package ukic.ante.dineeasy.ui.mainmenu.reservations

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ukic.ante.dineeasy.R
import ukic.ante.dineeasy.databinding.FragmentReservationCellBinding
import ukic.ante.dineeasy.model.Reservation
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReservationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(reservation: Reservation){
        val binding = FragmentReservationCellBinding.bind(itemView)
        binding.tvName.text = reservation.restaurantName
        binding.tvTable.text = reservation.reservedTable
        binding.tvDate.text = reservation.dateOfArrival.toString()
        binding.tvTime.text = reservation.timeOfArrival.toString()

        when (reservation.restaurantName) {
            "Bistro Šalša" -> {
                binding.ivImage.setImageResource(R.drawable.salsa)
            }
            "Haustor Haus" -> {
                binding.ivImage.setImageResource(R.drawable.haustor)
            }
            "Lari&Penati" -> {
                binding.ivImage.setImageResource(R.drawable.lari)
            }
            "Mali Bar" -> {
                binding.ivImage.setImageResource(R.drawable.malibar)
            }
            "Plac" -> {
                binding.ivImage.setImageResource(R.drawable.plac)
            }
            "RougeMarin" -> {
                binding.ivImage.setImageResource(R.drawable.rouge)
            }
        }
    }
}