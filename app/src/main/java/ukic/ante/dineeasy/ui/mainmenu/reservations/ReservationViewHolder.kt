package ukic.ante.dineeasy.ui.mainmenu.reservations

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ukic.ante.dineeasy.databinding.FragmentReservationCellBinding
import ukic.ante.dineeasy.model.Reservation

class ReservationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(reservation: Reservation){
        val binding = FragmentReservationCellBinding.bind(itemView)
        binding.tvName.text = reservation.restaurantName
        binding.tvTable.text = reservation.reservedTable
        binding.tvDate.text = reservation.dateOfArrival.toString()
        binding.tvTime.text = reservation.timeOfArrival.toString()
    }
}