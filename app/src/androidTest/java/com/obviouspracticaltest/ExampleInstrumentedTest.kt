package com.obviouspracticaltest

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.obviouspracticaltest.utils.SharedPreference
import com.obvioustest.ui.NasaPhotosListActivity
import com.obvioustest.utils.StaticUtils
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleInstrumentedTest {

    val appContext = InstrumentationRegistry.getTargetContext()
    @get:Rule
    var activityRule: ActivityTestRule<NasaPhotosListActivity> =
        ActivityTestRule(NasaPhotosListActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        assertEquals("com.obviouspracticaltest", appContext.packageName)
    }

    //This is testing for internet connection..
    @Test
    fun testInternetConnectionAvailable() {
        assertTrue(StaticUtils.isNetworkConnected(appContext))
    }

    @Test
    fun testInternetConnectionNotAvailable() {
        assertFalse(StaticUtils.isNetworkConnected(appContext))
    }

    @Test
    //Test current date
    fun testCurrentDate() {
        assertEquals(SharedPreference(appContext).getCurrenDate(), StaticUtils.getCurrentDate())
    }

    @Test
    fun viewTest() {
        onView(withId(R.id.rvGirid))            // withId(R.id.my_view) is a ViewMatcher

    }

}
