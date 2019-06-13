package org.mp.newsapp.di.feature.home.detail

import org.mp.newsapp.data.remote.model.User
import org.mp.newsapp.di.mvibase.MviViewState


data class UserViewState (val isLoading: Boolean,
                          val errorMessage: String,
                          val isError: Boolean,
                          val userList: User?,
                          val showShareOption: Boolean,
                          val user: User?
                          ) : MviViewState
{
    companion object {
        fun idle(): UserViewState {
            return UserViewState(
                    isLoading = false,
                    isError = false,
                    errorMessage = "",
                    userList = null,
                    showShareOption = false,
                    user = null
            )
        }
    }
}