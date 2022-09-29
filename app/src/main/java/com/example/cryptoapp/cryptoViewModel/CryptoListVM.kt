package com.example.cryptoapp.cryptoViewModel

import androidx.lifecycle.ViewModel
import com.example.cryptoapp.data.repository.CryptoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoListVM @Inject constructor(repo: CryptoRepo):ViewModel() {

    val repository = repo.getCrypto()
}