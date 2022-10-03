package com.example.cryptoapp

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.cryptoapp.adapter.Adapter
import com.example.cryptoapp.ui.home.HomeFragment
import org.junit.Before
import org.junit.Test

class HomeFragmentTest {
    private  val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp(){



        launchFragmentInHiltContainer<HomeFragment>(Bundle(),R.style.Theme_CryptoApp){
            navController.setGraph(R.navigation.mobile_navigation)
            Navigation.setViewNavController(requireView(),navController)

        }
    }

    @Test
    fun checkRecyclerViewVisibility(){

        onView(withId(R.id.CryptoRV))
            .check(matches(isDisplayed()))
    }

  /**  @Test
    fun recyclerViewItemClick_ShouldShow(){

        onView(withId(R.id.CryptoRV))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<Adapter.MyViewHolder>(0,click()))
            assert(navController.currentDestination?.id == R.id.detailsFragment)
    }
  */
}
