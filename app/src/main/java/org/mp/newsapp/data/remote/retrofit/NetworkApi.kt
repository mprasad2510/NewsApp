package org.mp.newsapp.data.remote.retrofit


import io.reactivex.Observable
import org.mp.newsapp.data.remote.model.User
import retrofit2.http.*


interface NetworkApi {
    companion object {
        const val BASE_URL = "https://hacker-news.firebaseio.com/v0/"
    }

    @GET("topstories.json")
    fun loadNews (): Observable<List<Int>>

    @GET("item/{id}.json")
    fun loadUser(@Path("id") value:Int): Observable<User>
}

