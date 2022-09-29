package com.example.cryptoapp.data.remote

import com.example.cryptoapp.data.entities.Data
import com.example.cryptoapp.data.entities.Items
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoService {

    @GET("assets")
    suspend fun getAllCrypto(): Response<Items>

    /**
     * service for retrieving details
     */


    @GET("assets/{id}")
    suspend fun getCryptoDetails(@Path("id")id:String): Response<Data>
}