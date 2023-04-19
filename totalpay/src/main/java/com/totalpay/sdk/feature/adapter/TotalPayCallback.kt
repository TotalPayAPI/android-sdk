/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.adapter

import com.totalpay.sdk.model.response.base.error.TotalPayError
import com.totalpay.sdk.model.response.base.TotalPayResponse

/**
 * The TotalPay Callback for the [TotalPayBaseAdapter] and its extenders.
 *
 * @param Result the successful result type of the [Response].
 * @param Response the response of the request type.
 */
interface TotalPayCallback<Result, Response : TotalPayResponse<Result>> {

    /**
     * The custom success result callback. Required to override.
     * Called after the [onResponse] in case the response is [TotalPayResponse.Result].
     *
     * @param result the [Result].
     */
    fun onResult(result: Result)

    /**
     * The custom error result callback. Required to override.
     * Called after the [onResponse] in case the response is [TotalPayResponse.Error].
     *
     * @param error the [TotalPayError].
     */
    fun onError(error: TotalPayError)

    /**
     * The unhandled exception callback. Optional to override.
     *
     * @param throwable the [Throwable].
     */
    fun onFailure(throwable: Throwable) = Unit

    /**
     * The custom success response callback: result or error. Optional to override.
     * @see TotalPayResponse
     *
     * @param response the [Response].
     */
    fun onResponse(response: Response) = Unit
}
