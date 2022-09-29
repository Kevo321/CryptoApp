package com.example.cryptoapp.data.repository

import com.example.cryptoapp.data.local.CryptoDAO
import com.example.cryptoapp.data.remote.RemoteDataSource
import com.example.cryptoapp.utils.performGetOperation
import javax.inject.Inject

class CryptoRepo @Inject constructor(private val remoteDataSource: RemoteDataSource,
                                     private val localDataSource: CryptoDAO
) {

    fun getCrypto() = performGetOperation(
        databaseQuery = {localDataSource.getAllCrypto()},
        networkCall = {remoteDataSource.getCrypto()},
        saveCallResult = {localDataSource.insertAll(it.data)}

    )

    /**
     * Cryptocurrency details
     */
    fun getCryptoCurrencyDetails(id:String) = performGetOperation(
        databaseQuery = {localDataSource.getCryptoCurrency(id)},
        networkCall = {remoteDataSource.getCryptoCurrencyByID(id)},
        saveCallResult = {localDataSource.insertDetails(it)}
    )




}