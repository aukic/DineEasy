package ukic.ante.dineeasy.model

import com.google.type.DateTime
import java.sql.Date
import java.sql.Time

class Reservation(
    val restaurantName: String,
    val timeOfArrival: Time,
    val reservedTable: String,
    val dateOfArrival: Date
) {

}