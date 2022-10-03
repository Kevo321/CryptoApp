package com.example.cryptoapp.data.remote.rates

import com.example.cryptoapp.data.entities.Items
import com.example.cryptoapp.data.entities.rates.Rates
import retrofit2.Response
import retrofit2.http.GET

interface RatesService {


    @GET("rates")
    suspend fun getAllCryptoRates(): Response<Rates>

}