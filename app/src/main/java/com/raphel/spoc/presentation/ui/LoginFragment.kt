package com.raphel.spoc.presentation.ui



import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.raphel.spoc.R
import com.raphel.spoc.databinding.FragmentLoginBinding
import com.raphel.spoc.domain.model.LoginDomainModel
import com.raphel.spoc.presentation.viewmodel.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private  var binding: FragmentLoginBinding? = null


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var emailPattern:String = "[a-zA-Z0-9._-]+@[a-z]+[.]+[a-z]+"

    lateinit var ordersViewModel: OrdersViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ordersViewModel = ViewModelProvider(this)[OrdersViewModel::class.java]
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val layout: View = inflater.inflate(com.raphel.spoc.R.layout.fragment_login, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        binding!!.loginButton.setOnClickListener {
            hideSoftKeyboard(activity!!)
            if(binding!!.emailTextField.text.toString().isEmpty()|| !(binding!!.emailTextField.text.toString().trim().matches(emailPattern.toRegex())))
             {

                 binding!!.emailLayout.helperText = "Invalid E-mail Address"

             }

            else{

                binding!!.emailLayout.helperText = ""

                if (binding!!.passwordTextField.text.toString().isEmpty()||binding!!.passwordTextField.text.toString().length<8){
                    binding!!.passwordLayout.helperText ="Password must be at least 8 characters"
                }


                else{
                    binding!!.emailLayout.helperText = ""
                    binding!!.passwordLayout.helperText = ""

                    if (isOnline(activity!!)) {
                        binding!!.loginButton.isEnabled = false
                        CoroutineScope(Dispatchers.IO).launch {

                            val response = ordersViewModel.logIn(LoginDomainModel(binding!!.emailTextField.text.toString(),binding!!.passwordTextField.text.toString()))
                            withContext(Dispatchers.Main) {
                                binding!!.loginButton.isEnabled = false


                            }
//                            val response = ordersViewModel.logIn(
//                                LoginDomainModel(
//                                    "PhiloTest@gmail.com",
//                                    "Aa@123dsaddsa4"
//                                )
//                            )
                            if (response.isSuccess) {
                                withContext(Dispatchers.Main) {
                                    val bundle = Bundle()
                                    val list = arrayListOf<String>()
                                    list.addAll(listOf(response.message, response.data))
                                    bundle.putStringArrayList(ARG_PARAM1, list)
                                    findNavController().navigate(
                                        R.id.action_loginFragment_to_allPlansFragment,
                                        bundle
                                    )
                                }
                            } else {

                                withContext(Dispatchers.Main) {
                                    binding!!.loginButton.isEnabled = true
                                    Toast.makeText(
                                        activity!!.applicationContext,
                                        "Invalid Access",
                                        Toast.LENGTH_SHORT,
                                    ).show()


                                }
                            }
                        }
                    } else {

                        Toast.makeText(
                            activity!!.applicationContext,
                            "No Internet",
                            Toast.LENGTH_SHORT
                        ).show()


                    }


                }

            }

        }
        return binding!!.root
    }


    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

}