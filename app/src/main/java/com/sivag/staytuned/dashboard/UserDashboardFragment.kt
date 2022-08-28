package com.sivag.staytuned.dashboard


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sivag.staytuned.base.BaseFragment
import com.sivag.staytuned.data.Api
import com.sivag.staytuned.data.model.ContactsModel
import com.sivag.staytuned.databinding.FragmentUserdashboardBinding
import kotlinx.android.synthetic.main.fragment_userdashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Siva G Gurusamy on 26/Aug/2022
 * email : sivaguru3161@gmail.com
 */


class UserDashboardFragment : BaseFragment() {

    private lateinit var binding: FragmentUserdashboardBinding

    lateinit var listContactsAdapter: ListContactsAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserdashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvContacts.setHasFixedSize(false)
        linearLayoutManager = LinearLayoutManager(requireContext())
        rvContacts.layoutManager = linearLayoutManager

        getContactsData()
    }


    private fun getContactsData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/api/")
            .build()
            .create(Api::class.java)
        val retrofitData = retrofitBuilder.getContactsData()

        retrofitData.enqueue(object : Callback<ContactsModel?> {
            override fun onResponse(call: Call<ContactsModel?>, response: Response<ContactsModel?>) {
                val responseBody = response.body()

                listContactsAdapter = ListContactsAdapter(requireContext(),responseBody)
                listContactsAdapter.notifyDataSetChanged()
                rvContacts.adapter = listContactsAdapter

            }

            override fun onFailure(call: Call<ContactsModel?>, t: Throwable) {
                Log.d("SIVAG :- Contacts RecyclerView", "onFailure: " + t.message)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserDashboardFragment()
    }

}