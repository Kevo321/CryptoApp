package com.example.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cryptoapp.cryptoViewModel.CryptoDetailsVM
import com.example.cryptoapp.cryptoViewModel.CryptoListVM
import com.example.cryptoapp.data.entities.Data
import com.example.cryptoapp.databinding.FragmentDetailsBinding
import com.example.cryptoapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewmodel: CryptoDetailsVM by viewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.getString("ID").let {

            viewmodel.cryptoDetailsCall("bitcoin")
            Log.i("ID",""+arguments?.getString("ID"))
        }

        startObserverGetDetails()


    }


    fun startObserverGetDetails() {
        viewmodel.crypto.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> { //200

                    Log.i("Data2", "" + (it.data?.name))
                  //  bindDetailsData(it?.data!!)


                }
                Resource.Status.ERROR -> {
                    Log.i("Error", it.message.toString())
                }
                Resource.Status.LOADING -> {

                }
            }
        }
    }

    private fun bindDetailsData(data:Data){
        binding.namedetailsTextView.text = data.name
        binding.idTextView.text = data.id




    }

}