package com.example.cryptoapp.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.adapter.RatesAdapter
import com.example.cryptoapp.cryptoViewModel.RatesListVM
import com.example.cryptoapp.databinding.FragmentDashboardBinding
import com.example.cryptoapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var adapter: RatesAdapter
    private val viewModel: RatesListVM by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       // val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObservers()
        adapter = RatesAdapter()

        _binding?.RatesRV?.adapter = adapter
        _binding?.RatesRV?.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun startObservers(){
        viewModel.repository.observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.SUCCESS ->{
                       Log.i("Datav2", ""+ (it.data))
                    adapter.submitList(it.data)
                }
                Resource.Status.ERROR ->{
                    Log.i("Error", it.message.toString())
                }
                Resource.Status.LOADING ->{

                }
            }
        }
    }
}