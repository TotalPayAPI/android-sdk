/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.base.result

import java.util.*

/**
 * The base response details result data holder description.
 * @see com.totalpay.sdk.model.response.base.TotalPayResponse
 * @see IOrderTotalPayResult
 */
interface IDetailsTotalPayResult : IOrderTotalPayResult {
    /**
     * Transaction date in the Payment Platform.
     */
    val transactionDate: Date

    /**
     * Descriptor from the bank, the same as cardholder will see in the bank statement. Optional.
     */
    val descriptor: String?
}
