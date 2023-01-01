package id.kotlin.seatravel.book

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface API_BookKapal {

    //fungsi untuk booking tiket kapal submit to supabase
    @POST("/rest/v1/book_kapal")
    suspend fun createtiketkapal(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Body data : bookkapalAPI
    )
}