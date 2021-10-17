package com.kotlin.employeeaccountapp.dashboard.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.employee.common.utils.PreferencesData
import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.result.APIResult
import com.kotlin.employeeaccountapp.R
import com.kotlin.employeeaccountapp.dashboard.editing.EditFragment
import com.kotlin.employeeaccountapp.dashboard.landing.LandingFragment
import com.kotlin.employeeaccountapp.dashboard.viewmodel.DashBoardActivityViewModel
import com.kotlin.employeeaccountapp.databinding.ActivityDashboardBinding
import com.kotlin.employeeaccountapp.login.view.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity(), OnDashboardCallback  {

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
        TODO("Not yet implemented")
    }



}