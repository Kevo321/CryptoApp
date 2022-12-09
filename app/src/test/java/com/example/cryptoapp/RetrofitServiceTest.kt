package com.example.cryptoapp

import com.example.cryptoapp.data.remote.CryptoService
import com.google.gson.Gson
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceTest {


    lateinit var mockWebServer: MockWebServer
    lateinit var apiService: CryptoService
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(CryptoService::class.java)
    }


    @Test
    fun `get all crypto api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("{}"))
            val response = apiService.getAllCrypto()
            val request = mockWebServer.takeRequest()
            assertEquals("/assets",request.path)
            assertEquals(true, response.isSuccessful)
        }
    }

    @Test
    fun `get crypto api details`(){

        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("{}"))
            val response = apiService.getCryptoDetails("bitcoin-cash")
            val request = mockWebServer.takeRequest()
            assertEquals("/assets/bitcoin-cash",request.path)
            assertEquals(true, response.isSuccessful)
        }

    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}