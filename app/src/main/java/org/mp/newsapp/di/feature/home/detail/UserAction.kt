package org.mp.newsapp.di.feature.home.detail

import org.mp.newsapp.data.remote.model.User
import org.mp.newsapp.di.mvibase.MviAction


sealed class UserAction : MviAction
{
    object LoadUserAction : UserAction()
    data class ClickAction(val user: User) : UserAction()

}