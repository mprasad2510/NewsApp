package org.mp.newsapp.di.feature.home

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import dagger.android.AndroidInjector
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.activity_main.*
import org.mp.newsapp.R
import org.mp.newsapp.di.base.BaseActivity
import org.mp.newsapp.di.feature.home.detail.UserActivity
import org.mp.newsapp.di.mvibase.MviView
import org.mp.newsapp.di.util.gone
import org.mp.newsapp.di.util.visible


class HomeActivity : BaseActivity(), MviView<HomeIntent, HomeViewState> , HasActivityInjector {

    override fun bind() {
        newsRv.layoutManager = LinearLayoutManager(this)

        viewModel.processIntents(intents())
        viewModel.states().observe(this, Observer { if (it != null) render(it) })

    }

    override fun layoutId(): Int = R.layout.activity_main


    @Inject
    lateinit var factory: HomeViewmodelFactory

    private val clickIntent = PublishSubject.create<HomeIntent.ClickIntent>()



    private val viewModel: HomeViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }

    private fun initialIntent(): Observable<HomeIntent.InitialIntent> {
        return Observable.just(HomeIntent.InitialIntent)
    }

    override fun intents(): Observable<HomeIntent> {
        return Observable.merge(initialIntent(), clickIntent)
    }

    override fun render(state: HomeViewState) {
        with(state) {
            if (isLoading) {
                progressBar.visible()
            } else {
                progressBar.gone()
            }
            if (!articles.isEmpty()) {
                newsRv.adapter = NewsAdapter(articles, { clickItem -> clickIntent.onNext(HomeIntent.ClickIntent(clickItem)) })
            }
            if(showShareOption){

                showIntent(articles)
            }


        }
    }
    private var itemPosition: Int = 0
    private var itemValue:Int = 0
    private fun showIntent(articles: List<Int>) {
        for (value in articles) {

            itemValue = value
        }

        for (position in articles.indices) {

            itemPosition = position
        }
            val intent = Intent(this@HomeActivity, UserActivity::class.java)
             with(intent)
             {
                 putExtra("id", itemValue)
                 putExtra("position", itemPosition)
             }
            startActivity(intent)

    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }

}
