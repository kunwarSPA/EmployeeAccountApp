package com.kotlin.employeeaccountapp.splash.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.kotlin.employeeaccountapp.R
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
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token: String? = task.result

                // Log and toast
                val msg = getString(R.string.app_token)
                Log.d("TAG", msg)
                Toast.makeText(this, msg + " " + token, Toast.LENGTH_SHORT).show()

            })
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