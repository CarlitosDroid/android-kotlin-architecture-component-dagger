package com.spidev.android_arquitecture_component_demo.di

import dagger.Module
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import com.spidev.android_arquitecture_component_demo.BuildConfig
import com.spidev.android_arquitecture_component_demo.repository.remote.api.WebServiceApi
import com.spidev.android_arquitecture_component_demo.util.BASE_URL
import okhttp3.logging.HttpLoggingInterceptor
import android.app.Application
import okhttp3.Cache
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.spidev.android_arquitecture_component_demo.util.LiveDataCallAdapterFactory


/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/7/18.
 */
@Module
class NetModule {

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideNeoAutoLiteApi(client: Retrofit): WebServiceApi {
        return client.create(WebServiceApi::class.java)
    }

}