package ukic.ante.dineeasy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.Query
import ukic.ante.dineeasy.model.Reservation

interface ReservationRepository {
    fun saveReservation(reservation:Reservation)
    fun deleteReservation(reservation: Reservation)
    fun getAllActiveReservations(): LiveData<List<Reservation>>
    fun getAllPastReservations(): LiveData<List<Reservation>>
}