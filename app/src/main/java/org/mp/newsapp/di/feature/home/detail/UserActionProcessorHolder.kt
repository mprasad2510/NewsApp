package org.mp.newsapp.di.feature.home.detail



import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import org.mp.newsapp.data.Repository
import org.mp.newsapp.di.feature.home.detail.UserActivity.Companion.id
import org.mp.newsapp.di.mvibase.MviActionProcessorHolder
import org.mp.newsapp.di.util.scheduler.BaseSchedulerProvider
import javax.inject.Inject

 class UserActionProcessorHolder @Inject constructor(private val repository: Repository,
                                                         private val schedulerProvider: BaseSchedulerProvider
) : MviActionProcessorHolder<UserAction, UserResult> {
    override fun transformFromAction(): ObservableTransformer<UserAction, UserResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                Observable.merge(
                        shared.ofType(UserAction.LoadUserAction::class.java).compose(loadUser()),
                        shared.ofType(UserAction.ClickAction::class.java).compose(shareArticle())

                )
            }
        }
    }


    private fun shareArticle(): ObservableTransformer<UserAction.ClickAction, UserResult.ClickResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                Observable.just(UserResult.ClickResult(it.user))
            }
        }

    }
   //  var id :Int = UserActivity.id
    private fun loadUser(): ObservableTransformer<UserAction.LoadUserAction, UserResult.LoadUserResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                repository.loadUser(20157116)
                        .map { response -> UserResult.LoadUserResult.Success(response)
                        //    Log.d("***ARTICLE ID***","$id")
                        }
                        .cast(UserResult.LoadUserResult::class.java)
                        .onErrorReturn { t ->
                            UserResult.LoadUserResult.Failure(t.localizedMessage)
                        }
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .startWith(UserResult.LoadUserResult.InFlight)

            }
        }

    }


}