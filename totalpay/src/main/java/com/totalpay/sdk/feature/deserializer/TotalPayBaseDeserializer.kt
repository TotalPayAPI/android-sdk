/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.deserializer

import com.totalpay.sdk.model.api.TotalPayResult
import com.totalpay.sdk.model.response.base.TotalPayResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

/**
 * The base TotalPay [JsonDeserializer] to properly manage the response: result or error.
 * The end-user response is presenter by the [TotalPayResponse].
 * @see com.totalpay.sdk.feature.adapter.TotalPayBaseAdapter
 *
 * @param Result the success result type for [Response].
 * @param Response the response type.
 */
abstract class TotalPayBaseDeserializer<Result, Response : TotalPayResponse<Result>> : JsonDeserializer<Response> {

    companion object {
        private const val RESULT = "result"
    }

    @Suppress("UNCHECKED_CAST")
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Response? {
        if (context == null) return null
        val jsonObject = json?.asJsonObject ?: return null

        val result = jsonObject[RESULT].asString
        return if (result.equals(TotalPayResult.ERROR.result, ignoreCase = true)) {
            parseErrorResponse(context, jsonObject) as Response
        } else {
            parseResultResponse(context, jsonObject) as Response
        }
    }

    /**
     * Parse the generic response to the [TotalPayResponse.Error].
     * @see com.totalpay.sdk.model.response.base.error.TotalPayError
     */
    private fun parseErrorResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ) = TotalPayResponse.Error<Result>(
        context.parse(jsonObject)
    )

    /**
     * Parse the generic response to the [TotalPayResponse.Result]. Required to override.
     * @see com.totalpay.sdk.model.response.base.result.ITotalPayResult
     */
    protected abstract fun parseResultResponse(
        context: JsonDeserializationContext,
        jsonObject: JsonObject
    ): TotalPayResponse.Result<Result>

    /**
     * Extension to parse the [JsonObject] by [ParseClass] type.
     *
     * @param ParseClass the class type to parse.
     * @param jsonObject the actual JSON.
     */
    protected inline fun <reified ParseClass> JsonDeserializationContext.parse(jsonObject: JsonObject) =
        deserialize<ParseClass>(jsonObject, ParseClass::class.java)
}
