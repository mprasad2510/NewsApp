package org.mp.newsapp.di.feature.home.detail

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_user.*
import org.mp.newsapp.R
import org.mp.newsapp.data.remote.model.User
import org.mp.newsapp.di.base.BaseActivity
import org.mp.newsapp.di.feature.home.UserIntent
import org.mp.newsapp.di.mvibase.MviView
import org.mp.newsapp.di.util.gone
import org.mp.newsapp.di.util.visible
import javax.inject.Inject


class UserActivity : BaseActivity(), MviView<UserIntent, UserViewState>, HasActivityInjector {


    companion object {
        var id = 0
    }
    override fun bind() {
        id = intent.getIntExtra("id", 0)
        viewModel.processIntents(intents())
        viewModel.states().observe(this, Observer { if (it != null) render(it) })
    }

    override fun layoutId(): Int {
        val view = R.layout.activity_user

        return view
    }


    @Inject
    lateinit var factory: UserViewModelFactory

    private val clickIntent = PublishSubject.create<UserIntent.ClickIntent>()


    private val viewModel: UserViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, factory).get(UserViewModel::class.java)
    }

    private fun initialIntent(): Observable<UserIntent.InitialIntent> {
        return Observable.just(UserIntent.InitialIntent)
    }

    override fun intents(): Observable<UserIntent> {
        return Observable.merge(initialIntent(), clickIntent)
    }

    override fun render(state: UserViewState) {

        with(state) {
            if (isLoadingUser) {
                progressBar1.visible()
            } else {
                progressBar1.gone()
            }
            text_by.text = StringBuilder().append("By : ").append(userList?.by)
            text_comments.text = StringBuilder().append("Title : ").append(userList?.title)
            text_kids.text =  StringBuilder().append("Kids : ").append(userList?.kids.toString())
            text_parent.text =  StringBuilder().append("Parents : ").append(userList?.score.toString())
            text_time.text = StringBuilder().append("Comments : ").append(userList?.time)

            if (showShareOption) {
                showShareIntent(user)
            }

        }
    }

    private fun showShareIntent(shareArticle: User?) {
        shareArticle?.let {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                shareArticle.title
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}