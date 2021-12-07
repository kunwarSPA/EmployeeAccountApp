package com.kotlin.employeeaccountapp.dashboard.landing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.employee.common.utils.PreferencesData
import com.employee.domain.login.result.APIResult
import com.employee.domain.model.EmployeeData
import com.kotlin.employeeaccountapp.dashboard.view.OnDashboardCallback
import com.kotlin.employeeaccountapp.dashboard.viewmodel.DashBoardActivityViewModel
import com.kotlin.employeeaccountapp.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LandingFragment : Fragment() {
    @Inject
    lateinit var preferences: PreferencesData
    private val viewModel: DashBoardActivityViewModel by viewModels()
    private lateinit var binding: FragmentDashboardBinding
    private var isAllFabVisible: Boolean? = null
    private var mCallback: OnDashboardCallback? = null



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDashboardCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnDashboardCallback!")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        viewModel.userDataLiveData.observe(viewLifecycleOwner, ::userStatusUpdate)
        viewModel.getEmployeeDetail(1, true)
        binding.editFab.visibility = View.GONE
        binding.editFabTv.visibility = View.GONE
        binding.logoutFab.visibility = View.GONE
        binding.logoutTv.visibility = View.GONE
        isAllFabVisible = false
        binding.addFab.shrink()
        binding.addFab.setOnClickListener {
            isAllFabVisible = if (!isAllFabVisible!!) {
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
            mCallback?.navigateToEditPage()
        }
        binding.logoutFab.setOnClickListener {

            mCallback?.navigateToLoginPage()
        }
        return binding.root
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
                Toast.makeText(activity,result.message,Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun populateEmployeeInitials(employeeData: EmployeeData) {
        binding.nameText.text = "Welcome " + employeeData.first_name
        binding.completeName.text = employeeData.first_name + "  " + employeeData.last_name
        binding.userEmail.text = employeeData.email
        Glide.with(this).load(employeeData.avatar).centerCrop().into(binding.userIcon)
            .clearOnDetach()

    }

    companion object {

        val FRAGMENT_NAME = LandingFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            LandingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}