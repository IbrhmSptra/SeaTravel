package id.kotlin.seatravel.recycleviewTiketKapal

import id.kotlin.seatravel.spinnerAsal.dataAsal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface API_tiketkapal {
    @GET("/rest/v1/book_kapal?select=*")
    suspend fun get(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String
    ) : Response<List<tiketkapalAPI>>
}