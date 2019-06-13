package org.mp.newsapp.di.feature.home

import org.mp.newsapp.di.mvibase.MviAction


sealed class HomeAction : MviAction {
    object LoadHomeAction : HomeAction()
    data class ClickAction(val article: Int) : HomeAction()
}