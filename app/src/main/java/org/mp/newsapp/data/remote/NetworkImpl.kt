package org.mp.newsapp.data.remote



import io.reactivex.Observable
import org.mp.newsapp.data.remote.model.User
import org.mp.newsapp.data.remote.retrofit.NetworkApi

class NetworkImpl(private val networkApi: NetworkApi) : Network {
    override fun loadNews(): Observable<List<Int>> = networkApi.loadNews()
    override fun loadUser(value:Int): Observable<User> = networkApi.loadUser(value)
}