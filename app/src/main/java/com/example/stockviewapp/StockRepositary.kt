package com.example.stockviewapp.repository

import android.util.Log
import com.example.stockviewapp.StockData
import com.example.stockviewapp.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Response
import okhttp3.MediaType.Companion.toMediaType

class StockRepository {
    private val apiService = RetrofitInstance.api

    // Function to fetch stock data from the API
    suspend fun getStockData(symbol: String): Response<StockData> {
        return try {
            val apiKey = "YOUR_API_KEY"  // Your Alpha Vantage API key
            val response: Response<StockData> = apiService.getStockData(symbol = symbol, apiKey = apiKey)
            Log.d("StockRepository", "Raw Response: ${response.raw()}")

            if (response.isSuccessful) {
                val body = response.body()
                Log.d("StockRepository", "Parsed Response: $body")

                // Check if the response contains valid data
                if (body != null && body.metaData != null && body.timeSeries != null) {
                    Response.success(body)  // Return valid response
                } else {
                    Log.e("StockRepository", "Parsed response has null fields: metaData=${body?.metaData}, timeSeries=${body?.timeSeries}")
                    Response.error(500, createErrorResponse("Invalid response from API. Check the symbol or API key."))
                }
            } else {
                // Handle API error responses
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Log.e("StockRepository", "Error fetching stock data: $errorBody")
                Response.error(response.code(), createErrorResponse(errorBody))
            }
        } catch (e: Exception) {
            // Log and handle any exceptions
            Log.e("StockRepository", "Exception occurred: ${e.message}", e)
            Response.error(500, createErrorResponse("Exception occurred: ${e.message}"))
        }
    }

    // Helper function to create a custom error response
    private fun createErrorResponse(message: String): ResponseBody {
        return ResponseBody.create("text/plain".toMediaType(), message)
    }
}
