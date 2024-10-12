package com.example.stockviewapp

import com.google.gson.annotations.SerializedName

// Main data class for stock data response
data class StockData(
    @SerializedName("Meta Data")
    val metaData: MetaData?,   // Metadata of the stock
    @SerializedName("Time Series (Daily)")
    val timeSeries: Map<String, TimeSeriesData>?  // Daily time series data
)

// Metadata about the stock query
data class MetaData(
    @SerializedName("1. Information")
    val information: String?,
    @SerializedName("2. Symbol")
    val symbol: String?,
    @SerializedName("3. Last Refreshed")
    val lastRefreshed: String?,
    @SerializedName("4. Output Size")
    val outputSize: String?,
    @SerializedName("5. Time Zone")
    val timeZone: String?
)

// Daily stock price and volume data
data class TimeSeriesData(
    @SerializedName("1. open")
    val open: String?,
    @SerializedName("2. high")
    val high: String?,
    @SerializedName("3. low")
    val low: String?,
    @SerializedName("4. close")
    val close: String?,
    @SerializedName("5. volume")
    val volume: String?
)
