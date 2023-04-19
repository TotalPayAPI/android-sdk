/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.gettransactionstatus

import com.totalpay.sdk.feature.adapter.TotalPayCallback
import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.totalpay.sdk.model.response.base.result.ITotalPayResult

/**
 * The response of the [com.totalpay.sdk.feature.adapter.TotalPayGetTransactionStatusAdapter].
 * @see com.totalpay.sdk.model.response.base.TotalPayResponse
 */
typealias TotalPayGetTransactionStatusResponse =
        TotalPayResponse<TotalPayGetTransactionStatusResult>

/**
 * The callback of the [com.totalpay.sdk.feature.adapter.TotalPayGetTransactionStatusAdapter].
 * @see com.totalpay.sdk.feature.adapter.TotalPayCallback
 */
typealias TotalPayGetTransactionStatusCallback =
        TotalPayCallback<TotalPayGetTransactionStatusResult, TotalPayGetTransactionStatusResponse>

/**
 * The result of the [com.totalpay.sdk.feature.adapter.TotalPayGetTransactionStatusAdapter].
 *
 * @param result the [ITotalPayResult].
 */
sealed class TotalPayGetTransactionStatusResult(open val result: ITotalPayResult) {

    /**
     * Success result.
     *
     * @property result the [TotalPayGetTransactionStatusSuccess].
     */
    data class Success(override val result: TotalPayGetTransactionStatusSuccess) :
        TotalPayGetTransactionStatusResult(result)
}
