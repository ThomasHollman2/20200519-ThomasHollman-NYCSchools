package com.example.nycschools.model

import com.example.nycschools.SchoolItems
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException
import java.util.concurrent.TimeUnit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

interface SchoolsApiService {
    //https://api.androidhive.info/json/movies.json
    //@Headers("X-App-Token : 93Q4STXpkcnEr7mteCyzb2yeQ")
//    @get:GET("resource/f9bf-2cp4.json")
////    val schools: Call<List<SchoolItems?>?>?

    companion object {
        val instance: SchoolsApiService by lazy {
            //Logging
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            //OkHttp
            val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .writeTimeout(1000, TimeUnit.MILLISECONDS)
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(object : Interceptor {
                        @Throws(IOException::class)
                        override fun intercept(chain: Interceptor.Chain): Response {
                            val original = chain.request()
                            val requestBuilder = original.newBuilder()
                                    .addHeader("Accept", "application/json")
                                    .addHeader("Request-Type", "Android")
                                    .addHeader("Content-Type", "application/json")
                            val request = requestBuilder.build()
                            return chain.proceed(request)
                        }
                    }).build()
            //Retrofit
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://data.cityofnewyork.us/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            retrofit.create(SchoolsApiService::class.java)
        }
    }

    @GET("resource/f9bf-2cp4.json")
    fun getSchools(): Single<List<SchoolItems>>

}