package com.example.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

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


        arguments?.getString("id").let {

            viewmodel.cryptoDetailsCall(it!!)

       /**     Log.i("ID",""+arguments?.getString("price"))

            Log.i("price",it)
           // binding.namedetailsTextView.text = it!!
          //  binding.priceTextView1.text = it.

            binding.DetailsPricetextView.text = "$"+it.substring(0,4)

       **/
            }

      startObserverGetDetails()
/**
        arguments?.getString("name").let {
            viewmodel.getCryptoDetails(it!!)
            binding.DetailsnametextView.text = it
        }

            arguments?.getString("changeprice").let {
                viewmodel.getCryptoDetails(it!!)
                binding.ChangePercentagetextView3.text = it.substring(0,6)
            }


        arguments?.getString("link").let {
            viewmodel.getCryptoDetails(it!!)
            binding.LinkTextView.text = it
        }

        Glide.with(binding.root)
            .load(R.drawable.cryptoiconblue)
            .transform(CircleCrop())
            .into(binding.imageView)

             // startObserverGetDetails()

        **/
        }



    fun startObserverGetDetails() {

        viewmodel.crypto.observe(viewLifecycleOwner){

           when (it.status) {
                Resource.Status.SUCCESS -> { //200
                    // pass the data to recyclerview
                    Log.i("Dataa", "" + (it.data))
                    bindDetailsData(it.data!!)

                    // create a recyclerview adapter
                }
                Resource.Status.ERROR -> {
                    Log.i("Error", it.message.toString())
                }
                Resource.Status.LOADING -> {
                    // Progress Dialog
                }
            }


        }

    }


    private fun bindDetailsData(data:Data){
        binding.LinkTextView.text = data.explorer
        binding.ChangePercentagetextView3.text = data.changePercent24Hr?.substring(0,6)
        binding.DetailsnametextView.text = data.name
        binding.DetailsPricetextView.text = "$"+data.priceUsd?.substring(0,4)


            Glide.with(binding.root)
            .load(R.drawable.cryptoiconblue)
            .transform(CircleCrop())
            .into(binding.imageView)


    }

}




