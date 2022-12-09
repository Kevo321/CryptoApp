package com.example.cryptoapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Crypto_Details")
data class Data(


    //
    val id: String?,
    @PrimaryKey
    val name: String,
    val priceUsd: String?,
    val symbol: String?,
    val changePercent24Hr: String?,
    val explorer: String?,

)