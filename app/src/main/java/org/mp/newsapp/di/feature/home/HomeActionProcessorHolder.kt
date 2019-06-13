package org.mp.newsapp.di.feature.home

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import org.mp.newsapp.data.Repository
import org.mp.newsapp.di.mvibase.MviActionProcessorHolder
import org.mp.newsapp.di.util.scheduler.BaseSchedulerProvider
import javax.inject.Inject

class HomeActionProcessorHolder @Inject constructor(private val repository: Repository,
                                                    private val schedulerProvider: BaseSchedulerProvider
)
                                                  : MviActionProcessorHolder<HomeAction, HomeResult> {
    override fun transformFromAction(): ObservableTransformer<HomeAction, HomeResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                Observable.merge(
                        shared.ofType(HomeAction.LoadHomeAction::class.java).compose(loadHome()),
                        shared.ofType(HomeAction.ClickAction::class.java).compose(shareArticle())

                )
            }
        }
    }


    private fun shareArticle(): ObservableTransformer<HomeAction.ClickAction, HomeResult.ClickResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                Observable.just(HomeResult.ClickResult(it.article))
            }
        }

    }

    private fun loadHome(): ObservableTransformer<HomeAction.LoadHomeAction, HomeResult.LoadHomeResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                repository.loadNews()
                        .map { response -> HomeResult.LoadHomeResult.Success(response) }
                        .cast(HomeResult.LoadHomeResult::class.java)
                        .onErrorReturn { t ->
                            HomeResult.LoadHomeResult.Failure(t.localizedMessage)
                        }
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .startWith(HomeResult.LoadHomeResult.InFlight)
            }
        }

    }


}