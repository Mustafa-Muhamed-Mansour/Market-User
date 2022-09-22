package com.market_user.model

class CartModel
{
    lateinit var id: String
    lateinit var randomKey: String
    lateinit var imageProduct: String
    lateinit var titleProduct: String
    lateinit var priceType: String
    lateinit var priceProduct: String
    lateinit var quantityProduct: String
    lateinit var resultTotal: String

    constructor()
    {
    }

    constructor(id: String, randomKey: String, imageProduct: String, titleProduct: String, priceType: String, priceProduct: String, quantityProduct: String, resultTotal: String)
    {
        this.id = id
        this.randomKey = randomKey
        this.imageProduct = imageProduct
        this.titleProduct = titleProduct
        this.priceType = priceType
        this.priceProduct = priceProduct
        this.quantityProduct = quantityProduct
        this.resultTotal = resultTotal
    }

//    constructor(id: String, randomKey: String, imageProduct: String, titleProduct: String, priceProduct: String, quantityProduct: String, resultTotal: String)
//    {
//        this.id = id
//        this.randomKey = randomKey
//        this.imageProduct = imageProduct
//        this.titleProduct = titleProduct
//        this.priceProduct = priceProduct
//        this.quantityProduct = quantityProduct
//        this.resultTotal = resultTotal
//    }




}