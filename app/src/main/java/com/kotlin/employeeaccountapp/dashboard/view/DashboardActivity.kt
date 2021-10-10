package com.kotlin.employeeaccountapp.dashboard.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.kotlin.employeeaccountapp.account.view.AccountActivity
import com.kotlin.employeeaccountapp.dashboard.viewmodel.DashBoardActivityViewModel
import com.kotlin.employeeaccountapp.employee.view.EmployeeActivity
import com.kotlin.employeeaccountapp.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashBoardActivityViewModel by viewModels()
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.userDataLiveData.observe(this) {
            val username = it?.username
            binding.nameText.text = "Welcome back $username"
        }

        viewModel.notificationLiveData.observe(this) {
            val notification = it
            binding.notificationText.text = "You have $notification unread notifications!"
        }

        binding.accountButton.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }

        binding.coworkersButton.setOnClickListener {
            startActivity(Intent(this, EmployeeActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotifications()
    }
}