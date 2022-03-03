package com.cvscodechallenge.utils

import com.cvscodechallenge.model.Item

sealed class UIState {
    data class SUCCESS(val response: List<Item>): UIState()
    data class LOADING(val isLoading: Boolean = true): UIState()
    data class ERROR(val errorMessage: String): UIState()
    object EMPTY: UIState()
}