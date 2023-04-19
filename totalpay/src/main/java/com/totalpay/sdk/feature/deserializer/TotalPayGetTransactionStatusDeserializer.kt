/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.deserializer

import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.totalpay.sdk.model.response.capture.TotalPayCaptureResult
import com.totalpay.sdk.model.response.creditvoid.TotalPayCreditVoidResult
import com.totalpay.sdk.model.response.gettransactionstatus.TotalPayGetTransactionStatusResponse
import com.totalpay.sdk.model.response.gettransactionstatus.TotalPayGetTransactionStatusResult
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject

/**
 * Custom deserializer for the [com.totalpay.sdk.feature.adapter.TotalPayGetTransactionStatusAdapter].
 * @see TotalPayGetTransactionStatusResult
 * @see TotalPayGetTransactionStatusResponse
 */
class TotalPayGetTransactionStatusDeserializer :
    TotalPayBaseDeserializer<TotalPayGetTransactionStatusResult, TotalPayGetTransactionStatusResponse>() {

    override fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): TotalPayResponse.Result<TotalPayGetTransactionStatusResult> {
        val getTransactionStatusResult = TotalPayGetTransactionStatusResult.Success(context.parse(jsonObject))
        return TotalPayResponse.Result(getTransactionStatusResult)
    }
}
