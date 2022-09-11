package com.raphel.spoc.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.raphel.spoc.R
import com.raphel.spoc.databinding.FragmentAllPlansBinding
import com.raphel.spoc.databinding.FragmentOrderDetailsBinding
import com.raphel.spoc.domain.model.OrderDetailsDomainModel
import com.raphel.spoc.domain.model.ProductDetailsDomainModel
import com.raphel.spoc.presentation.adapter.OrdersListAdapter
import com.raphel.spoc.presentation.adapter.ProductsListAdapter
import com.raphel.spoc.presentation.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@AndroidEntryPoint
class OrderDetailsFragment : Fragment() {
    private var apiKey: String? = null
    private val list = arrayListOf<String?>()

    private var orderId: String? = null
    private  var binding: FragmentOrderDetailsBinding? = null

    private lateinit var adapter: ProductsListAdapter

    private lateinit var ordersViewModel: OrdersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getStringArrayList(ARG_PARAM2)?.let { it1 ->
                list.addAll(it1)
                apiKey = list[0]
                orderId = list[1]
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        ordersViewModel = ViewModelProvider(this)[OrdersViewModel::class.java]
        binding = FragmentOrderDetailsBinding.inflate(layoutInflater)
        adapter = ProductsListAdapter()
        CoroutineScope(Dispatchers.IO).launch{
            val response = ordersViewModel.getOrderDetails(apiKey.toString(),orderId!!.toInt())
//            val response = OrderDetailsDomainModel("Ahmed","MAtaria","Sayed",false,"6548","20/10/21","saddsa","el-ezaby",
//                listOf(
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                    ProductDetailsDomainModel("lelbard","20/10/2024","984651","9546","ketofan",20000),
//                ),"58949849")
            withContext(Dispatchers.Main){
                binding!!.apply {
                    pharmacyNameTv.text = response.pharmacyName
                    agentNameTv.text = response.agentName
                    orderDateTv.text = response.orderDate
                    distributorNameTv.text = response.distributorName
                    branchNameTv.text = response.branchName
                    pharmacyNameAtDistributorTv.text = response.pharmacyCode
                    if (response.expiryGoods){
                        expiredGoodTv.text = "Yes"
                    }
                    else{
                        expiredGoodTv.text ="No"
                    }
                    valueOfExpiredGoodsTv.text = response.valueOfExpiredGoods

                    println(response.productDetailsDomainModels)

                    adapter.submitList(response.productDetailsDomainModels.map { it.copy() }.toMutableList())
                    binding?.productsRecyclerview!!.adapter = adapter
                    binding?.progressBar!!.visibility = View.GONE
                    binding?.wholeLayout!!.visibility = View.VISIBLE
                }
            }
        }
        return binding!!.root


    }

}