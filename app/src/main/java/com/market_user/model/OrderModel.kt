package com.market_user.model

class OrderModel
{
    lateinit var randomKey: String
    lateinit var imageProduct: String
    lateinit var titleProduct: String
    lateinit var priceProduct: String
    lateinit var priceType: String
    lateinit var resultPrice: String

    constructor()
    {
    }

    constructor(randomKey: String, imageProduct: String, titleProduct: String, priceProduct: String, priceType: String, resultPrice: String)
    {
        this.randomKey = randomKey
        this.imageProduct = imageProduct
        this.titleProduct = titleProduct
        this.priceProduct = priceProduct
        this.priceType = priceType
        this.resultPrice = resultPrice
    }

//    constructor(randomKey: String, imageProduct: String, titleProduct: String, priceProduct: String, resultPrice: String)
//    {
//        this.randomKey = randomKey
//        this.imageProduct = imageProduct
//        this.titleProduct = titleProduct
//        this.priceProduct = priceProduct
//        this.resultPrice = resultPrice
//    }




}