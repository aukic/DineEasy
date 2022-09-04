package ukic.ante.dineeasy.data

import androidx.lifecycle.LiveData
import ukic.ante.dineeasy.model.Reservation

interface TableRepository {
    fun getAllTables(reservation: Reservation): LiveData<List<String>>
}