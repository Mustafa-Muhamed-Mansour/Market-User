package com.market_user.interfaces

import com.market_user.model.SellerModel

interface Seller
{
    fun clickProductOfSeller(sellerModel: SellerModel)
    fun opinionOfSeller(sellerModel: SellerModel)
}