/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.creditvoid

import com.totalpay.sdk.feature.adapter.TotalPayCallback
import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.totalpay.sdk.model.response.base.result.ITotalPayResult

/**
 * The response of the [com.totalpay.sdk.feature.adapter.TotalPayCreditVoidAdapter].
 * @see com.totalpay.sdk.model.response.base.TotalPayResponse
 */
typealias TotalPayCreditVoidResponse = TotalPayResponse<TotalPayCreditVoidResult>

/**
 * The callback of the [com.totalpay.sdk.feature.adapter.TotalPayCreditVoidAdapter].
 * @see com.totalpay.sdk.feature.adapter.TotalPayCallback
 */
typealias TotalPayCreditVoidCallback = TotalPayCallback<TotalPayCreditVoidResult, TotalPayCreditVoidResponse>

/**
 * The result of the [com.totalpay.sdk.feature.adapter.TotalPayCreditVoidAdapter].
 *
 * @param result the [ITotalPayResult].
 */
sealed class TotalPayCreditVoidResult(open val result: ITotalPayResult) {

    /**
     * Success result.
     *
     * @property result the [TotalPayCreditVoidSuccess].
     */
    data class Success(override val result: TotalPayCreditVoidSuccess) : TotalPayCreditVoidResult(result)
}
