package com.hatecrab.movies.api

import android.content.Context
import com.google.gson.Gson
import com.hatecrab.movies.R
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.Completable
import rx.Observable
import rx.Single
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class ApiCallAdapterFactory(private val context: Context, private val gson: Gson) : CallAdapter.Factory() {

    val rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create()

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*> {
        return ApiCallAdapter(rxJavaCallAdapterFactory.get(returnType, annotations, retrofit), context, gson)
    }

}

class ApiCallAdapter(val originalCallAdapter: CallAdapter<*>, private val context: Context, private val gson: Gson) : CallAdapter<Any> {

    override fun responseType(): Type = originalCallAdapter.responseType()

    override fun <T : Any?> adapt(call: Call<T>): Any {
        val instance = originalCallAdapter.adapt(call)

        val convertThrowable: (Throwable) -> Throwable =  {
            if (it is SocketTimeoutException || it is ConnectException || it is SocketException || it is UnknownHostException) {
                NetworkError(context.resources.getString(R.string.no_internet_connection), it)
            } else {
                it
            }
        }

        return when (instance) {
            is Observable<*> -> instance.onErrorResumeNext{ Observable.error(convertThrowable(it))}
            is Single<*> -> instance.onErrorResumeNext{ Single.error(convertThrowable(it))}
            is Completable -> instance.onErrorResumeNext { Completable.error(convertThrowable(it)) }
            else -> instance
        }
    }
}

class NetworkError(val msg: String, cause: Throwable): java.lang.RuntimeException(cause) {

    override val message: String?
        get() = msg
}