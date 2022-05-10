package ukic.ante.dineeasy.data


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
        collectionReference.document(firebaseAuth.uid.toString()).collection("Orders")
        val query: Query = collectionReference.orderBy("Timestamp",Query.Direction.DESCENDING).whereGreaterThan("Timestamp",systemTime)
        val reservationList:List<Reservation> = mutableListOf()
        val reservationListLiveData: MutableLiveData<List<Reservation>> = MutableLiveData<List<Reservation>>()
        query.addSnapshotListener { value, _ ->
            for (documentSnapshot: DocumentChange in value?.documentChanges!!){
                val reservedTable = documentSnapshot.document.get("reservedTable").toString()
                val timeOfArrival = documentSnapshot.document.get("timeOfArrival").toString()
                val restaurantName = documentSnapshot.document.get("restaurantName").toString()
                val dateOfArrival = documentSnapshot.document.get("dateOfArrival").toString()
                val convertedDate = Date.valueOf(dateOfArrival)
                val convertedTime = Time.valueOf(timeOfArrival)
                val reservation = Reservation(restaurantName,convertedTime,reservedTable,convertedDate)
                reservationList.plus(reservation)
                reservationListLiveData.value = reservationList
            }
        }

        return reservationListLiveData
    }

    override fun getAllPastReservations(): LiveData<List<Reservation>>  {
        collectionReference.document(firebaseAuth.uid.toString()).collection("Orders")
        val query: Query = collectionReference.orderBy("Timestamp",Query.Direction.DESCENDING).whereLessThan("Timestamp",systemTime)
        val reservationList:List<Reservation> = mutableListOf()
        val reservationListLiveData: MutableLiveData<List<Reservation>> = MutableLiveData<List<Reservation>>()
        query.addSnapshotListener { value, _ ->
            for (documentSnapshot: DocumentChange in value?.documentChanges!!){
                val reservedTable = documentSnapshot.document.get("reservedTable").toString()
                val timeOfArrival = documentSnapshot.document.get("timeOfArrival").toString()
                val restaurantName = documentSnapshot.document.get("restaurantName").toString()
                val dateOfArrival = documentSnapshot.document.get("dateOfArrival").toString()
                val convertedDate = Date.valueOf(dateOfArrival)
                val convertedTime = Time.valueOf(timeOfArrival)
                val reservation = Reservation(restaurantName,convertedTime,reservedTable,convertedDate)
                reservationList.plus(reservation)
                reservationListLiveData.value = reservationList
            }
        }
        return reservationListLiveData
    }
}