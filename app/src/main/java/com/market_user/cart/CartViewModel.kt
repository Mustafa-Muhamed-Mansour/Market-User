package com.market_user.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.market_user.model.CartModel
import com.market_user.model.OrderModel

class CartViewModel : ViewModel()
{
    private var booleanMutableLiveDataCartModel: MutableLiveData<ArrayList<CartModel>> = MutableLiveData()
    private var booleanMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var stringMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var cartModel: ArrayList<CartModel> = ArrayList()
    private var randomKey: String = FirebaseDatabase.getInstance().reference.push().key.toString()
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var retCartReference: DatabaseReference = FirebaseDatabase.getInstance().reference



    fun retrieveCart(): LiveData<ArrayList<CartModel>>
    {
        retCartReference
            .child("USERS")
            .child(auth.uid!!)
            .child("Carts")
            .addValueEventListener(object : ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot)
                {
                    cartModel.clear()

                    if (snapshot.exists())
                    {
                        for (snap in snapshot.children)
                        {
                            val model = snap.getValue(CartModel::class.java)
                            cartModel.add(model!!)
                        }

                        booleanMutableLiveDataCartModel.postValue(cartModel)
                    }
                }

                override fun onCancelled(error: DatabaseError)
                {
                    stringMutableLiveData.value = error.message
                }

            })

        return booleanMutableLiveDataCartModel
    }

    fun deleteCart(randomKey: String): LiveData<Boolean>
    {
        retCartReference
            .child("USERS")
            .child(auth.uid!!)
            .child("Carts")
            .child(randomKey)
            .removeValue()

        booleanMutableLiveData.postValue(true)

        return booleanMutableLiveData
    }


    fun sendOrder(image: String, title: String, price: String, priceType: String, resultPrice: String): LiveData<Boolean>
    {
        val orderModel = OrderModel(randomKey, image, title, price, priceType, resultPrice)
        retCartReference
            .child("USERS")
            .child(auth.uid!!)
            .child("Orders")
            .child(randomKey)
            .setValue(orderModel)
            .addOnCompleteListener {

                if (it.isSuccessful)
                {
                    booleanMutableLiveData.postValue(true)
                }

                else
                {
                    stringMutableLiveData.value = it.exception!!.message
                    booleanMutableLiveData.postValue(false)
                }

//        retCartReference
//            .child("Users")
//            .child(auth.uid!!)
//            .addValueEventListener(object : ValueEventListener
//            {
//
//                override fun onDataChange(snapshot: DataSnapshot)
//                {
//                    if (snapshot.exists())
//                    {
//                        val userModel = snapshot.getValue(UserModel::class.java)
//
//
//                    }
//
//                    else
//                    {
//                        booleanMutableLiveData.postValue(false)
//                    }
//                }

//                override fun onCancelled(error: DatabaseError)
//                {
//                    stringMutableLiveData.value = error.message
//                    booleanMutableLiveData.postValue(false)
//                }
            }

        return booleanMutableLiveData
    }


    fun deleteOrder(randomKey: String)
    {
        retCartReference
            .child("USERS")
            .child(auth.uid!!)
            .child("Carts")
            .child(randomKey)
            .removeValue()
    }

}