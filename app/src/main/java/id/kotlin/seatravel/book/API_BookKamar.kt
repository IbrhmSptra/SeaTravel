package id.kotlin.seatravel.book

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface API_BookKamar {

    @POST("/rest/v1/book_kamar")
    suspend fun createtiketkamar(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Body data : bookkamarAPI
    )

}