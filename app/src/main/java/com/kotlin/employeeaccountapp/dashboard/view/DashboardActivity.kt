package com.kotlin.employeeaccountapp.dashboard.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.employee.common.utils.PreferencesData
import com.kotlin.employeeaccountapp.R
import com.kotlin.employeeaccountapp.dashboard.editing.EditFragment
import com.kotlin.employeeaccountapp.dashboard.landing.LandingFragment
import com.kotlin.employeeaccountapp.databinding.ActivityDashboardBinding
import com.kotlin.employeeaccountapp.login.view.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity(), OnDashboardCallback {

    @Inject
    lateinit var preferences: PreferencesData
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToDashboardPage()
    }
    override fun navigateToEditPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.gallery_container,
                EditFragment.newInstance(),
                EditFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
    }

    override fun navigateToDashboardPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.gallery_container,
                LandingFragment.newInstance(),
                LandingFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
    }

    override fun navigateToLoginPage() {
        preferences.setLoggedIn("")
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }



}