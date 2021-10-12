package com.kotlin.employeeaccountapp.dashboard.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.entity.response.UsersResponse
import com.employee.domain.login.result.APIResult
import com.kotlin.employeeaccountapp.account.view.AccountActivity
import com.kotlin.employeeaccountapp.dashboard.viewmodel.DashBoardActivityViewModel
import com.kotlin.employeeaccountapp.databinding.ActivityDashboardBinding
import com.kotlin.employeeaccountapp.employee.viewmodel.UserStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashBoardActivityViewModel by viewModels()
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.userDataLiveData.observe(this, ::userStatusUpdate)
        viewModel.getEmployeeDetail(1)
    }

    override fun onResume() {
        super.onResume()
    }


    private fun userStatusUpdate(result: APIResult<EmployeeData>) {
        print(result)
        when (result) {
            is APIResult.Loading -> {
                print("Loading")
            }
            is APIResult.Success -> {
                populateEmployeeInitials(result.data)
            }
            is APIResult.Failure -> {
                print("Loading")
            }
        }
    }


    private fun populateEmployeeInitials(employeeData: EmployeeData){
        binding.nameText.text  = "Welcome "+employeeData.data.first_name
        binding.completeName.text = employeeData.data.first_name + "  "+employeeData.data.last_name
        binding.userEmail.text =employeeData.data.email
        Glide.with(this).load(employeeData.data.avatar).centerCrop().into(binding.userIcon)

    }
}