package com.example.stockviewapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockviewapp.repository.StockRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class StockViewModel : ViewModel() {
    private val repository = StockRepository() // Initialize the StockRepository

    private val _stockData = MutableLiveData<StockData?>() // LiveData to hold stock data
    val stockData: LiveData<StockData?> get() = _stockData  // Expose immutable LiveData

    // Function to fetch stock data based on the stock symbol
    fun fetchStockData(symbol: String) {
        viewModelScope.launch {
            try {
                val response = repository.getStockData(symbol)  // Call repository to get data
                if (response.isSuccessful) {
                    _stockData.postValue(response.body())
                } else {
                    Timber.e("Error fetching stock data: ${response.errorBody()?.string()}")
                    _stockData.postValue(null)  // Handle error by posting null
                }
            } catch (e: Exception) {
                Timber.e(e, "Exception while fetching stock data")
                _stockData.postValue(null) // Handle error by posting null
            }
        }
    }
}
