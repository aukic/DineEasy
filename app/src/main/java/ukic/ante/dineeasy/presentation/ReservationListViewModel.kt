package ukic.ante.dineeasy.presentation

import androidx.lifecycle.ViewModel
import ukic.ante.dineeasy.data.ReservationRepository
import ukic.ante.dineeasy.model.Reservation

class ReservationListViewModel(
    val reservationRepository: ReservationRepository
): ViewModel() {
    val activeReservations = reservationRepository.getAllActiveReservations()
    val pastReservations = reservationRepository.getAllPastReservations()

    fun deleteReservation(reservation: Reservation){
        reservationRepository.deleteReservation(reservation)
    }
}