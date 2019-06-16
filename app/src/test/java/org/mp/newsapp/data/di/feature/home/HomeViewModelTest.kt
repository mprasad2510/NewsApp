package org.mp.newsapp.data.di.feature.home

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mp.newsapp.data.Repository
import org.mp.newsapp.data.remote.model.User
import org.mp.newsapp.di.feature.home.HomeActionProcessorHolder
import org.mp.newsapp.di.feature.home.HomeIntent
import org.mp.newsapp.di.feature.home.HomeViewModel
import org.mp.newsapp.di.feature.home.HomeViewState
import org.mp.newsapp.di.util.scheduler.BaseSchedulerProvider
import org.mp.newsapp.util.scheduler.ImmediateSchedulerProvider

class HomeViewModelTest {
    @Mock
    private lateinit var repository: Repository
    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var homeViewModel: HomeViewModel

    @Mock
    lateinit var observer: Observer<HomeViewState>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUpHomeViewModel() {
        MockitoAnnotations.initMocks(this)
        schedulerProvider = ImmediateSchedulerProvider()
        homeViewModel = HomeViewModel(HomeActionProcessorHolder(repository, schedulerProvider))
        homeViewModel.states().observeForever(observer)
    }

    @Test
    fun InitialIntentTest() {
        val articles = listOf<Int>()
        whenever(repository.loadNews()).thenReturn(Observable.just(articles))
        homeViewModel.processIntents(Observable.just(HomeIntent.InitialIntent))
        verify(observer).onChanged(HomeViewState(
            isLoading = true,
            isError = false,
            errorMessage = "",
            articles = emptyList(),
            showShareOption = false,
            shareArticle = null
        ))
        //TODO: figure out why this viewstate in invoked twice
        verify(observer, times(2)).onChanged(HomeViewState(
            isLoading = false,
            isError = false,
            errorMessage = "",
            articles = articles,
            shareArticle = null,
            showShareOption = false
        ))
    }

    @Test
    fun LoadErrorTest() {
        whenever(repository.loadNews()).thenReturn(Observable.error(Throwable("This is somekind of error")))
        homeViewModel.processIntents(Observable.just(HomeIntent.InitialIntent))
        verify(observer).onChanged(HomeViewState(
            isLoading = false,
            isError = false,
            errorMessage = "",
            articles = emptyList(),
            showShareOption = false,
            shareArticle = null
        ))
        verify(observer).onChanged(
            HomeViewState(
            isLoading = false,
            isError = true,
            errorMessage = "This is somekind of error",
            articles = emptyList(),
            showShareOption = false,
            shareArticle = null

        )
        )
    }

    @Test
    fun onNewsClickTest() {
        val article = User(by = "",descendants=103,id = 20157116 ,kids = listOf(20157116) ,score =596 ,time = 1560271587,title = "The Thing",type = "story",url = "https://en.wikipedia.org/wiki/The_Thing_(listening_device)")
        homeViewModel.processIntents(Observable.just(HomeIntent.ClickIntent(article =
        20157116 )))
        verify(observer).onChanged(HomeViewState(
            isLoading = false,
            isError = false,
            errorMessage = "",
            articles = emptyList(),
            shareArticle = 20157116,
            showShareOption = true
        ))
    }
}