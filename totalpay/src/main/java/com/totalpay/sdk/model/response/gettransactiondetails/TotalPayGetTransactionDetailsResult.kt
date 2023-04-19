/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.gettransactiondetails

import com.totalpay.sdk.feature.adapter.TotalPayCallback
import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.totalpay.sdk.model.response.base.result.IOrderTotalPayResult

/**
 * The response of the [com.totalpay.sdk.feature.adapter.TotalPayGetTransactionDetailsAdapter].
 * @see com.totalpay.sdk.model.response.base.TotalPayResponse
 */
typealias TotalPayGetTransactionDetailsResponse =
        TotalPayResponse<TotalPayGetTransactionDetailsResult>

/**
 * The callback of the [com.totalpay.sdk.feature.adapter.TotalPayGetTransactionDetailsAdapter].
 * @see com.totalpay.sdk.feature.adapter.TotalPayCallback
 */
typealias TotalPayGetTransactionDetailsCallback =
        TotalPayCallback<TotalPayGetTransactionDetailsResult, TotalPayGetTransactionDetailsResponse>

/**
 * The result of the [com.totalpay.sdk.feature.adapter.TotalPayGetTransactionDetailsAdapter].
 *
 * @param result the [IOrderTotalPayResult].
 */
sealed class TotalPayGetTransactionDetailsResult(open val result: IOrderTotalPayResult) {

    /**
     * Success result.
     *
     * @property result the [TotalPayGetTransactionDetailsSuccess].
     */
    data class Success(override val result: TotalPayGetTransactionDetailsSuccess) :
        TotalPayGetTransactionDetailsResult(result)
}
