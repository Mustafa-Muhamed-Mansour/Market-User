package com.market_user.productOfSeller

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.market_user.R
import com.market_user.adapter.ProductAdapter
import com.market_user.databinding.FragmentProductOfSellerBinding
import com.market_user.interfaces.ClickOnProduct
import com.market_user.model.ProductModel

class ProductOfSellerFragment : Fragment(), ClickOnProduct
{

    private lateinit var binding: FragmentProductOfSellerBinding
    private lateinit var viewModel: ProductOfSellerViewModel
    private lateinit var productSeller: ProductOfSellerFragmentArgs


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentProductOfSellerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)


        initView()
        clickView()
        initViewModel()
        retrieveViewModel()

    }

    private fun initView()
    {
        productSeller = ProductOfSellerFragmentArgs.fromBundle(requireArguments())

        Glide
            .with(requireContext())
            .load(productSeller.productOfSeller.image)
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
            .into(binding.imgSeller)
        binding.txtNameSeller.text = productSeller.productOfSeller.name
        binding.txtPhoneSeller.text = productSeller.productOfSeller.phone
    }

    private fun clickView()
    {
    }

    private fun initViewModel()
    {
        viewModel = ViewModelProvider(this)[ProductOfSellerViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun retrieveViewModel()
    {
        binding.loadingProduct.visibility = View.VISIBLE
        viewModel
            .retrieveProductOfSeller(productSeller.productOfSeller.id)
            .observe(viewLifecycleOwner)
            {

                binding.loadingProduct.visibility = View.GONE

                val productAdapter = ProductAdapter(it, this@ProductOfSellerFragment)
                binding.rVProduct.adapter = productAdapter
                binding.rVProduct.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rVProduct.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
                productAdapter.notifyDataSetChanged()
            }
    }


    override fun clickOnProductCartOfSeller(productModel: ProductModel)
    {
        viewModel
            .productCart(productModel.image, productModel.title, productModel.price, productModel.quantity, productModel.priceType, "0")
            .observe(viewLifecycleOwner)
            {

                if (it)
                {
                    Toast.makeText(requireContext(), "Done is uploaded in cart", Toast.LENGTH_SHORT).show()
                }

                else
                {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }

            }
    }

}