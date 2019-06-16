package org.mp.newsapp.di.feature.home

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.CoreMatchers


@RunWith(AndroidJUnit4::class)
class HomeActivityInstrumentationTest {


     val mActivity: HomeActivity? = null
   // private val resId = R.id.activity_main


    @Rule
    @JvmField
    var rule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    @Test
    fun clickOnItemWithText() {
//        onView(withId(R.id.))
//            .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }

    @RunWith(AndroidJUnit4::class)
    class CustomAssertions {
        companion object {
            val mRecyclerView: RecyclerView? = null
            fun hasItemCount(count: Int): ViewAssertion {
                return RecyclerViewItemCountAssertion(count)
            }
        }

        private class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {

            override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }

                if (view !is RecyclerView) {
                    throw IllegalStateException("The asserted view is not RecyclerView")
                }

                if (view.adapter == null) {
                    throw IllegalStateException("No adapter is assigned to RecyclerView")
                }

                ViewMatchers.assertThat("RecyclerView item count", mRecyclerView?.adapter?.itemCount, CoreMatchers.equalTo(count))
            }
        }
    }

}