package com.obviouspracticaltest

import android.content.Context
import androidx.test.InstrumentationRegistry
import com.obvioustest.utils.StaticUtils
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    var instrumentationContext: Context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun internet_available(){
        assertTrue("Internet Available", StaticUtils.isNetworkConnected(instrumentationContext))
        assertFalse("Internet Not Available",StaticUtils.isNetworkConnected(instrumentationContext))
    }
}
