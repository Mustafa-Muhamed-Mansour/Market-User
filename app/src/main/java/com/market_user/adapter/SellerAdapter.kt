package com.market_user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.market_user.R
import com.market_user.databinding.ItemSellerBinding
import com.market_user.interfaces.Seller
import com.market_user.model.SellerModel

class SellerAdapter(private var sellerModel: ArrayList<SellerModel>, private var seller: Seller): RecyclerView.Adapter<SellerAdapter.SellerViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerViewHolder
    {
        return SellerViewHolder(ItemSellerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int)
    {
        val model = sellerModel[position]

        Glide
            .with(holder.itemView.context)
            .load(model.image)
            .placeholder(R.drawable.ic_location)
            .error(R.drawable.ic_location)
            .into(holder.bind.imgItemSeller)
        holder.bind.txtLocationItemSeller.text = model.address

        holder.itemView.setOnClickListener {

            seller.clickProductOfSeller(model)

        }

        holder.bind.imgBtnCommentItemSeller.setOnClickListener {


            seller.opinionOfSeller(model)

        }
    }

    override fun getItemCount(): Int
    {
        return sellerModel.size
    }

    class SellerViewHolder(binding: ItemSellerBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val bind = binding
    }

}