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
import com.kotlin.employeeaccountapp.dashboard.viewmodel.DashBoardActivityViewModel
import com.kotlin.employeeaccountapp.databinding.ActivityDashboardBinding
import com.kotlin.employeeaccountapp.login.view.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

  /*  var mAddAlarmFab: FloatingActionButton? = null
     var mAddPersonFab:FloatingActionButton? = null
    var mAddFab: ExtendedFloatingActionButton? = null
    var addAlarmActionText: TextView? = null
    var addPersonActionText:TextView? = null*/
    @Inject
    lateinit var preferences: PreferencesData
    private val viewModel: DashBoardActivityViewModel by viewModels()
    private lateinit var binding: ActivityDashboardBinding
    var isAllFabsVisible: Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.userDataLiveData.observe(this, ::userStatusUpdate)
        viewModel.getEmployeeDetail(1)
        binding.editFab.visibility = View.GONE;
        binding.editFabTv.visibility = View.GONE;
        binding.logoutFab.visibility = View.GONE;
        binding.logoutTv.visibility = View.GONE;
        isAllFabsVisible = false;
        binding.addFab.shrink()
        binding.addFab.setOnClickListener {
            isAllFabsVisible = if (!isAllFabsVisible!!) {
                binding.logoutFab.show()
                binding.editFab.show()
                binding.editFabTv.visibility = View.VISIBLE
                binding.logoutTv.visibility = View.VISIBLE
                binding.addFab.extend()
                true
            } else {
                binding.editFab.hide()
                binding.logoutFab.hide()
                binding.editFabTv.visibility = View.GONE
                binding.logoutTv.visibility = View.GONE
                binding.addFab.shrink()
                false
            }
        }
        binding.editFab.setOnClickListener {
            Toast.makeText(
                this, "Edit Clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.logoutFab.setOnClickListener {

            preferences.setLoggedIn("")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
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