package id.kotlin.seatravel.recycleviewTiketKamar

import id.kotlin.seatravel.recycleviewTiketKapal.tiketkapalAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface API_tiketkamar {
    @GET("/rest/v1/book_kamar?select=*")
    suspend fun get(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String
    ) : Response<List<tiketkamarAPI>>
}