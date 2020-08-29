package com.joapp.aadpp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IntDef
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.joapp.aadpp.R
import com.joapp.aadpp.data.User
import com.joapp.aadpp.network.ApiService
import com.joapp.aadpp.ui.adapter.LeadersAdapter
import kotlinx.android.synthetic.main.fragment_learning.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeadersFragment : Fragment() {

    @IntDef(FLAG_HOURS, FLAG_SKILL)
    annotation class LearningType

    val apiService: ApiService by lazy { ApiService.create() }
    var flag = FLAG_HOURS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            flag = it.getInt("flag", FLAG_HOURS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learning, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        when (flag) {
            FLAG_HOURS -> loadLearningHours()
            else -> loadLearningSkill()
        }
    }

    fun loadLearningHours() {
        apiService.getHours().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful)
                    recyclerView.adapter = LeadersAdapter(response.body() ?: listOf())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(requireContext(), "Somethnig went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun loadLearningSkill() {
        apiService.getSkilliq().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful)
                    recyclerView.adapter = LeadersAdapter(response.body() ?: listOf(), FLAG_SKILL)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(requireContext(), "Somethnig went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    companion object {

        const val FLAG_HOURS = 0
        const val FLAG_SKILL = 1

        @JvmStatic
        fun newInstance(@LearningType flag: Int = FLAG_HOURS) =
            LeadersFragment().apply {
                arguments = Bundle().apply {
                    putInt("flag", flag)
                }
            }
    }
}