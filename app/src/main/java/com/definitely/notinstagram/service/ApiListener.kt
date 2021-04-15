package com.definitely.notinstagram.service

import com.definitely.notinstagram.model.ContentModel
import com.definitely.notinstagram.model.DescriptionModel
import com.definitely.notinstagram.model.HomeModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiListener {
    @GET("search?term=a&amp;country=au&amp;media=movie&amp;all")
    fun getHomeResults(): Single<HomeModel>

    @GET("lookup")
    fun getDetailsResults(@Query("id") collectionId: String): Single<ContentModel>

    @GET("lookup")
    fun getDescription(@Query("id") collectionId: String): Single<DescriptionModel>
}