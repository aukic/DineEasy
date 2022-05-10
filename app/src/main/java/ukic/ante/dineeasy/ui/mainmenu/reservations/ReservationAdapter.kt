package ukic.ante.dineeasy.ui.mainmenu.reservations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ukic.ante.dineeasy.R
import ukic.ante.dineeasy.model.Reservation

class ReservationAdapter: RecyclerView.Adapter<ReservationViewHolder>() {

    private val reservations = mutableListOf<Reservation>()
    var onReservationEventListener: OnReservationEventListener? = null

    fun setReservations(reservations: List<Reservation>){
        this.reservations.clear()
        this.reservations.addAll(reservations)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_reservation_cell,parent,false)
        return ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.bind(reservation)

        onReservationEventListener?.let { listener->
            holder.itemView.setOnClickListener { listener.onReservationSelected(reservation) }
        }
    }

    override fun getItemCount(): Int {
        return reservations.count()
    }
}