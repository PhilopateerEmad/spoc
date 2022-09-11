package com.raphel.spoc.presentation.ui

import android.annotation.SuppressLint
import android.app.Activity

import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.raphel.spoc.R
import com.raphel.spoc.databinding.FragmentAllPlansBinding
import com.raphel.spoc.domain.model.OrderDomainModel
import com.raphel.spoc.presentation.adapter.OrdersListAdapter
import com.raphel.spoc.presentation.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import androidx.appcompat.widget.SearchView
import androidx.compose.ui.text.toLowerCase
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
@SuppressLint("StaticFieldLeak")
private  var binding: FragmentAllPlansBinding? = null

@AndroidEntryPoint
class AllPlansFragment : Fragment(), OrdersListAdapter.ItemClickListener{
    private var apiKey: String? = null
    private val list = arrayListOf<String?>()
    private var tempArray =mutableListOf<OrderDomainModel>()
    private var managerId: String? = null
    private lateinit var adapter: OrdersListAdapter


    private lateinit var ordersViewModel: OrdersViewModel

    private var itemsList = mutableListOf<OrderDomainModel>()
    private val agentsItems = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { it.getStringArrayList(ARG_PARAM1)?.let { it1 -> list.addAll(it1) }
            apiKey = list[0]
            managerId = list[1]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ordersViewModel = ViewModelProvider(this)[OrdersViewModel::class.java]

        binding = FragmentAllPlansBinding.inflate(layoutInflater)

        val layout: View = inflater.inflate(R.layout.fragment_all_plans, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)
        //(activity as AppCompatActivity?)!!.supportActionBar!!.title = ""
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true)
        adapter = OrdersListAdapter(this)

        CoroutineScope(Dispatchers.IO).launch{
            val response = ordersViewModel.getOrdersList(apiKey?:"0", 3/*managerId?.toIntOrNull()?:0*/)
            itemsList.clear()
            agentsItems.clear()

            for(data in response.indices){
                itemsList.add(data,response[data])
                if(!(agentsItems.contains(response[data].agentName))){
                    agentsItems.add(data,response[data].agentName)
                }

            }

            tempArray.addAll(itemsList)
            withContext(Dispatchers.Main){
                adapter.submitList(itemsList.map { it.copy() }.toMutableList())
                binding?.TasksRv!!.adapter = adapter
                binding?.progressBar!!.visibility = View.GONE
                binding?.TasksRv!!.visibility = View.VISIBLE
            }
        }

//        val adapterSearch = ArrayAdapter(requireContext(), R.layout.list_item, agentsItems)
//        (binding!!.agentNameSearch as? AutoCompleteTextView)?.setAdapter(adapterSearch)



        return binding!!.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.menu_item_search,menu)
        val item = menu.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView
//        val adapterSearch = ArrayAdapter(requireContext(), R.layout.list_item, agentsItems)
//        (searchView as? AutoCompleteTextView)?.setAdapter(adapterSearch)
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideSoftKeyboard(activity!!)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tempArray.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if(searchText.isNotBlank()){
                    itemsList.forEach {
                        if(it.agentName.lowercase(Locale.getDefault()).contains(searchText))
                        tempArray.add(it)
                        else{
                            adapter.submitList(emptyList())
                        }
                    }

                    adapter.submitList(tempArray)
                }
                else{
                    tempArray.clear()
                    tempArray.addAll(itemsList)
                    adapter.submitList(tempArray)
                }
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun orderItem(orderId:String) {
        val bundle = Bundle()

        val list = arrayListOf<String>()
        list.addAll(listOf(apiKey.toString(),orderId))
        bundle.putStringArrayList(ARG_PARAM2,list)
        findNavController().navigate(R.id.action_allPlansFragment_to_orderDetailsFragment,bundle)


    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

}









//
//override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//    inflater.inflate(R.menu.menu_item_search,menu)
//    val item = menu.findItem(R.id.search_action)
//    val searchView = item?.actionView as SearchView.SearchAutoComplete
//    val adapterSearch = ArrayAdapter(requireContext(), R.layout.list_item, agentsItems)
//    (searchView as? AutoCompleteTextView)?.setAdapter(adapterSearch)
//    searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
//        override fun onQueryTextSubmit(query: String?): Boolean {
//            hideSoftKeyboard(activity!!)
//            return true
//        }
//
//        override fun onQueryTextChange(newText: String?): Boolean {
//            tempArray.clear()
//            val searchText = newText!!.lowercase(Locale.getDefault())
//            if(searchText.isNotBlank()){
//                itemsList.forEach {
//                    if(it.agentName.lowercase(Locale.getDefault()).contains(searchText))
//                        tempArray.add(it)
//                    else{
//                        adapter.submitList(emptyList())
//                    }
//                }
//
//                adapter.submitList(tempArray)
//            }
//            else{
//                tempArray.clear()
//                tempArray.addAll(itemsList)
//                adapter.submitList(tempArray)
//            }
//            return false
//        }
//
//    })
//    super.onCreateOptionsMenu(menu, inflater)
//}