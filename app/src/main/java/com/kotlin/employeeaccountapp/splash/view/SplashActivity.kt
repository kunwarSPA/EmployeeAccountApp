package com.kotlin.employeeaccountapp.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.kotlin.employeeaccountapp.dashboard.view.DashboardActivity
import com.kotlin.employeeaccountapp.databinding.ActivitySplashBinding
import com.kotlin.employeeaccountapp.login.view.LoginActivity
import com.kotlin.employeeaccountapp.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity(){
    private val viewModel: SplashViewModel by viewModels()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loginStatusLiveData.observe(this) {
            when (it) {
                true -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                }
                false -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
            finish()
        }
    }
}