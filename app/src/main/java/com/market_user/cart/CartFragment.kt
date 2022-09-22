package com.market_user.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.market_user.R
import com.market_user.adapter.CartAdapter
import com.market_user.databinding.FragmentCartBinding
import com.market_user.interfaces.DeleteOnCart
import com.market_user.interfaces.Product
import com.market_user.interfaces.ToastMessage
import com.market_user.model.CartModel

class CartFragment : Fragment(), Product, DeleteOnCart, ToastMessage
{

    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var quantityReference: DatabaseReference


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)


        initView()
        initDatabase()
        initViewModel()
        retrieveViewModel()

    }


    private fun initView()
    {
    }

    private fun initDatabase()
    {
        quantityReference = FirebaseDatabase.getInstance().reference
    }

    private fun initViewModel()
    {
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
    }

    private fun retrieveViewModel()
    {
        binding.loadingCart.visibility = View.VISIBLE
        viewModel
            .retrieveCart()
            .observe(viewLifecycleOwner)
            {
                binding.loadingCart.visibility = View.GONE

                val cartAdapter = CartAdapter(it,this@CartFragment, this@CartFragment, this@CartFragment)
                binding.rVCart.adapter = cartAdapter
                binding.rVCart.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rVCart.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            }
    }

    override fun clickOnQuantityOfCart(numberQuantity: Int, cartModel: CartModel): Int
    {
        return ((numberQuantity) * (cartModel.priceProduct.toInt()))
    }

    override fun clickOnQuantityOfOrder(numberQuantity: Int, cartModel: CartModel): Int
    {
        val resultTotal = ((numberQuantity) * (cartModel.priceProduct.toInt()))

        viewModel
            .sendOrder(cartModel.imageProduct, cartModel.titleProduct, cartModel.priceProduct, cartModel.priceType, resultTotal.toString())
            .observe(viewLifecycleOwner)
            {

                if (it)
                {
                    viewModel
                        .deleteOrder(cartModel.randomKey)

                    Toast.makeText(requireContext(), "Done", Toast.LENGTH_SHORT).show()

                    Navigation.findNavController(requireView()).navigate(R.id.action_cartFragment_to_homeFragment)
                }

                else
                {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }

            }

        return resultTotal
    }

    override fun deleteOnOrderOfCart(cartModel: CartModel)
    {
        viewModel
            .deleteCart(cartModel.randomKey)
            .observe(viewLifecycleOwner)
            {

                if (it)
                {
                    Toast.makeText(requireContext(), "Done is deleted", Toast.LENGTH_SHORT).show()
                }

                else
                {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }

            }
    }

    override fun toastOnGreaterOfQuantityCart()
    {
        Toast.makeText(requireContext(), "Sorry, Unable to implement your request because The quantity selected is greater than the available quantity ..", Toast.LENGTH_LONG).show()
    }
}