package com.market_user.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.market_user.R
import com.market_user.databinding.ItemProductBinding
import com.market_user.interfaces.ClickOnProduct
import com.market_user.model.ProductModel

class ProductAdapter(private var productModel: ArrayList<ProductModel>, private var clickOnProduct: ClickOnProduct): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder
    {
        return ProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int)
    {
        val model = productModel[position]

        Glide
            .with(holder.itemView.context)
            .load(model.image)
            .placeholder(R.drawable.ic_product)
            .error(R.drawable.ic_product)
            .into(holder.bind.imgItemProduct)
        holder.bind.txtTitleItemProduct.text = model.title

        holder.bind.txtTypeProductItemProduct.text = model.typeProduct
        holder.bind.txtQuantityItemProduct.text = "Quantity: ${model.quantity}"

        if (model.priceType == "Pound")
        {
            holder.bind.txtPriceItemProduct.text = "${model.price}  £"
        }

        if (model.priceType == "Dollar")
        {
            holder.bind.txtPriceItemProduct.text = "${model.price}  $"
        }

        if (model.priceType == "Euro")
        {
            holder.bind.txtPriceItemProduct.text = "${model.price}  €"
        }

        if (model.priceType == "Durham")
        {
            holder.bind.txtPriceItemProduct.text = "${model.price}د.إ  "
        }

        if (model.priceType == "Rial")
        {
            holder.bind.txtPriceItemProduct.text = "${model.price}ر.س  "
        }

        if (model.priceType == "Dinar")
        {
            holder.bind.txtPriceItemProduct.text = "${model.price}د.ك  "
        }

        holder.bind.fabCartItemProduct.setOnClickListener {

            clickOnProduct.clickOnProductCartOfSeller(model)

        }
    }

    override fun getItemCount(): Int
    {
        return productModel.size
    }

    class ProductViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val bind = binding
    }

}