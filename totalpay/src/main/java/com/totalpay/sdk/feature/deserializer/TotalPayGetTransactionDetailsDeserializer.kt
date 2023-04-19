/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.deserializer

import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.totalpay.sdk.model.response.capture.TotalPayCaptureResult
import com.totalpay.sdk.model.response.creditvoid.TotalPayCreditVoidResult
import com.totalpay.sdk.model.response.gettransactiondetails.TotalPayGetTransactionDetailsResponse
import com.totalpay.sdk.model.response.gettransactiondetails.TotalPayGetTransactionDetailsResult
import com.totalpay.sdk.model.response.gettransactionstatus.TotalPayGetTransactionStatusResponse
import com.totalpay.sdk.model.response.gettransactionstatus.TotalPayGetTransactionStatusResult
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject

/**
 * Custom deserializer for the [com.totalpay.sdk.feature.adapter.TotalPayGetTransactionDetailsAdapter].
 * @see TotalPayGetTransactionDetailsResult
 * @see TotalPayGetTransactionDetailsResponse
 */
class TotalPayGetTransactionDetailsDeserializer :
    TotalPayBaseDeserializer<TotalPayGetTransactionDetailsResult, TotalPayGetTransactionDetailsResponse>() {

    override fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): TotalPayResponse.Result<TotalPayGetTransactionDetailsResult> {
        val getTransactionDetailsResult = TotalPayGetTransactionDetailsResult.Success(context.parse(jsonObject))
        return TotalPayResponse.Result(getTransactionDetailsResult)
    }
}
