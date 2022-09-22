package com.market_user.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.market_user.adapter.SellerAdapter
import com.market_user.common.Validation
import com.market_user.databinding.FragmentHomeBinding
import com.market_user.interfaces.Seller
import com.market_user.model.SellerModel
import com.market_user.opinion.OpinionFragment


class HomeFragment : Fragment(), Seller
{

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var opinionFragment: OpinionFragment
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var validation: Validation


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)


        initView()
        initViewModel()
        retrieveViewModel()

    }

    private fun initView()
    {
        bottomSheetDialog = BottomSheetDialog(requireContext())
        opinionFragment = OpinionFragment()
        validation = Validation()
    }

    private fun initViewModel()
    {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun retrieveViewModel()
    {
        binding.loadingSeller.visibility = View.VISIBLE
        viewModel
            .retrieveSeller()
            .observe(viewLifecycleOwner)
            {

                binding.loadingSeller.visibility = View.GONE

                val sellerAdapter = SellerAdapter(it, this@HomeFragment)
                binding.rVSeller.adapter = sellerAdapter
                binding.rVSeller.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rVSeller.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
                sellerAdapter.notifyDataSetChanged()
            }
    }


    override fun clickProductOfSeller(sellerModel: SellerModel)
    {
        val productOfSeller = HomeFragmentDirections.actionHomeFragmentToProductOfSellerFragment(sellerModel)
        Navigation.findNavController(requireView()).navigate(productOfSeller)
    }

    override fun opinionOfSeller(sellerModel: SellerModel)
    {
        val dataSeller = HomeFragmentDirections.actionHomeFragmentToOpinionFragment(sellerModel)
        Navigation.findNavController(requireView()).navigate(dataSeller)
    }
}