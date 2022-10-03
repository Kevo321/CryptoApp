package com.example.cryptoapp.data.remote.rates

import com.example.cryptoapp.data.remote.BaseDataSource
import javax.inject.Inject

class RatesRemoteDataSource @Inject constructor(private val ratesService: RatesService):BaseDataSource() {

    suspend fun getRates() = getResult { ratesService.getAllCryptoRates() }
}