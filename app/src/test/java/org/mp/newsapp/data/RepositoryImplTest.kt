package org.mp.newsapp.data

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mp.newsapp.data.remote.Network
import org.mp.newsapp.data.remote.model.User

class RepositoryImplTest {
    @Mock
    private lateinit var network: Network
    private lateinit var repository: RepositoryImpl
    @Before
    fun setUpRepository() {
        MockitoAnnotations.initMocks(this)
        repository = RepositoryImpl(network)
    }


    @Test
    fun loadPosts() {
        val articles = listOf<Int>()
        whenever(network.loadNews()).thenReturn(Observable.just(articles))
        repository.loadNews()
        Mockito.verify<Network>(network).loadNews()
    }

    @Test
    fun loadComments() {
        val user = User(by = "",descendants=103,id = 20157116 ,kids = listOf(20157116) ,score =596 ,time = 1560271587,title = "The Thing",type = "story",url = "https://en.wikipedia.org/wiki/The_Thing_(listening_device)")
        whenever(network.loadUser(20157116)).thenReturn(Observable.just(user))
        repository.loadUser(20157116)
        Mockito.verify<Network>(network).loadUser(20157116)
    }

}