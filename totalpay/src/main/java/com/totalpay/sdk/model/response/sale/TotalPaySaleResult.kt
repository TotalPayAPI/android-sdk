/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.sale

import com.totalpay.sdk.feature.adapter.TotalPayCallback
import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.totalpay.sdk.model.response.base.result.IDetailsTotalPayResult

/**
 * The response of the [com.totalpay.sdk.feature.adapter.TotalPaySaleAdapter].
 * @see com.totalpay.sdk.model.response.base.TotalPayResponse
 */
typealias TotalPaySaleResponse = TotalPayResponse<TotalPaySaleResult>

/**
 * The callback of the [com.totalpay.sdk.feature.adapter.TotalPaySaleAdapter].
 * @see com.totalpay.sdk.feature.adapter.TotalPayCallback
 */
typealias TotalPaySaleCallback = TotalPayCallback<TotalPaySaleResult, TotalPaySaleResponse>

/**
 * The result of the [com.totalpay.sdk.feature.adapter.TotalPaySaleAdapter].
 *
 * @param result the [IDetailsTotalPayResult].
 */
sealed class TotalPaySaleResult(open val result: IDetailsTotalPayResult) {

    /**
     * Success result.
     *
     * @property result the [TotalPaySaleSuccess].
     */
    data class Success(override val result: TotalPaySaleSuccess) : TotalPaySaleResult(result)

    /**
     * Decline result.
     *
     * @property result the [TotalPaySaleDecline].
     */
    data class Decline(override val result: TotalPaySaleDecline) : TotalPaySaleResult(result)

    /**
     * Recurring Init result.
     *
     * @property result the [TotalPaySaleRecurring].
     */
    data class Recurring(override val result: TotalPaySaleRecurring) : TotalPaySaleResult(result)

    /**
     * 3DS result.
     *
     * @property result the [TotalPaySale3ds].
     */
    data class Secure3d(override val result: TotalPaySale3ds) : TotalPaySaleResult(result)
}
