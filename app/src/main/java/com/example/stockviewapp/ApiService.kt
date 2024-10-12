package com.example.stockviewapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService {
    @GET("query")
    suspend fun getStockData(
        @Query("function") function: String = "TIME_SERIES_DAILY", // API function for daily stock data
        @Query("symbol") symbol: String, // Stock symbol
        @Query("apikey") apiKey: String = "YOUR_API_KEY"
    ): Response<StockData>

    companion object {
        private const val BASE_URL = "https://www.alphavantage.co" // API base URL

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Converts JSON to Kotlin objects
                .build()
                .create(ApiService::class.java)
        }
    }
}
