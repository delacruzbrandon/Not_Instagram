package com.definitely.notinstagram.view.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.definitely.notinstagram.data.RetrofitService
import com.definitely.notinstagram.model.ContentModel
import com.definitely.notinstagram.model.ContentModelResult
import com.definitely.notinstagram.model.DescriptionModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

private const val TAG = "DetailsViewModel"
class DetailsViewModel(collectionId: Int): ViewModel() {

    private val retrofitServices = RetrofitService()
    private val detailsDisposable = CompositeDisposable()

    val _collectionId = MutableLiveData<Int>()
    val collectionId: LiveData<Int>
    get() = _collectionId

    val _linkContents = MutableLiveData<List<ContentModelResult>>()
    val linkContents: LiveData<List<ContentModelResult>>
    get() = _linkContents

    val _description = MutableLiveData<DescriptionModel>()
    val description: LiveData<DescriptionModel>
    get() = _description

    init {
        _collectionId.value = collectionId

        Log.d(TAG, "${_collectionId.value}")
    }



    fun fetchDetailsData() {
        detailsDisposable.add(
            retrofitServices.getDetails(_collectionId.value!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ContentModel>() {
                    override fun onSuccess(t: ContentModel) {
                        Log.d(TAG, "onSuccess: ${t.results[0]}")

                        _linkContents.value = t.results
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "onError: ${e.stackTrace}")
                        Log.d(TAG, "onError: ${e.cause}")
                        Log.d(TAG, "onError: ${e.message}")
                        Log.d(TAG, "onError: ${e.localizedMessage}")
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
    }
}