package id.kotlin.seatravel.BatalkanPesanan

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.Query

interface API_Batalkan {
    @DELETE("/rest/v1/book_kapal")
    suspend fun deletetiketkapal(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Query("id") idquery : String
    ) : Response<Unit>

    @DELETE("/rest/v1/book_kamar")
    suspend fun deletetiketkamar(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Query("id") idquery : String
    ) : Response<Unit>
}