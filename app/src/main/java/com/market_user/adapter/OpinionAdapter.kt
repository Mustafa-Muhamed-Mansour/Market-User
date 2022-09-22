package com.market_user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.market_user.R
import com.market_user.databinding.ItemOpinionBinding
import com.market_user.model.OpinionModel

class OpinionAdapter(private var opinionModel: ArrayList<OpinionModel>): RecyclerView.Adapter<OpinionAdapter.CommentViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder
    {
        return CommentViewHolder(ItemOpinionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int)
    {
        val model = opinionModel[position]

        Glide
            .with(holder.itemView.context)
            .load(model.imageUser)
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
            .into(holder.bind.imgUserItemOpinion)
        holder.bind.txtUserNameItemOpinion.text = model.nameUser
        holder.bind.txtUserOpinionItemOpinion.text = model.commentUser
        holder.bind.txtNumberTrueItemOpinion.text = model.numberTrue.toString()
        holder.bind.txtNumberFalseItemOpinion.text = model.numberFalse.toString()

    }

    override fun getItemCount(): Int
    {
        return opinionModel.size
    }

    class CommentViewHolder(binding: ItemOpinionBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val bind = binding
    }

}