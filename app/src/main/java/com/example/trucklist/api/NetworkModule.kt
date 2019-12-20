package com.example.trucklist.api


import com.example.trucklist.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@Module()
class NetworkModule {
    @Provides
    @Singleton
    fun providesApi(): ApiClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging);

        val gson = GsonBuilder()
            .create()

        val gitApi = Retrofit.Builder()
            .baseUrl(IOApi.URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(IOApi::class.java)
        return ApiClient(gitApi)
    }
}