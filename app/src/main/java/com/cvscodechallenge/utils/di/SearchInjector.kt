package com.cvscodechallenge.utils.di

import com.cvscodechallenge.network.APIService
import com.cvscodechallenge.network.SearchRepository
import com.cvscodechallenge.viewmodel.SearchViewModel
import com.cvscodechallenge.viewmodel.SearchViewModelFactory

object SearchInjector {

    private val repository: SearchRepository by lazy {
        SearchRepository(APIService.getRetrofit())
    }

    private fun provideSearchViewModelFactory() = SearchViewModelFactory(repository = repository)

    val viewModel: SearchViewModel by lazy {
        provideSearchViewModelFactory().create(SearchViewModel::class.java)
    }
}