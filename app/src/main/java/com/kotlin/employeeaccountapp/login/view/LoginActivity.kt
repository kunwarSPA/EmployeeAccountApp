package com.kotlin.employeeaccountapp.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.kotlin.employeeaccountapp.login.viewmodel.LoginActivityViewModel
import com.kotlin.employeeaccountapp.login.viewmodel.LoginStatus
import com.kotlin.employeeaccountapp.dashboard.view.DashboardActivity
import com.kotlin.employeeaccountapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginActivityViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loginStatusLiveData.observe(this, ::onLoginStatusUpdate)

        binding.submitButton.setOnClickListener {
            val username = binding.userIdInput.text.toString()
            val password = binding.passwordInput.text.toString()

            viewModel.login(username, password)
        }
    }

    private fun onLoginStatusUpdate(loginStatus: LoginStatus) {
        when (loginStatus) {
            is LoginStatus.Success -> {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
            is LoginStatus.Failure -> {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}