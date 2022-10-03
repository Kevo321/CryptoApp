package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.data.entities.rates.RateData
import com.example.cryptoapp.databinding.RowRatesBinding


class RatesAdapter constructor():  ListAdapter<RateData, RatesAdapter.MyViewHolder>(RatesSampleItemDiffCallback()) {

    class MyViewHolder(val itemBinding: RowRatesBinding) :


        RecyclerView.ViewHolder(itemBinding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RowRatesBinding.inflate(view,parent,false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val ratesList = getItem(position)



        holder.itemBinding.nameTextView.text = ratesList.id
        //holder.itemBinding.iconTextView2.text = ratesList.symbol
       holder.itemBinding.priceTextView.text =  ratesList.rateUsd?.substring(0,5)
        holder.itemBinding.symboltextView.text = ratesList.symbol

        /* holder.itemBinding.percentTextView.text = changeColour(
             (cryptoList.changePercent24Hr!!.substring(0,5))) + "%"

         */



        }


    }



class RatesSampleItemDiffCallback : DiffUtil.ItemCallback<RateData>() {
    override fun areItemsTheSame(oldItem: RateData, newItem: RateData): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: RateData, newItem: RateData): Boolean = oldItem == newItem

}



