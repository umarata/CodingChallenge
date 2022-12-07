package com.example.coding_challenge.di

import com.example.coding_challenge.BuildConfig
import com.example.coding_challenge.data.network.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CodingChallengeModule {

    private const val BASE_URL = BuildConfig.BASE_URL
    private const val timeout = 5 * 1000L

    /**
     * This function providesHttpLoggingInterceptor() provides the singleton reference for HttpLoggingInterceptor
     */
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * This function okHttpInstance(httpLoggingInterceptor: HttpLoggingInterceptor) provides the singleton reference for OkHttpClient
     */
    @Singleton
    @Provides
    fun okHttpInstance(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient
            .Builder()
        /**
         * setting HttpLoggingInterceptor only when app is running in debug mode
         */
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    /**
     * This function retrofitInstance(okHttpClient: OkHttpClient) provides the singleton reference for Retrofit
     */
    @Singleton
    @Provides
    fun retrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    /**
     * This function provideApiService(retrofit: Retrofit) provides the singleton reference for WebService
     */
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)


}