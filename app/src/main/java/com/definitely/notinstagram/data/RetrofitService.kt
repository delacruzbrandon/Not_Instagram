package com.definitely.notinstagram.data

import com.definitely.notinstagram.model.ContentModel
import com.definitely.notinstagram.model.DescriptionModel
import com.definitely.notinstagram.model.HomeModel
import com.definitely.notinstagram.service.ApiListener
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService() {
    private val BASE_URL = "https://itunes.apple.com/"

    private var baseApi: ApiListener = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiListener::class.java)

    private var detailsApi: ApiListener = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiListener::class.java)


    fun getResult(): Single<HomeModel> {
        return baseApi.getHomeResults()
    }

    fun getDetails(collectionId: Int): Single<ContentModel> {
        return detailsApi.getDetailsResults(collectionId.toString())
    }

    fun getDescription(collectionId: Int): Single<DescriptionModel> {
        return detailsApi.getDescription(collectionId.toString())
    }
}