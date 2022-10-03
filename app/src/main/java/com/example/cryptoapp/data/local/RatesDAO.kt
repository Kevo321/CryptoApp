package com.example.cryptoapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptoapp.data.entities.Data
import com.example.cryptoapp.data.entities.rates.RateData

@Dao
interface RatesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRates(result: List<RateData>)

    @Query("SELECT * FROM Crypto_Rates")
    fun getAllCryptoRates(): LiveData<List<RateData>>
}