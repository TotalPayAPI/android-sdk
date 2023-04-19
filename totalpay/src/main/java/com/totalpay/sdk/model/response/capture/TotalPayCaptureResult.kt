/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.capture

import com.totalpay.sdk.feature.adapter.TotalPayCallback
import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.totalpay.sdk.model.response.base.result.IDetailsTotalPayResult
import com.totalpay.sdk.model.response.sale.TotalPaySaleDecline

/**
 * The response of the [com.totalpay.sdk.feature.adapter.TotalPayCaptureAdapter].
 * @see com.totalpay.sdk.model.response.base.TotalPayResponse
 */
typealias TotalPayCaptureResponse = TotalPayResponse<TotalPayCaptureResult>

/**
 * The callback of the [com.totalpay.sdk.feature.adapter.TotalPayCaptureAdapter].
 * @see com.totalpay.sdk.feature.adapter.TotalPayCallback
 */
typealias TotalPayCaptureCallback = TotalPayCallback<TotalPayCaptureResult, TotalPayCaptureResponse>

/**
 * The result of the [com.totalpay.sdk.feature.adapter.TotalPayCaptureAdapter].
 *
 * @param result the [IDetailsTotalPayResult].
 */
sealed class TotalPayCaptureResult(open val result: IDetailsTotalPayResult) {

    /**
     * Success result.
     *
     * @property result the [TotalPayCaptureSuccess].
     */
    data class Success(override val result: TotalPayCaptureSuccess) : TotalPayCaptureResult(result)

    /**
     * Decline result.
     *
     * @property result the [TotalPaySaleDecline].
     */
    data class Decline(override val result: TotalPaySaleDecline) : TotalPayCaptureResult(result)
}
