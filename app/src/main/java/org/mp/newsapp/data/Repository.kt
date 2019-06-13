package org.mp.newsapp.data

import io.reactivex.Observable
import org.mp.newsapp.data.remote.model.User


interface Repository {
     fun loadNews(): Observable<List<Int>>
     fun loadUser(value:Int): Observable<User>
}