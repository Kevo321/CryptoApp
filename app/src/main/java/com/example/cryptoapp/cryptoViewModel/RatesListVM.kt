package com.example.cryptoapp.cryptoViewModel

import androidx.lifecycle.ViewModel
import com.example.cryptoapp.data.repository.RatesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RatesListVM @Inject constructor(repo: RatesRepo): ViewModel() {


    val repository = repo.getRates()
}