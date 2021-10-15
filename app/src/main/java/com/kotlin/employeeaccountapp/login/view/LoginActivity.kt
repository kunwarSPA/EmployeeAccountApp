package com.kotlin.employeeaccountapp.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.employee.common.utils.PreferencesData
import com.employee.domain.login.entity.request.EmployeeLogin
import com.kotlin.employeeaccountapp.dashboard.view.DashboardActivity
import com.kotlin.employeeaccountapp.databinding.ActivityLoginBinding
import com.kotlin.employeeaccountapp.login.viewmodel.LoginActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginActivityViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    @Inject
    lateinit var preferences: PreferencesData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loginStatusLiveData.observe(this, ::onLoginStatusUpdate)

        binding.submitButton.setOnClickListener {
            val username = binding.userIdInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val employeeLogin = EmployeeLogin(username,password)
            viewModel.login(employeeLogin)
        }
    }

    private fun onLoginStatusUpdate(loginStatus :String) {
      if(loginStatus.contains("400")){
          Toast.makeText(this,"SOMETHING WENT WRONG",Toast.LENGTH_LONG).show()
      }else{
          preferences.setLoggedIn(loginStatus)
          val intent = Intent(this, DashboardActivity::class.java)
          startActivity(intent)
          finish()

      }
    }
}