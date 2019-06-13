package org.mp.newsapp.di.mvibase

import io.reactivex.ObservableTransformer

interface MviActionProcessorHolder<I: MviAction, R: MviResult>{
    fun transformFromAction(): ObservableTransformer<I, R>
}