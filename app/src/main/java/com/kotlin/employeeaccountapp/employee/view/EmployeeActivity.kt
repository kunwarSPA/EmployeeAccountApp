package com.kotlin.employeeaccountapp.employee.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.employee.domain.login.entity.response.UsersResponse
import com.kotlin.employeeaccountapp.common.utils.hideKeyboard
import com.kotlin.employeeaccountapp.employee.view.adapter.EmployeeAdapter
import com.kotlin.employeeaccountapp.employee.viewmodel.EmployeeActivityViewModel
import com.kotlin.employeeaccountapp.employee.viewmodel.UserStatus
import com.kotlin.employeeaccountapp.databinding.ActivityEmployeeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmployeeActivity : AppCompatActivity() {

    private val viewModel: EmployeeActivityViewModel by viewModels()
    private lateinit var binding: ActivityEmployeeBinding
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.userStatusLiveData.observe(this, ::userStatusUpdate)

        binding.getAllButton.setOnClickListener {
            viewModel.getAllUsers(1)
            it.hideKeyboard(this)
        }

        binding.getUserButton.setOnClickListener {
            val id = binding.userIdInput.text.toString().toInt()

            viewModel.getUser(id)
            it.hideKeyboard(this)
        }

        setRecyclerView()
    }

    private fun userStatusUpdate(result: UserStatus<List<UsersResponse.User>>) {
        when (result) {
            is UserStatus.Loading -> {
                binding.progress.visibility = View.VISIBLE
            }
            is UserStatus.Success -> {
                binding.progress.visibility = View.GONE

                adapter.updateData(result.data)
            }

            is UserStatus.Failure -> {
                binding.progress.visibility = View.GONE
                Toast.makeText(this, "Error: ${result.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = linearLayoutManager
        adapter = EmployeeAdapter(mutableListOf())
        binding.recycler.adapter = adapter
    }
}