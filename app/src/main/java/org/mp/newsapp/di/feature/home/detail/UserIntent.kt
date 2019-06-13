package org.mp.newsapp.di.feature.home

import org.mp.newsapp.data.remote.model.User
import org.mp.newsapp.di.mvibase.MviIntent


sealed class UserIntent : MviIntent {
    object InitialIntent : UserIntent()
    data class ClickIntent(val user: User): UserIntent()

}