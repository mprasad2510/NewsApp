package org.mp.newsapp.di.feature.home

import org.mp.newsapp.di.mvibase.MviIntent


sealed class HomeIntent : MviIntent {
    object InitialIntent : HomeIntent()
    data class ClickIntent(val article: Int):HomeIntent()
}