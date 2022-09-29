package com.example.cryptoapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptoapp.data.entities.Data

@Dao
interface CryptoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(result: List<Data>)

    @Query("SELECT * FROM Crypto_Details")
    fun getAllCrypto(): LiveData<List<Data>>

    /**
     * Query for crypto details
     */


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(crypto:Data)

    @Query("SELECT * FROM Crypto_Details WHERE id = :id")
    fun getCryptoCurrency(id:String): LiveData<Data>
}