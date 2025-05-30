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
package com.shopify.checkout_sdk_mobile_buy_integration_sample.cart.data

import androidx.compose.runtime.Stable
import com.shopify.checkout_sdk_mobile_buy_integration_sample.common.ID

sealed class CartState {
    data object Empty : CartState()

    @Stable
    data class Cart(
        val cartID: ID,
        val cartLines: List<CartLine>,
        val cartTotals: CartTotals,
        val checkoutUrl: String,
    ) : CartState()

}

data class CartLine(
    val id: ID,
    val title: String,
    val vendor: String,
    val quantity: Int,
    val variantDescription: String,
    val image: CartLineImage?,
    val pricePerQuantity: Double,
    val currencyPerQuantity: String,
    val totalPrice: Double,
    val totalCurrency: String,
)

data class CartLineImage(
    val url: String,
    val altText: String?,
)

data class CartTotals(
    val totalQuantity: Int,
    val totalAmount: CartAmount,
    val totalAmountEstimated: Boolean,
)

data class CartAmount(
    val currency: String,
    val price: Double,
)

val CartState.totalQuantity
    get() = when (this) {
        is CartState.Empty -> 0
        is CartState.Cart -> cartTotals.totalQuantity
    }
