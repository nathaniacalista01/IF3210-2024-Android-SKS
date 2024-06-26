package com.example.bondoman.repositories

import androidx.annotation.WorkerThread
import com.example.bondoman.entities.Transaction
import com.example.bondoman.interfaces.ITransactionDao
import com.example.bondoman.lib.SecurePreferences
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: ITransactionDao, securePreferences: SecurePreferences) {
    val allTransaction : Flow<List<Transaction>> = transactionDao.getAll(securePreferences.getEmail() ?: "")

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    suspend fun update(id: Int, name:String, amount: Float, location: String)
        = transactionDao.update(id, name, amount, location)

    suspend fun delete(id: Int)
        = transactionDao.delete(id)
}