/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.base.result

/**
 * The base response order result data holder description.
 * @see com.totalpay.sdk.model.response.base.TotalPayResponse
 * @see ITotalPayResult
 */
interface IOrderTotalPayResult : ITotalPayResult {
    /**
     * Amount of capture.
     */
    val orderAmount: Double

    /**
     * Currency.
     */
    val orderCurrency: String
}
