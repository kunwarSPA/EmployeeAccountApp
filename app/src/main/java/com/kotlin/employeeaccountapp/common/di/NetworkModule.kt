package com.kotlin.employeeaccountapp.common.di

import com.employee.data.login.api.Api
import com.employee.data.login.repositoryImpl.EmployeeDetailsRepositoryImpl
import com.employee.data.login.repositoryImpl.LoginRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.employee.data.login.repositoryImpl.UserRepositoryImpl
import com.employee.domain.login.repository.EmployeeDetailsRepository
import com.employee.domain.login.repository.LoginRepository
import com.employee.domain.login.repository.UserRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.kotlin.employeeaccountapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    private val baseUrl = "https://reqres.in"

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

    }

   /* @Singleton
    @Provides
    fun providesRetrofitBuilder(
        httpClient: OkHttpClient.Builder,
        convertFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .addConverterFactory(convertFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
*/
    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit.Builder): Api {
        return retrofit.build().create(Api::class.java)
    }

    @Singleton
    @Provides
    fun providesUserRepository(userRepository: UserRepositoryImpl): UserRepository {
        return userRepository
    }

    @Singleton
    @Provides
    fun providesLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository {
        return loginRepository
    }

    @Singleton
    @Provides
    fun providesEmployeeRepository(employeeDetailsRepositoryImpl: EmployeeDetailsRepositoryImpl): EmployeeDetailsRepository {
        return employeeDetailsRepositoryImpl
    }


    @Provides
    @Singleton
    fun providesLoggingInterceptor(): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .build()
    }




    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        httpClient.retryOnConnectionFailure(true)
        return httpClient

    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    /* @Provides
     @Singleton
     fun provideObjectMapper(): ObjectMapper = ObjectMapper()
         .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
         .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
         .registerModule(KotlinModule())
 */



}