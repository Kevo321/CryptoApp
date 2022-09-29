package com.example.cryptoapp.adapter

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.cryptoapp.R
import com.example.cryptoapp.data.entities.Data
import com.example.cryptoapp.databinding.RowCryptoBinding

class Adapter constructor(private val onClickListener:OnClickListener):
    ListAdapter<Data, Adapter.MyViewHolder>(SampleItemDiffCallback()) {

    class MyViewHolder(val itemBinding: RowCryptoBinding) :


        RecyclerView.ViewHolder(itemBinding.root)

    class OnClickListener(val clickListener: (crypto: Data)-> Unit){

        fun onClick(Crypto: Data) = clickListener(Crypto)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RowCryptoBinding.inflate(view,parent,false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val cryptoList = getItem(position)



        holder.itemBinding.nameTextView.text = cryptoList.id
        holder.itemBinding.iconTextView2.text = cryptoList.symbol
        holder.itemBinding.priceTextView.text = "$"+ cryptoList.priceUsd?.substring(0,4)
        holder.itemBinding.percentTextView.text = "24hr: "+cryptoList.changePercent24Hr?.substring(0,5)+ "%"
        /* holder.itemBinding.percentTextView.text = changeColour(
             (cryptoList.changePercent24Hr!!.substring(0,5))) + "%"

         */

        Glide.with(holder.itemView.context)
            .load(R.drawable.cryptoiconblue)
            .transform(CircleCrop())
            .into(holder.itemBinding.iconImageView)

        holder.itemView.setOnClickListener{
            onClickListener.onClick(cryptoList)

        }









    }

    /**
     * trying to check if string contains specia character and if it does
     * change the colour to red else change to green.
     */

    fun changeColour(param1:String):String{

        val ss = SpannableString(param1)
        val red =  ForegroundColorSpan(Color.RED)
        val green = ForegroundColorSpan(Color.GREEN)
        if(param1.contains("-")) {
            ss.setSpan(red, 0, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        } else {
            ss.setSpan(green,0,ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        return ss.toString()
    }


}







class SampleItemDiffCallback : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean = oldItem == newItem

}