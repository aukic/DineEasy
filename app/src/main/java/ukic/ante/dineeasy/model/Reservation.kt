package ukic.ante.dineeasy.model

import android.os.Parcelable
import com.google.type.DateTime
import kotlinx.parcelize.Parcelize
import java.sql.Date
import java.sql.Time

@Parcelize
class Reservation(var restaurantName: String, var timeOfArrival: Time, var reservedTable: String, var dateOfArrival: Date):Parcelable
 {
}