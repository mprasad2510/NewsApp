package org.mp.newsapp.util.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.mp.newsapp.di.util.scheduler.BaseSchedulerProvider

/**
 * Implementation of the [BaseSchedulerProvider] making all [Scheduler]s immediate.
 */
class ImmediateSchedulerProvider : BaseSchedulerProvider {
  override fun computation(): Scheduler = Schedulers.trampoline()

  override fun io(): Scheduler = Schedulers.trampoline()

  override fun ui(): Scheduler = Schedulers.trampoline()
}
