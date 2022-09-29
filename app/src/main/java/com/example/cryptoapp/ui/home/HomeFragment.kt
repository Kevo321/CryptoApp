package com.example.cryptoapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.Adapter
import com.example.cryptoapp.cryptoViewModel.CryptoListVM
import com.example.cryptoapp.databinding.FragmentHomeBinding
import com.example.cryptoapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewmodel: CryptoListVM by viewModels()
    private lateinit var adapter: Adapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       // val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      startObservers()


        adapter = Adapter(Adapter.OnClickListener{

        val bundle = Bundle()
        bundle.putString("ID",it.id)
        findNavController().navigate(R.id.action_navigation_home_to_detailsFragment,bundle)

        })

        _binding?.CryptoRV?.adapter = adapter
        _binding?.CryptoRV?.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun startObservers(){
        viewmodel.repository.observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.SUCCESS ->{
                    Log.i("Data", ""+ (it.data))
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