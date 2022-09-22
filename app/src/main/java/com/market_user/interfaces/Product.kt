package com.market_user.interfaces

import com.market_user.model.CartModel

interface Product
{
    fun clickOnQuantityOfCart(numberQuantity: Int, cartModel: CartModel): Int
    {
        return 0
    }

    fun clickOnQuantityOfOrder(numberQuantity: Int, cartModel: CartModel): Int
    {
        return 0
    }

    fun clickGreaterOnQuantityOfCart(): Int
    {
        return 0
    }
}