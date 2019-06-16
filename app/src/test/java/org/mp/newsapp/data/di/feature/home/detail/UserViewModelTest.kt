package org.mp.newsapp.data.di.feature.home.detail

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Maybe
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mp.newsapp.data.Repository
import org.mp.newsapp.data.remote.model.User
import org.mp.newsapp.di.feature.home.UserIntent
import org.mp.newsapp.di.feature.home.detail.UserActionProcessorHolder
import org.mp.newsapp.di.feature.home.detail.UserViewModel
import org.mp.newsapp.di.feature.home.detail.UserViewState
import org.mp.newsapp.di.util.scheduler.BaseSchedulerProvider
import org.mp.newsapp.util.scheduler.ImmediateSchedulerProvider
import java.net.SocketException

class UserViewModelTest {

    @Mock
    private lateinit var repository: Repository
    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var userViewModel: UserViewModel

    @Mock
    lateinit var observer: Observer<UserViewState>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUpHomeViewModel() {
        MockitoAnnotations.initMocks(this)
        schedulerProvider = ImmediateSchedulerProvider()
        userViewModel = UserViewModel((UserActionProcessorHolder(repository, schedulerProvider)))
        userViewModel.states().observeForever(observer)
    }

    @Test
    fun InitialIntentTest() {
        val comments = User(
            by = "cos2pi",
            descendants = 103,
            id = 20157116,
            kids = listOf(20157116),
            score = 596,
            time = 1560271587,
            title = "The Thing",
            type = "story",
            url = "https://en.wikipedia.org/wiki/The_Thing_(listening_device)"
        )
        whenever(repository.loadUser(20157116)).thenReturn(Observable.just(comments))
        userViewModel.processIntents(Observable.just(UserIntent.InitialIntent))
        verify(observer).onChanged(
            UserViewState(
                isLoadingUser = false,
                isError = false,
                errorMessage = "",
                userList = null,
                showShareOption = false,
                user = null
            )
        )
    }

    @Test
    fun LoadErrorTest() {
        whenever(repository.loadUser(20157116)).thenReturn(Observable.error(Throwable("This is somekind of error")))
        userViewModel.processIntents(Observable.just(UserIntent.InitialIntent))
        verify(observer).onChanged(
            UserViewState(
                isLoadingUser = false,
                isError = false,
                errorMessage = "",
                userList = null,
                showShareOption = false,
                user = null
            )
        )
        verify(observer).onChanged(
            UserViewState(
                isLoadingUser = false,
                isError = true,
                errorMessage = "This is somekind of error",
                userList = null,
                showShareOption = false,
                user = null
            )
        )


        //
//
//        Mockito.`when`(this.repository.loadUser(20157116)).thenAnswer {
//            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
//        }
//        // Mocked Observer
//        val observer = mock(Observer::class.java) as Observer<User>
//        this.userViewModel.repositoriesLiveData.observeForever(observer)
//        // Invoke
//        this.userViewModel.fetchUserRepositories(ArgumentMatchers.anyString())
//        // Assertions
//        assertNotNull(this.userViewModel.repositoriesLiveData.value)
//        assertEquals(LiveDataResult.Status.ERROR, this.userViewModel.repositoriesLiveData.value?.status)
//        assert(this.userViewModel.repositoriesLiveData.value?.err is Throwable)
//    }
    }
}