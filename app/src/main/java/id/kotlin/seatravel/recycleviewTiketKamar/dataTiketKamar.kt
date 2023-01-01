package id.kotlin.seatravel.recycleviewTiketKamar

import id.kotlin.seatravel.BookKamar

data class dataTiketKamar(
    val id : String,
    val nama : String,
    val kamar: String,
    val jmlKamar : String,
    val checkin : String,
    val checkout : String
)
