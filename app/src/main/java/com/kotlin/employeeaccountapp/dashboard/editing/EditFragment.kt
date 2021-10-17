package com.kotlin.employeeaccountapp.dashboard.editing

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.employee.common.utils.PreferencesData
import com.employee.domain.login.entity.request.EmployeeUpdate
import com.employee.domain.login.entity.response.EmployeeData
import com.employee.domain.login.result.APIResult
import com.kotlin.employeeaccountapp.dashboard.landing.LandingFragment
import com.kotlin.employeeaccountapp.dashboard.view.OnDashboardCallback
import com.kotlin.employeeaccountapp.dashboard.viewmodel.DashBoardActivityViewModel
import com.kotlin.employeeaccountapp.databinding.FragmentDashboardBinding
import com.kotlin.employeeaccountapp.databinding.FragmentEditBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditFragment : Fragment() {

    @Inject
    lateinit var preferences: PreferencesData
    private val viewModel: DashBoardActivityViewModel by viewModels()
    private lateinit var binding: FragmentEditBinding
    private var isAllFabVisible: Boolean? = null
    private var mCallback: OnDashboardCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDashboardCallback) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnDashboardCallback!")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        viewModel.updateUserDataLiveData.observe(viewLifecycleOwner, ::userStatusUpdate)

        binding.saveButton.setOnClickListener{
            if(binding.completeName.text.isEmpty() || binding.userEmail.text.isEmpty()){
                Toast.makeText(activity,"Fields cant be empty",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val employeeUpdate = EmployeeUpdate(binding.completeName.text.toString(),binding.userEmail.text.toString())
            viewModel.updateEmployeeDetail(1,employeeUpdate)
        }

        return binding.root
    }

    private fun userStatusUpdate(result: APIResult<EmployeeUpdate>) {
        print(result)
        when (result) {
            is APIResult.Loading -> {
                print("Loading")
            }
            is APIResult.Success -> {
                Toast.makeText(activity,"Updated Successfully",Toast.LENGTH_LONG).show()
                mCallback?.navigateToDashboardPage()
            }
            is APIResult.Failure -> {
               Toast.makeText(activity,result.message,Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {

        val FRAGMENT_NAME = EditFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            EditFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}