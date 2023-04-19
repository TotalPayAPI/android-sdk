/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.adapter

import androidx.annotation.NonNull
import androidx.annotation.Size
import com.totalpay.sdk.core.TotalPayCredential
import com.totalpay.sdk.feature.deserializer.TotalPayGetTransactionStatusDeserializer
import com.totalpay.sdk.feature.service.TotalPayGetTransactionStatusService
import com.totalpay.sdk.model.api.TotalPayAction
import com.totalpay.sdk.toolbox.TotalPayValidation
import com.totalpay.sdk.model.response.gettransactionstatus.TotalPayGetTransactionStatusCallback
import com.totalpay.sdk.model.response.gettransactionstatus.TotalPayGetTransactionStatusResponse
import com.totalpay.sdk.toolbox.TotalPayHashUtil
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the GET_TRANS_STATUS operation.
 * @see TotalPayGetTransactionStatusService
 * @see TotalPayGetTransactionStatusDeserializer
 * @see TotalPayGetTransactionStatusCallback
 * @see TotalPayGetTransactionStatusResponse
 */
object TotalPayGetTransactionStatusAdapter : TotalPayBaseAdapter<TotalPayGetTransactionStatusService>() {

    override fun provideServiceClass(): Class<TotalPayGetTransactionStatusService> =
        TotalPayGetTransactionStatusService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<TotalPayGetTransactionStatusResponse>(),
            TotalPayGetTransactionStatusDeserializer()
        )
    }

    /**
     * Executes the [TotalPayGetTransactionStatusService.getTransactionStatus] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param payerEmail customer’s email. String up to 256 characters.
     * @param cardNumber the credit card number.
     * @param callback the [TotalPayGetTransactionStatusCallback].
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
        @NonNull
        callback: TotalPayGetTransactionStatusCallback
    ) {
        val hash = TotalPayHashUtil.hash(
            email = payerEmail,
            cardNumber = cardNumber,
            transactionId = transactionId
        )

        execute(transactionId, hash, callback)
    }

    /**
     * Executes the [TotalPayGetTransactionStatusService.getTransactionStatus] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param hash special signature to validate your request to payment platform.
     * @param callback the [TotalPayGetTransactionStatusCallback].
     */
    fun execute(
        @NonNull
        @Size(TotalPayValidation.Text.UUID)
        transactionId: String,
        @NonNull
        hash: String,
        @NonNull
        callback: TotalPayGetTransactionStatusCallback
    ) {
        service.getTransactionStatus(
            url = TotalPayCredential.paymentUrl(),
            action = TotalPayAction.GET_TRANS_STATUS.action,
            clientKey = TotalPayCredential.clientKey(),
            transactionId = transactionId,
            hash = hash
        ).totalPayEnqueue(callback)
    }
}
