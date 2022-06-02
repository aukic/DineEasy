package ukic.ante.dineeasy.data


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import ukic.ante.dineeasy.model.Reservation
import java.sql.Date
import java.sql.Time
import kotlin.collections.HashMap

class ReservationRepositoryImpl(): ReservationRepository {
    private var firebaseAuth: FirebaseAuth = Firebase.auth
    private var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var systemTime = System.currentTimeMillis()
    private var collectionReference: CollectionReference = firebaseFirestore.collection("Users")


    override fun saveReservation(reservation: Reservation) {
        collectionReference.document(firebaseAuth.uid.toString()).collection("Orders")
        val hashMap: HashMap<String,String> = HashMap()
        var timestamp: Long = reservation.dateOfArrival.time.plus(reservation.timeOfArrival.time)
        hashMap["Timestamp"] = timestamp.toString()
        hashMap["restaurantName"] = reservation.restaurantName
        hashMap["reservedTable"] = reservation.reservedTable
        hashMap["dateOfArrival"] = reservation.dateOfArrival.toString()
        hashMap["timeOfArrival"] = reservation.timeOfArrival.toString()

        collectionReference.document().set(hashMap)
    }

    override fun deleteReservation(reservation: Reservation) {

    }

    override fun getAllActiveReservations(): LiveData<List<Reservation>> {
        val reservationList= mutableListOf<Reservation>()
        val reservationListLiveData: MutableLiveData<List<Reservation>> = MutableLiveData<List<Reservation>>()
        collectionReference.document(firebaseAuth.uid.toString()).collection("Narudbe")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    for (document in it.result){
                        val reservedTable = document.data.getValue("reservedTable").toString()
                        val timeOfArrival = document.data.getValue("timeOfArrival").toString()
                        val restaurantName = document.data.getValue("restaurantName").toString()
                        val dateOfArrival = document.data.getValue("dateOfArrival").toString()
                        val timestamp = document.data.getValue("Timestamp").toString().toLong()
                        val convertedDate = Date.valueOf(dateOfArrival)
                        val convertedTime = Time.valueOf(timeOfArrival)
                        val reservation = Reservation(restaurantName,convertedTime,reservedTable,convertedDate)
                        if (timestamp > systemTime){
                            reservationList.add(reservation)
                            reservationListLiveData.value = reservationList
                        }
                    }
                }
            }
        return reservationListLiveData
    }

    override fun getAllPastReservations(): LiveData<List<Reservation>>  {
        val reservationList= mutableListOf<Reservation>()
        val reservationListLiveData: MutableLiveData<List<Reservation>> = MutableLiveData<List<Reservation>>()
        collectionReference.document(firebaseAuth.uid.toString()).collection("Narudbe")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    for (document in it.result){
                        val reservedTable = document.data.getValue("reservedTable").toString()
                        val timeOfArrival = document.data.getValue("timeOfArrival").toString()
                        val restaurantName = document.data.getValue("restaurantName").toString()
                        val dateOfArrival = document.data.getValue("dateOfArrival").toString()
                        val timestamp = document.data.getValue("Timestamp").toString().toLong()
                        val convertedDate = Date.valueOf(dateOfArrival)
                        val convertedTime = Time.valueOf(timeOfArrival)
                        val reservation = Reservation(restaurantName,convertedTime,reservedTable,convertedDate)
                        if (timestamp < systemTime){
                            reservationList.add(reservation)
                            reservationListLiveData.value = reservationList
                        }
                    }
                }
            }
        return reservationListLiveData
    }
}