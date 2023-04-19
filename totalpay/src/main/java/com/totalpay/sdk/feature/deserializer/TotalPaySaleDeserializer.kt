/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.deserializer

import com.totalpay.sdk.model.api.TotalPayResult
import com.totalpay.sdk.model.api.TotalPayStatus
import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.totalpay.sdk.model.response.sale.TotalPaySaleResponse
import com.totalpay.sdk.model.response.sale.TotalPaySaleResult
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject

/**
 * Custom deserializer for the [com.totalpay.sdk.feature.adapter.TotalPaySaleAdapter].
 * @see TotalPaySaleResult
 * @see TotalPaySaleResponse
 */
class TotalPaySaleDeserializer : TotalPayBaseDeserializer<TotalPaySaleResult, TotalPaySaleResponse>() {

    override fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): TotalPayResponse.Result<TotalPaySaleResult> {
        val status = jsonObject["status"].asString
        val result = jsonObject["result"].asString

        val saleResult = if (status.equals(TotalPayStatus.SECURE_3D.status, ignoreCase = true)) {
            TotalPaySaleResult.Secure3d(context.parse(jsonObject))
        } else {
            if (result.equals(TotalPayResult.DECLINED.result, ignoreCase = true)) {
                TotalPaySaleResult.Decline(context.parse(jsonObject))
            } else {
                if (jsonObject.has("recurring_token")) {
                    TotalPaySaleResult.Recurring(context.parse(jsonObject))
                } else {
                    TotalPaySaleResult.Success(context.parse(jsonObject))
                }
            }
        }

        return TotalPayResponse.Result(saleResult)
    }
}
