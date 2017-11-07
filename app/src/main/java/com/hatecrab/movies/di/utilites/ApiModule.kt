package com.hatecrab.movies.di.utilites

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hatecrab.movies.api.ApiCallAdapterFactory
import com.hatecrab.movies.api.ApiResponseConverterFactory
import com.hatecrab.movies.api.IRemoteApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS)

        builder.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "b0fc4c075392638bf2e7cdd923e9e42c")
                    .build()

            val requestBuilder = original.newBuilder().url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val hli = HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }
        hli.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(hli)

        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient, apiCallAdapterFactory: ApiCallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(ApiResponseConverterFactory(gson))
                .addCallAdapterFactory(apiCallAdapterFactory)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideApiCallAdapterFactory(context: Context, gson: Gson): ApiCallAdapterFactory {
        return ApiCallAdapterFactory(context, gson)
    }

    @Provides
    @Singleton
    internal fun provideRemoteApi(retrofit: Retrofit): IRemoteApi {
        return retrofit.create(IRemoteApi::class.java)
    }
}
