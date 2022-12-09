package com.example.cryptoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.Resource
import com.example.cryptoapp.cryptoViewModel.CryptoListVM
import com.example.cryptoapp.data.entities.Data
import com.example.cryptoapp.data.local.CryptoDAO
import com.example.cryptoapp.data.remote.RemoteDataSource
import com.example.cryptoapp.data.repository.CryptoRepo
import com.example.cryptoapp.utils.Resource.Companion.success
import dagger.hilt.android.lifecycle.HiltViewModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import javax.inject.Inject
import com.example.cryptoapp.utils.Resource.Status

@HiltViewModel
class ViewModelTests @Inject constructor() {

    @get:Rule
    val instantTaskExecutorRule =  InstantTaskExecutorRule()
    private lateinit var viewModel: CryptoListVM
    val myCrypto = listOf( Data("TCoin","TestCoin","10","TCN","0",""))

    private val cryptoObserver: Observer<com.example.cryptoapp.utils.Resource<List<Data>>> = mockk(relaxed = true)

    var repository: CryptoRepo = mockk(relaxed = true){
        coEvery { getCrypto() } returns MutableLiveData (success(myCrypto))
    }

    @Before
    fun Before(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = CryptoListVM(repository)
        viewModel.repository.observeForever(cryptoObserver)
    }

    @Test
    fun `get crypto should emit list`(){


        verify { cryptoObserver.onChanged(success(myCrypto)) }
        assert(viewModel.repository.value == success(myCrypto))

    }
}



