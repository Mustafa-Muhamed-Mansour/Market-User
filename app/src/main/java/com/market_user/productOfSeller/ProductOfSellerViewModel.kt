package com.market_user.productOfSeller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.market_user.model.CartModel
import com.market_user.model.ProductModel
import com.market_user.model.UserModel

class ProductOfSellerViewModel : ViewModel()
{

    private var booleanMutableLiveDataProductModel: MutableLiveData<ArrayList<ProductModel>> = MutableLiveData()
    private var booleanMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var stringMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var productModel: ArrayList<ProductModel> = ArrayList()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var randomKey: String = FirebaseDatabase.getInstance().reference.push().key.toString()
    private var retProductReference: DatabaseReference = FirebaseDatabase.getInstance().reference


    fun retrieveProductOfSeller(id: String): LiveData<ArrayList<ProductModel>>
    {
        retProductReference
            .child("Sellers")
            .child(id)
            .child("Products")
            .addValueEventListener(object : ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    productModel.clear()

                    if (snapshot.exists())
                    {
                        for (snap in snapshot.children)
                        {
                            val model = snap.getValue(ProductModel::class.java)
                            productModel.add(model!!)
                        }

                        booleanMutableLiveDataProductModel.postValue(productModel)
                    }

                    else
                    {
                        stringMutableLiveData.value = snapshot.value.toString()
                    }
                }

                override fun onCancelled(error: DatabaseError)
                {
                    stringMutableLiveData.value = error.message
                }

            })

        return booleanMutableLiveDataProductModel
    }


    fun productCart(imageProduct: String, titleProduct: String, priceProduct: String, quantityProduct: String, priceType: String, resultTotal: String): LiveData<Boolean>
    {

        retProductReference
            .child("Users")
            .child(auth.uid!!)
            .addValueEventListener(object : ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    if (snapshot.exists())
                    {
                        val model = snapshot.getValue(UserModel::class.java)
                        retProductReference
                            .child("USERS")
                            .child(auth.uid!!)
                            .setValue(model)
                            .addOnCompleteListener {

                                if (it.isSuccessful)
                                {
                                    val cartModel = CartModel(auth.uid!!, randomKey, imageProduct, titleProduct, priceType, priceProduct, quantityProduct, resultTotal)
                                    retProductReference
                                        .child("USERS")
                                        .child(auth.uid!!)
                                        .child("Carts")
                                        .child(randomKey)
                                        .setValue(cartModel)
                                        .addOnCompleteListener {

                                            if (it.isSuccessful)
                                            {
                                                booleanMutableLiveData.postValue(true)
                                            }

                                            else
                                            {
                                                booleanMutableLiveData.postValue(false)
                                                stringMutableLiveData.value = it.exception!!.message
                                            }

                                        }
                                }

                                else
                                {
                                    stringMutableLiveData.value = it.exception!!.message
                                    booleanMutableLiveData.postValue(false)
                                }
                            }
                    }
                }

                override fun onCancelled(error: DatabaseError)
                {
                    stringMutableLiveData.value = error.message
                    booleanMutableLiveData.postValue(false)
                }

            })

        return booleanMutableLiveData
    }

}