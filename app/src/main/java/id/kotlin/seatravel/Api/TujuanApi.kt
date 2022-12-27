package id.kotlin.seatravel.Api

import id.kotlin.seatravel.Data.Tujuan
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TujuanApi {
    @GET("/rest/v1/asal?select=*")
    suspend fun get(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String
    ) : Response<List<Tujuan>>
}