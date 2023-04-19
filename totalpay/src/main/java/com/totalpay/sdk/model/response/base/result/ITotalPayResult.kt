/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.base.result

import com.totalpay.sdk.model.api.TotalPayAction
import com.totalpay.sdk.model.api.TotalPayResult
import com.totalpay.sdk.model.api.TotalPayStatus

/**
 * The base response result data holder description.
 * @see com.totalpay.sdk.model.response.base.TotalPayResponse
 */
interface ITotalPayResult {
    /**
     * The action of the transaction.
     */
    val action: TotalPayAction

    /**
     * The result of the transaction.
     */
    val result: TotalPayResult

    /**
     * The status of the transaction.
     */
    val status: TotalPayStatus

    /**
     * Transaction ID in the Merchant’s system.
     */
    val orderId: String

    /**
     * Transaction ID in the Payment Platform.
     */
    val transactionId: String
}
