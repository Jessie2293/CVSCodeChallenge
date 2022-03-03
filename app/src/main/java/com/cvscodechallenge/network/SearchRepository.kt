package com.cvscodechallenge.network

import android.util.Log
import com.cvscodechallenge.model.Item
import com.cvscodechallenge.utils.UIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val TAG = "SearchRepository"

class SearchRepository(private val service: APIService) {

    private val localCache = mutableListOf<Item>()

    suspend fun getSearch(tags: String): Flow<UIState> = flow {
        emit(UIState.LOADING())

        val response = service.getSearch(tags)
        Log.d(TAG, "getSearch: $response")
        if (response.isSuccessful) {
            response.body()?.let {
                localCache.clear()
                localCache.addAll(it.items)
                emit(UIState.SUCCESS(it.items))

            } ?: emit(UIState.ERROR(response.message()))
        } else {
            emit(UIState.ERROR(response.message()))
            emit(UIState.LOADING(false))
            Log.e(TAG, "getSearch: ${response.message()}")
        }
    }

    suspend fun getDetailsListItem(position: Int) = flow<UIState>{
        if (localCache.isNotEmpty()) {
            emit(UIState.LOADING())
            delay(500)
            emit(UIState.SUCCESS(listOf(localCache[position])))
        }
    }
}