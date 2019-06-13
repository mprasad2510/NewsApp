package org.mp.newsapp.data.remote


import io.reactivex.Observable
import org.mp.newsapp.data.remote.model.User


interface Network {
    fun loadNews(): Observable<List<Int>>
    fun loadUser(value:Int): Observable<User>
}