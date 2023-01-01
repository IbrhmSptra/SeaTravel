package id.kotlin.seatravel.spinnerKamar

import id.kotlin.seatravel.spinnerAsal.dataAsal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface API_kamar {
    @GET("/rest/v1/kamar?select=*")
    suspend fun get(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String
    ) : Response<List<dataspkamar>>
}