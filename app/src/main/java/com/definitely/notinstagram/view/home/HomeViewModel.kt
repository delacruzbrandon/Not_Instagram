package com.definitely.notinstagram.view.home

import android.telecom.Call
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.definitely.notinstagram.data.RetrofitService
import com.definitely.notinstagram.model.HomeModel
import com.definitely.notinstagram.model.HomeModelResult
import com.definitely.notinstagram.service.ApiListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

private const val TAG = "HomeViewModel"
class HomeViewModel: ViewModel() {

    private val retrofitServices = RetrofitService()
    private val homeDisposable = CompositeDisposable()

    private val _imageModel = MutableLiveData<List<HomeModelResult>>()
    val imageModel: LiveData<List<HomeModelResult>>
        get() = _imageModel


    init {
    }

    fun fetchHomeData() {
        homeDisposable.add(
            retrofitServices.getResult()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<HomeModel>() {
                    override fun onSuccess(t: HomeModel) {
                        _imageModel.value = t.results
                        Log.d(TAG, "onSuccess: ${t.results}")
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: ${e.cause}")
                        Log.d(TAG, "onError: ${e.localizedMessage}")
                        Log.d(TAG, "onError: ${e.message}")
                        Log.d(TAG, "onError: ${e.stackTrace}")
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}