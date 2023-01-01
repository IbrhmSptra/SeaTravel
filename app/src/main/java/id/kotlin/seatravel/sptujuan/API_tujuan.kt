package id.kotlin.seatravel.sptujuan


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface API_tujuan {
    @GET("/rest/v1/tujuan?select=*")
    suspend fun get(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String
    ) : Response<List<dataTujuan>>
}