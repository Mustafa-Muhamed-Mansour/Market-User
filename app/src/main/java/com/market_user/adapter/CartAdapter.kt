package com.market_user.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.market_user.R
import com.market_user.databinding.ItemCartBinding
import com.market_user.interfaces.DeleteOnCart
import com.market_user.interfaces.Product
import com.market_user.interfaces.ToastMessage
import com.market_user.model.CartModel
import it.sephiroth.android.library.numberpicker.NumberPicker

class CartAdapter(private var cartModel: ArrayList<CartModel>, private var product: Product, private var deleteOnCart: DeleteOnCart, private var toastMessage: ToastMessage): RecyclerView.Adapter<CartAdapter.CartViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder
    {
        return CartViewHolder(ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int)
    {
        val model = cartModel[position]

        Glide
            .with(holder.itemView.context)
            .load(model.imageProduct)
            .placeholder(R.drawable.ic_product)
            .error(R.drawable.ic_product)
            .into(holder.bind.imgItemCart)
        holder.bind.txtTitleItemCart.text = model.titleProduct
//        holder.bind.txtQuantityItemCart.text = "Quantity: ${model.quantityProduct}"
        holder.bind.numberPickerItemCart.minValue = 0
        holder.bind.numberPickerItemCart.maxValue = model.quantityProduct.toInt()
//        holder.bind.numberPickerItemCart.maxValue = 15

        if (model.priceType == "Pound")
        {
            holder.bind.txtPriceItemCart.text = "Price: ${model.priceProduct}" + " £"
        }

        if (model.priceType == "Dollar")
        {
            holder.bind.txtPriceItemCart.text = "Price: ${model.priceProduct}" + " $"
        }

        if (model.priceType == "Euro")
        {
            holder.bind.txtPriceItemCart.text = "Price: ${model.priceProduct}" + " €"
        }

        if (model.priceType == "Durham")
        {
            holder.bind.txtPriceItemCart.text = "Price: ${model.priceProduct}" + "د.إ "
        }

        if (model.priceType == "Rial")
        {
            holder.bind.txtPriceItemCart.text = "Price: ${model.priceProduct}" + "ر.س "
        }

        if (model.priceType == "Dinar")
        {
            holder.bind.txtPriceItemCart.text = "Price: ${model.priceProduct}" + "د.ك "
        }

        holder.bind.numberPickerItemCart.numberPickerChangeListener = object : NumberPicker.OnNumberPickerChangeListener
        {
            override fun onProgressChanged(numberPicker: NumberPicker, progress: Int, fromUser: Boolean)
            {

//                if (numberPicker.progress >= model.quantityProduct.toInt().inc())
//                {
//                    holder.bind.txtResultItemCart.text = product.clickGreaterOnQuantityOfCart().toString()
//                    toastMessage.toastOnGreaterOfQuantityCart()
//                    holder.bind.btnSendOrderItemCart.visibility = View.GONE
//                }

//                else
//                {
                    holder.bind.btnSendOrderItemCart.visibility = View.VISIBLE

                    if (model.priceType == "Pound")
                    {
                        holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + " £"
                    }

                    if (model.priceType == "Dollar")
                    {
                        holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + " $"
                    }

                    if (model.priceType == "Euro")
                    {
                        holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + " €"
                    }

                    if (model.priceType == "Durham")
                    {
                        holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "د.إ "
                    }

                    if (model.priceType == "Rial")
                    {
                        holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "ر.س "
                    }

                    if (model.priceType == "Dinar")
                    {
                        holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "د.ك "
                    }
//                }
            }

            override fun onStartTrackingTouch(numberPicker: NumberPicker)
            {

//                if (model.priceType == "Pound")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "£"
//                }
//
//                if (model.priceType == "Dollar")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "$"
//                }
//
//                if (model.priceType == "Euro")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "€"
//                }
//
//                if (model.priceType == "Durham")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "د.إ"
//                }
//
//                if (model.priceType == "Rial")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "ر.س"
//                }
//
//                if (model.priceType == "Dinar")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "د.ك"
//                }
            }

            override fun onStopTrackingTouch(numberPicker: NumberPicker)
            {

//                if (model.priceType == "Pound")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "£"
//                }
//
//                if (model.priceType == "Dollar")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "$"
//                }
//
//                if (model.priceType == "Euro")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "€"
//                }
//
//                if (model.priceType == "Durham")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "د.إ"
//                }
//
//                if (model.priceType == "Rial")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "ر.س"
//                }
//
//                if (model.priceType == "Dinar")
//                {
//                    holder.bind.txtResultItemCart.text = product.clickOnQuantityOfCart(numberPicker.progress, model).toString() + "د.ك"
//                }
            }
        }

        holder.bind.btnSendOrderItemCart.setOnClickListener {

            product.clickOnQuantityOfOrder(holder.bind.numberPickerItemCart.progress, model).toString()

        }

        holder.bind.imgBtnDeleteItemCart.setOnClickListener {

            deleteOnCart.deleteOnOrderOfCart(model)
        }
    }

    override fun getItemCount(): Int
    {
        return cartModel.size
    }

    class CartViewHolder(binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val bind = binding
    }
}