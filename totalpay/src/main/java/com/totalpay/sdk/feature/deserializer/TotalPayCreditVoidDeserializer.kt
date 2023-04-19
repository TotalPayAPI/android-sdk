/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.deserializer

import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.totalpay.sdk.model.response.creditvoid.TotalPayCreditVoidResponse
import com.totalpay.sdk.model.response.creditvoid.TotalPayCreditVoidResult
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject

/**
 * Custom deserializer for the [com.totalpay.sdk.feature.adapter.TotalPayCreditvoidAdapter].
 * @see TotalPayCreditvoidResult
 * @see TotalPayCreditvoidResponse
 */
class TotalPayCreditVoidDeserializer :
    TotalPayBaseDeserializer<TotalPayCreditVoidResult, TotalPayCreditVoidResponse>() {

    override fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): TotalPayResponse.Result<TotalPayCreditVoidResult> {
        val creditvoidResult = TotalPayCreditVoidResult.Success(context.parse(jsonObject))
        return TotalPayResponse.Result(creditvoidResult)
    }
}
