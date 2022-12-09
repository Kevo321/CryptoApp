package com.example.cryptoapp.data.entities.rates

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Crypto_Rates")
data class RateData(
    val currencySymbol: String?,
    @PrimaryKey
    val id: String,
    val rateUsd: String?,
    val symbol: String?,
    val type: String?
)