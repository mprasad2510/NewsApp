package org.mp.newsapp.di.feature.home

import org.mp.newsapp.di.mvibase.MviResult

sealed class HomeResult : MviResult {
    sealed class LoadHomeResult : HomeResult() {
        data class Success(val newsList: List<Int>) : LoadHomeResult()
        data class Failure(val errorMessage: String) : LoadHomeResult()
        object InFlight : LoadHomeResult()
    }

    data class ClickResult(val article: Int) : HomeResult()
}