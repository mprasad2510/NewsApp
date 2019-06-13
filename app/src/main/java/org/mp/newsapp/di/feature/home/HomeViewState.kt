package org.mp.newsapp.di.feature.home

import org.mp.newsapp.di.mvibase.MviViewState


data class HomeViewState(val isLoading: Boolean,
                         val errorMessage: String,
                         val isError: Boolean,
                         val articles: List<Int>,
                         val showShareOption: Boolean,
                         val shareArticle: Int?
) : MviViewState {

    companion object {
        fun idle(): HomeViewState {
            return HomeViewState(
                    isLoading = false,
                    isError = false,
                    errorMessage = "",
                    articles = emptyList(),
                    showShareOption = false,
                    shareArticle = null

            )
        }
    }
}