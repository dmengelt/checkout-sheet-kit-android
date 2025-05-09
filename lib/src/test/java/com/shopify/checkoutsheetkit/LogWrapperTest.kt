/*
 * MIT License
 *
 * Copyright 2023-present, Shopify Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.shopify.checkoutsheetkit

import android.util.Log
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
class LogWrapperTest {
    private lateinit var log: LogWrapper

    @Before
    fun beforeEach() {
        log = LogWrapper()
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.ERROR
        }
    }

    @After
    fun afterEach() {
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.ERROR
        }
    }

    @Test
    fun `should emit debug logs when LogLevel is DEBUG`() {
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.DEBUG
        }

        log.d("TAG", "Debug message")
        assertThat(ShadowLog.getLogs().any {
            it.type == Log.DEBUG && it.tag == "TAG" && it.msg == "Debug message"
        }).isTrue()
    }

    @Test
    fun `should suppress debug logs when LogLevel is WARN`() {
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.WARN
        }

        log.d("TAG", "Debug message")
        assertThat(ShadowLog.getLogs().any {
            it.type == Log.DEBUG && it.tag == "TAG" && it.msg == "Debug message"
        }).isFalse()
    }

    @Test
    fun `should suppress debug logs when LogLevel is ERROR`() {
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.WARN
        }

        log.d("TAG", "Debug message")
        assertThat(ShadowLog.getLogs().any {
            it.type == Log.DEBUG && it.tag == "TAG" && it.msg == "Debug message"
        }).isFalse()
    }

    @Test
    fun `should emit warn logs when LogLevel is DEBUG`() {
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.DEBUG
        }

        log.w("TAG", "Warn message")
        assertThat(ShadowLog.getLogs().any {
            it.type == Log.WARN && it.tag == "TAG" && it.msg == "Warn message"
        }).isTrue()
    }

    @Test
    fun `should emit warn logs when LogLevel is WARN`() {
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.WARN
        }

        log.w("TAG", "Warn message")
        assertThat(ShadowLog.getLogs().any {
            it.type == Log.WARN && it.tag == "TAG" && it.msg == "Warn message"
        }).isTrue()
    }

    @Test
    fun `should suppress warn logs when LogLevel is ERROR`() {
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.ERROR
        }

        log.w("TAG", "Warn message")
        assertThat(ShadowLog.getLogs().any {
            it.type == Log.WARN && it.tag == "TAG" && it.msg == "Warn message"
        }).isFalse()
    }

    @Test
    fun `should emit error logs when LogLevel is DEBUG`() {
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.DEBUG
        }

        log.e("TAG", "Error message")
        assertThat(ShadowLog.getLogs().any {
            it.type == Log.ERROR && it.tag == "TAG" && it.msg == "Error message"
        }).isTrue()
    }

    @Test
    fun `should emit error logs when LogLevel is WARN`() {
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.WARN
        }

        log.e("TAG", "Error message")
        assertThat(ShadowLog.getLogs().any {
            it.type == Log.ERROR && it.tag == "TAG" && it.msg == "Error message"
        }).isTrue()
    }

    @Test
    fun `should emit error logs when LogLevel is ERROR`() {
        ShopifyCheckoutSheetKit.configure {
            it.logLevel = LogLevel.ERROR
        }

        log.e("TAG", "Error message")
        assertThat(ShadowLog.getLogs().any {
            it.type == Log.ERROR && it.tag == "TAG" && it.msg == "Error message"
        }).isTrue()
    }
}
