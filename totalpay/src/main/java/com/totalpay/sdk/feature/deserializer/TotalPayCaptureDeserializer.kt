/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.deserializer

import com.totalpay.sdk.model.api.TotalPayResult
import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.totalpay.sdk.model.response.capture.TotalPayCaptureResponse
import com.totalpay.sdk.model.response.capture.TotalPayCaptureResult
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject

/**
 * Custom deserializer for the [com.totalpay.sdk.feature.adapter.TotalPayCaptureAdapter].
 * @see TotalPayCaptureResult
 * @see TotalPayCaptureResponse
 */
class TotalPayCaptureDeserializer : TotalPayBaseDeserializer<TotalPayCaptureResult, TotalPayCaptureResponse>() {

    override fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): TotalPayResponse.Result<TotalPayCaptureResult> {
        val result = jsonObject["result"].asString

        val captureResult = if (result.equals(TotalPayResult.DECLINED.result, ignoreCase = true)) {
            TotalPayCaptureResult.Decline(context.parse(jsonObject))
        } else {
            TotalPayCaptureResult.Success(context.parse(jsonObject))
        }

        return TotalPayResponse.Result(captureResult)
    }
}
