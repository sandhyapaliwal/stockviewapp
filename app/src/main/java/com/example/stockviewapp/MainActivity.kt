package com.example.stockviewapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : ComponentActivity() {
    private val viewModel: StockViewModel by viewModels()

    private lateinit var searchButton: Button
    private lateinit var stockSymbolInput: EditText
    private lateinit var stockNameText: TextView
    private lateinit var stockPriceText: TextView
    private lateinit var stockChangeText: TextView
    private lateinit var errorMessageText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        searchButton = findViewById(R.id.searchButton)
        stockSymbolInput = findViewById(R.id.stockSymbolEditText)
        stockNameText = findViewById(R.id.stockInfoTextView)
        stockPriceText = findViewById(R.id.stock_price_text)
        stockChangeText = findViewById(R.id.stock_change_text)
        errorMessageText = findViewById(R.id.error_message_text)

        // Initially hide stock price and percentage change
        stockPriceText.visibility = TextView.GONE
        stockChangeText.visibility = TextView.GONE

        // Observe stock data when the activity is created
        observeStockData()

        // Set click listener for the search button
        searchButton.setOnClickListener {
            // Clear previous error message and stock info
            errorMessageText.text = ""
            stockNameText.text = ""
            stockPriceText.text = ""
            stockChangeText.text = ""
            stockPriceText.visibility = TextView.GONE
            stockChangeText.visibility = TextView.GONE

            val stockSymbol = stockSymbolInput.text.toString().trim()
            if (stockSymbol.isNotEmpty()) {
                viewModel.fetchStockData(stockSymbol) // Fetch data for entered symbol
            } else {
                showErrorMessage("Please enter a valid stock symbol")
            }
        }
    }

    private fun observeStockData() {
        viewModel.stockData.observe(this) { stockData ->
            Log.d("MainActivity", "Received Stock Data: $stockData")
            if (stockData != null) {
                val latestEntry = stockData.timeSeries?.entries?.firstOrNull()?.value
                val openPrice = latestEntry?.open?.toDoubleOrNull()
                val closePrice = latestEntry?.close?.toDoubleOrNull()


                if (openPrice != null && closePrice != null) {
                    stockNameText.text = stockData.metaData?.symbol ?: "Unknown Symbol"
                    stockPriceText.text = "Price: $closePrice"
                    stockPriceText.visibility = TextView.VISIBLE // Show price TextView

                    // Calculate the percentage change
                    val percentageChange = ((closePrice - openPrice) / openPrice) * 100
                    stockChangeText.text = "Percentage Change: %.2f%%".format(percentageChange)
                    stockChangeText.visibility = TextView.VISIBLE // Show percentage change TextView

                    // Set color based on percentage change
                    val changeColor = if (percentageChange >= 0) {
                        getColor(R.color.green) // Green for positive change
                    } else {
                        getColor(R.color.red) // Red for negative change
                    }
                    stockChangeText.setTextColor(changeColor)

                    // Clear error message
                    errorMessageText.text = ""
                } else {
                    showErrorMessage("Data unavailable for calculation.")
                }
            } else {
                // This case will trigger when stockData is null, indicating an error
                showErrorMessage("Failed to fetch stock data. Please try again.")
            }
        }
    }

    private fun showErrorMessage(message: String) {
        errorMessageText.text = message
        stockNameText.text = "" // Clear stock name
        stockPriceText.text = "" // Clear stock price
        stockChangeText.text = "" // Clear stock change
        stockPriceText.visibility = TextView.GONE // Hide price TextView
        stockChangeText.visibility = TextView.GONE // Hide percentage change TextView
    }
}
