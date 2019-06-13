package org.mp.newsapp.data

import io.reactivex.Observable
import org.mp.newsapp.data.remote.Network
import org.mp.newsapp.data.remote.model.User
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val network: Network) : Repository {
    override fun loadNews(): Observable<List<Int>> {
        return network.loadNews()
    }

    override fun loadUser(value:Int): Observable<User> {
        return network.loadUser(value)
    }
}