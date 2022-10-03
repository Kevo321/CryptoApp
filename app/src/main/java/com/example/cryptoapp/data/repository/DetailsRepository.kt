package com.example.cryptoapp.data.repository

import com.example.cryptoapp.data.remote.CryptoService
import javax.inject.Inject

class DetailsRepository @Inject constructor(val cryptoService: CryptoService) {

    suspend fun getCryptoDetails(id:String) = cryptoService.getCryptoDetails(id)
}