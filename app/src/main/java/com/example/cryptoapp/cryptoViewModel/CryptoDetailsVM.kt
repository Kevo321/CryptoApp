package com.example.cryptoapp.cryptoViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.cryptoapp.data.entities.Data
import com.example.cryptoapp.data.repository.CryptoRepo
import com.example.cryptoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailsVM @Inject constructor(private val repo: CryptoRepo): ViewModel() {

    /**
     * first pass the id from backend
     */

    private val _id = MutableLiveData<String>()
    private val _crypto = _id.switchMap { id ->repo.getCryptoCurrencyDetails(id) }

    val crypto: LiveData<Resource<Data>> = _crypto

    // this function will be used by the fragment to retrieve the details
    fun cryptoDetailsCall(id:String){

        _id.value = id
    }
}