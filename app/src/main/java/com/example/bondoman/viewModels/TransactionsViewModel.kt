package com.example.bondoman.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bondoman.entities.Transaction
import com.example.bondoman.repositories.TransactionRepository
import kotlinx.coroutines.launch

class TransactionsViewModel(private val repository: TransactionRepository) : ViewModel() {
    val allTransaction: LiveData<List<Transaction>> = repository.allTransaction.asLiveData()

    fun insert(transaction: Transaction) = viewModelScope.launch {
        repository.insert(transaction)
    }
    fun deleteById(id: Int) = viewModelScope.launch {
        repository.delete(id)
    }

    fun updateById(id: Int, name:String, amount: Float, location: String) = viewModelScope.launch {
        repository.update(id, name, amount, location)
    }
}