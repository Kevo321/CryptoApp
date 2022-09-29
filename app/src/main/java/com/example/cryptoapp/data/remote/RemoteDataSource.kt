package com.example.cryptoapp.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val cryptoService: CryptoService) :
    BaseDataSource() {

    suspend fun getCrypto() = getResult { cryptoService.getAllCrypto() }


    suspend fun getCryptoCurrencyByID(id:String) = getResult { cryptoService.getCryptoDetails(id) }

}