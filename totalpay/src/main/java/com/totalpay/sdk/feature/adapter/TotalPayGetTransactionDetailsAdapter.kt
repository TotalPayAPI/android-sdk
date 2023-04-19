/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.adapter

import androidx.annotation.NonNull
import androidx.annotation.Size
import com.totalpay.sdk.core.TotalPayCredential
import com.totalpay.sdk.feature.deserializer.TotalPayGetTransactionDetailsDeserializer
import com.totalpay.sdk.feature.service.TotalPayGetTransactionDetailsService
import com.totalpay.sdk.model.api.TotalPayAction
import com.totalpay.sdk.toolbox.TotalPayValidation
import com.totalpay.sdk.model.response.gettransactiondetails.TotalPayGetTransactionDetailsCallback
import com.totalpay.sdk.model.response.gettransactiondetails.TotalPayGetTransactionDetailsResponse
import com.totalpay.sdk.toolbox.TotalPayHashUtil
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the GET_TRANS_DETAILS operation.
 * @see TotalPayGetTransactionDetailsService
 * @see TotalPayGetTransactionDetailsDeserializer
 * @see TotalPayGetTransactionDetailsCallback
 * @see TotalPayGetTransactionDetailsResponse
 */
object TotalPayGetTransactionDetailsAdapter : TotalPayBaseAdapter<TotalPayGetTransactionDetailsService>() {

    override fun provideServiceClass(): Class<TotalPayGetTransactionDetailsService> =
        TotalPayGetTransactionDetailsService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<TotalPayGetTransactionDetailsResponse>(),
            TotalPayGetTransactionDetailsDeserializer()
        )
    }

    /**
     * Executes the [TotalPayGetTransactionDetailsService.getTransactionDetails] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param payerEmail customer’s email. String up to 256 characters.
     * @param cardNumber the credit card number.
     * @param callback the [TotalPayGetTransactionDetailsCallback].
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
        callback: TotalPayGetTransactionDetailsCallback
    ) {
        val hash = TotalPayHashUtil.hash(
            email = payerEmail,
            cardNumber = cardNumber,
            transactionId = transactionId
        )

        execute(transactionId, hash, callback)
    }

    /**
     * Executes the [TotalPayGetTransactionDetailsService.getTransactionDetails] request.
     *
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param hash special signature to validate your request to payment platform.
     * @param callback the [TotalPayGetTransactionDetailsCallback].
     */
    fun execute(
        @NonNull
        @Size(TotalPayValidation.Text.UUID)
        transactionId: String,
        @NonNull
        hash: String,
        @NonNull
        callback: TotalPayGetTransactionDetailsCallback
    ) {
        service.getTransactionDetails(
            url = TotalPayCredential.paymentUrl(),
            action = TotalPayAction.GET_TRANS_DETAILS.action,
            clientKey = TotalPayCredential.clientKey(),
            transactionId = transactionId,
            hash = hash
        ).totalPayEnqueue(callback)
    }
}
