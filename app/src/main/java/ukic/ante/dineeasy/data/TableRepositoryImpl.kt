package ukic.ante.dineeasy.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import ukic.ante.dineeasy.model.Reservation
import java.util.*


class TableRepositoryImpl(): TableRepository {
    private var databaseRef = FirebaseDatabase.getInstance().reference.child("Tablica").child("Datum1")
    override fun getAllTables(reservation: Reservation): LiveData<List<String>> {
        var calendar = Calendar.getInstance()
        calendar.time = reservation.dateOfArrival
        databaseRef = databaseRef.child(calendar[Calendar.DAY_OF_MONTH].toString()).child(reservation.restaurantName)
        val tableList= mutableListOf<String>()
        val tableListLiveData: MutableLiveData<List<String>> = MutableLiveData<List<String>>()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tables = dataSnapshot.children
                for (table in tables){
                    val tableState = table.child("Slobodan").value.toString()

                    tableList.add(tableState)
                }

                tableListLiveData.value = tableList
            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }
        databaseRef.addValueEventListener(postListener)
        return tableListLiveData
    }
}