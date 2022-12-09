package com.example.cryptoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cryptoapp.data.entities.Data
import com.example.cryptoapp.data.local.CryptoDAO
import com.example.cryptoapp.data.remote.CryptoService
import com.example.cryptoapp.data.remote.RemoteDataSource
import com.example.cryptoapp.data.repository.CryptoRepo
import com.example.cryptoapp.utils.Resource
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryTests {

    @get:Rule
    val instantTaskExecutorRule =  InstantTaskExecutorRule()

    private val cryptoObserver: Observer<Resource<List<Data>>> = mockk(relaxed = true)

    private val remoteDataSource: RemoteDataSource = mockk(relaxed = true)
    private val localDataSource: CryptoDAO = mockk(relaxed = true)
    private val cryptoService:CryptoService = mockk(relaxed = true)
    private lateinit var repostitory:CryptoRepo

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repostitory = CryptoRepo(remoteDataSource,localDataSource,cryptoService)

    }

    @Test
    fun`get crypto should return livedata of crypto API`(){

        repostitory.getCrypto().observeForever(cryptoObserver)
        verify { cryptoObserver.onChanged(any()) }

    }




}