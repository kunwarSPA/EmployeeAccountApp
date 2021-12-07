package com.kotlin.employeeaccountapp

import com.employee.data.login.api.Api
import com.employee.data.login.database.AppDatabase
import com.employee.data.login.mapper.EmployeeMapper
import com.employee.data.login.repositoryImpl.EmployeeDetailsRepositoryImpl
import com.employee.data.login.useCaseImpl.EmployeeDetailUseCaseImpl
import com.employee.domain.common.usecase.Callback
import com.employee.domain.login.repository.EmployeeDetailsRepository
import com.employee.domain.login.usecase.EmployeeDetailUseCase
import com.employee.domain.model.EmployeeData
import com.kotlin.employeeaccountapp.dashboard.viewmodel.DashBoardActivityViewModel
import com.kotlin.employeeaccountapp.rx.RxJavaTestHooksResetRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.`when`
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DashBoardActivityViewModelTest {

    @get:Rule
    var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

    @Mock
    lateinit var employeeDetailsRepository: EmployeeDetailsRepository

    @Mock
    lateinit var callback: Callback<EmployeeData>

    lateinit var employeeDetailUseCase: EmployeeDetailUseCase
  //  @Mock lateinit var allUsersUseCaseCallback  : BaseUseCase.Callback<EmployeeData>
    private lateinit var dashBoardActivityViewModel: DashBoardActivityViewModel

    lateinit var  employeeData : EmployeeData
    @Mock
    lateinit var api: Api

    @Mock
    lateinit var appDatabase: AppDatabase

    @Mock
    lateinit var mapper: EmployeeMapper

    @Before
    fun setUp() {
        employeeDetailUseCase  = EmployeeDetailUseCaseImpl(employeeDetailsRepository)
        dashBoardActivityViewModel = DashBoardActivityViewModel(employeeDetailUseCase)
      employeeData = EmployeeData("avatar","a@g.com","first name",1,"last name")



    }

    @Test
    fun `testGetEmployeeDetail`() {

      /*  employeeData = EmployeeData("avatar","a@g.com","first name",1,"last name")
        val employeeDetailsRepositoryImpl = EmployeeDetailsRepositoryImpl(api,appDatabase,mapper)
        employeeDetailUseCase  = EmployeeDetailUseCaseImpl(employeeDetailsRepositoryImpl)
        dashBoardActivityViewModel = DashBoardActivityViewModel(employeeDetailUseCase)

        val callback : Callback<EmployeeData> = callback
        given(employeeDetailsRepository.getUserDetail(id)).willReturn(Single.amb(mock()))

        given(api.getUserDetail(id)).willReturn( Single.just(mock()))

        verify(employeeDetailUseCase.getEmployeeDetail(id,callback).doOnSubscribe { callback.onSuccess(employeeData) })*/
        val id = 1
        val employeeDetailsRepositoryImpl = EmployeeDetailsRepositoryImpl(api,appDatabase,mapper)
        employeeDetailUseCase  = EmployeeDetailUseCaseImpl(employeeDetailsRepositoryImpl)
        val callback : Callback<EmployeeData> = callback
        `when`(api.getUserDetail(id)).thenReturn( Single.just(mock()))
        given(employeeDetailsRepository.getUserDetail(id)).willReturn(Single.just(mock()))
       // `when`(employeeDetailUseCase.getEmployeeDetail(id,callback)).thenReturn(Observable.just(mock()))
        dashBoardActivityViewModel.getEmployeeDetail(id,true)
        verify(callback).onSuccess(mock())
    }

}