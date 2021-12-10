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
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.`when`
import org.mockito.BDDMockito.anyInt
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DashBoardActivityViewModelTest {

    @get:Rule
    var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

    @Mock
    lateinit var employeeDetailsRepository: EmployeeDetailsRepository

    @Mock
    lateinit var callback: Callback<EmployeeData>

    @Mock
    lateinit var employeeDetailUseCase: EmployeeDetailUseCase
  //  @Mock lateinit var allUsersUseCaseCallback  : BaseUseCase.Callback<EmployeeData>
    private lateinit var dashBoardActivityViewModel: DashBoardActivityViewModel
    @Mock
    lateinit var  employeeData : EmployeeData
    @Mock
    lateinit var api: Api

    @Mock
    lateinit var appDatabase: AppDatabase

    @Mock
    lateinit var mapper: EmployeeMapper

    @Mock
    lateinit var employeeDetailsRepositoryImpl: EmployeeDetailsRepositoryImpl
/*
    @BeforeClass
    fun setUpClass() {

        // Override the default "out of the box" AndroidSchedulers.mainThread() Scheduler
        //
        // This is necessary here because otherwise if the static initialization block in AndroidSchedulers
        // is executed before this, then the Android SDK dependent version will be provided instead.
        //
        // This would cause a java.lang.ExceptionInInitializerError when running the test as a
        // Java JUnit test as any attempt to resolve the default underlying implementation of the
        // AndroidSchedulers.mainThread() will fail as it relies on unavailable Android dependencies.

        // Comment out this line to see the java.lang.ExceptionInInitializerError
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { callable: Callable<Scheduler?>? -> Schedulers.trampoline() }
    }*/

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
      //  employeeDetailUseCase  = EmployeeDetailUseCaseImpl(employeeDetailsRepository)
        dashBoardActivityViewModel = DashBoardActivityViewModel(employeeDetailUseCase)
        employeeData = EmployeeData("avatar", "a@g.com", "first name", 1, "last name")



    }

    @Test
    fun `testGetEmployeeDetail`() {
        employeeDetailsRepositoryImpl = (EmployeeDetailsRepositoryImpl(api, appDatabase, mapper))
        employeeDetailUseCase  = EmployeeDetailUseCaseImpl(employeeDetailsRepositoryImpl)
        this.callback.onSuccess(employeeData)
        `when`(api.getUserDetail(anyInt())).thenReturn(Single.just(mock()))
        Assert.assertNotNull(employeeDetailUseCase.getEmployeeDetail(1, callback))
    }

}