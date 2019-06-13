package org.mp.newsapp.di.feature.home.detail
import org.mp.newsapp.data.remote.model.User
import org.mp.newsapp.di.mvibase.MviResult


sealed class UserResult : MviResult {
    sealed class LoadUserResult : UserResult() {
        data class Success(val userList: User) : LoadUserResult()
        data class Failure(val errorMessage: String) : LoadUserResult()
        object InFlight : LoadUserResult()
    }

    data class ClickResult(val user: User) : UserResult()
}