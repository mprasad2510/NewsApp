package org.mp.newsapp.di.feature.home.detail


import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import org.mp.newsapp.di.base.BaseViewModel
import org.mp.newsapp.di.feature.home.UserIntent
import org.mp.newsapp.di.mvibase.MviActionProcessorHolder

class UserViewModel(private val userActionProcessorHolder:
                    UserActionProcessorHolder
) : BaseViewModel<UserIntent, UserViewState, UserAction, UserResult>()
{
    override fun intentFilter(): ObservableTransformer<UserIntent, UserIntent> {
            return ObservableTransformer { intents ->
                intents.publish { shared ->
                    Observable.merge<UserIntent>(
                            shared.ofType(UserIntent.InitialIntent::class.java).take(2),
                            shared.filter { it != UserIntent.InitialIntent }
                    )
                }
            }


        }

    override fun actionFromIntent(intent: UserIntent): UserAction {
        return when (intent) {
            is UserIntent.InitialIntent -> UserAction.LoadUserAction
            is UserIntent.ClickIntent -> UserAction.ClickAction(intent.user)
        }
    }

    override fun actionProcessorHolder(): MviActionProcessorHolder<UserAction, UserResult> = userActionProcessorHolder

    override fun reducer(): BiFunction<UserViewState, UserResult, UserViewState> = reducer

    override fun initialState(): UserViewState = UserViewState.idle()

    init {
        connectObservableToLiveData()
    }
    companion object {
        private val reducer = BiFunction { previousState: UserViewState, result: UserResult ->
            when (result) {
                is UserResult.LoadUserResult -> {
                    when (result) {
                        is UserResult.LoadUserResult.Success -> {
                            previousState.copy(isLoadingUser = false, isError = false, errorMessage = "", userList = result.userList,showShareOption = true)
                        }
                        is UserResult.LoadUserResult.Failure -> {
                            previousState.copy(isLoadingUser = false, isError = true, errorMessage = result.errorMessage)
                        }
                        is UserResult.LoadUserResult.InFlight -> {
                            previousState.copy(isLoadingUser = true, isError = false, errorMessage = "")
                        }
                    }
                }
                is UserResult.ClickResult -> {
                    previousState.copy(showShareOption = true, user = result.user)
                }
            }
        }
    }

}