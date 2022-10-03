package com.example.cryptoapp.cryptoViewModel

import androidx.lifecycle.*
import com.example.cryptoapp.data.entities.Data
import com.example.cryptoapp.data.repository.CryptoRepo
import com.example.cryptoapp.data.repository.DetailsRepository
import com.example.cryptoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoDetailsVM @Inject constructor(private val repo: DetailsRepository): ViewModel() {

    /**
     * first pass the id from backend
     */


    val cryptoLiveData = MutableLiveData<Data>()
    val errorLiveData = MutableLiveData<String>()

    fun getCryptoDetails(id:String){
        viewModelScope.launch {
            var response = repo.getCryptoDetails(id)
                if(response.isSuccessful){
                    cryptoLiveData.postValue(response.body())
                } else{
                    errorLiveData.postValue(response.errorBody().toString())

                }
        }

    }







/*
    private val _id = MutableLiveData<String>()
    private val _crypto = _id.switchMap { id ->repo.getCryptoCurrencyDetails(id) }

    val crypto: LiveData<Resource<Data>> = _crypto

    // this function will be used by the fragment to retrieve the details
    fun cryptoDetailsCall(id:String){

        _id.value = id
    }

 */
}