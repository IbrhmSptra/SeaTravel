package id.kotlin.seatravel.spinnerAsal


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface API_asal {
    @GET("/rest/v1/asal?select=*")
    suspend fun get(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String
    ) : Response<List<dataAsal>>
}