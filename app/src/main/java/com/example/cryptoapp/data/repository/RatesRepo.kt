package com.example.cryptoapp.data.repository

import com.example.cryptoapp.data.local.RatesDAO
import com.example.cryptoapp.data.remote.rates.RatesRemoteDataSource
import com.example.cryptoapp.utils.performGetOperation
import javax.inject.Inject

class RatesRepo @Inject constructor(private val remoteDataSource: RatesRemoteDataSource,
                                    private val localDataSource:RatesDAO) {

    fun getRates()= performGetOperation(
        databaseQuery = {localDataSource.getAllCryptoRates()},
        networkCall = {remoteDataSource.getRates()},
        saveCallResult = {localDataSource.insertAllRates(it.data)}
    )
}