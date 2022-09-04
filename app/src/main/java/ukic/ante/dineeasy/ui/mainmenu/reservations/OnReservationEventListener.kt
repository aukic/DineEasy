package ukic.ante.dineeasy.ui.mainmenu.reservations

import ukic.ante.dineeasy.model.Reservation

interface OnReservationEventListener {
    fun onReservationSelected(reservation: Reservation?)
    fun fillRecycler()
}