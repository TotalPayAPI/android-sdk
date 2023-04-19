/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.adapter

import androidx.annotation.FloatRange
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.totalpay.sdk.core.TotalPayCredential
import com.totalpay.sdk.feature.deserializer.TotalPayCaptureDeserializer
import com.totalpay.sdk.feature.service.TotalPayCaptureService
import com.totalpay.sdk.model.api.TotalPayAction
import com.totalpay.sdk.model.response.capture.TotalPayCaptureCallback
import com.totalpay.sdk.model.response.capture.TotalPayCaptureResponse
import com.totalpay.sdk.toolbox.TotalPayAmountFormatter
import com.totalpay.sdk.toolbox.TotalPayHashUtil
import com.totalpay.sdk.toolbox.TotalPayValidation
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the CAPTURE operation.
 * @see TotalPayCaptureService
 * @see TotalPayCaptureDeserializer
 * @see TotalPayCaptureCallback
 * @see TotalPayCaptureResponse
 */
object TotalPayCaptureAdapter : TotalPayBaseAdapter<TotalPayCaptureService>() {

    private val totalPayAmountFormatter = TotalPayAmountFormatter()

    override fun provideServiceClass(): Class<TotalPayCaptureService> = TotalPayCaptureService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<TotalPayCaptureResponse>(),
            TotalPayCaptureDeserializer()
        )
    }

    /**
     * Executes the [TotalPayCaptureService.capture] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param payerEmail customer’s email. String up to 256 characters.
     * @param cardNumber the credit card number.
     * @param amount the amount for capture. Only one partial capture is allowed.
     * Numbers in the form XXXX.XX (without leading zeros).
     * @param callback the [TotalPayCaptureCallback].
     */
    fun execute(
        @NonNull
        @Size(TotalPayValidation.Text.UUID)
        transactionId: String,
        @NonNull
        @Size(max = TotalPayValidation.Text.REGULAR)
        payerEmail: String,
        @NonNull
        @Size(min = TotalPayValidation.Card.CARD_NUMBER_MIN, max = TotalPayValidation.Card.CARD_NUMBER_MAX)
        cardNumber: String,
        @Nullable
        @FloatRange(from = TotalPayValidation.Amount.VALUE_MIN)
        amount: Double?,
        @NonNull
        callback: TotalPayCaptureCallback
    ) {
        val hash = TotalPayHashUtil.hash(
            email = payerEmail,
            cardNumber = cardNumber,
            transactionId = transactionId
        )

        execute(transactionId, hash, amount, callback)
    }

    /**
     * Executes the [TotalPayCaptureService.capture] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param hash special signature to validate your request to payment platform.
     * @param amount the amount for capture. Only one partial capture is allowed.
     * Numbers in the form XXXX.XX (without leading zeros).
     * @param callback the [TotalPayCaptureCallback].
     * @see com.totalpay.sdk.toolbox.TotalPayHashUtil
     */
    fun execute(
        @NonNull
        @Size(TotalPayValidation.Text.UUID)
        transactionId: String,
        @NonNull
        hash: String,
        @Nullable
        @FloatRange(from = TotalPayValidation.Amount.VALUE_MIN)
        amount: Double?,
        @NonNull
        callback: TotalPayCaptureCallback
    ) {
        service.capture(
            url = TotalPayCredential.paymentUrl(),
            action = TotalPayAction.CAPTURE.action,
            clientKey = TotalPayCredential.clientKey(),
            transactionId = transactionId,
            amount = amount?.let { totalPayAmountFormatter.amountFormat(it) },
            hash = hash
        ).totalPayEnqueue(callback)
    }
}
