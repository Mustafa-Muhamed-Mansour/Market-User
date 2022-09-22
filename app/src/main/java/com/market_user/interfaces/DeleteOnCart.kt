package com.market_user.interfaces

import com.market_user.model.CartModel

interface DeleteOnCart
{
    fun deleteOnOrderOfCart(cartModel: CartModel)
}