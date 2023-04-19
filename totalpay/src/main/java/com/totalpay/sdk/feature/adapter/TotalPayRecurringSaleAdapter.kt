/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.adapter

import androidx.annotation.NonNull
import androidx.annotation.Size
import com.totalpay.sdk.core.TotalPayCredential
import com.totalpay.sdk.feature.deserializer.TotalPaySaleDeserializer
import com.totalpay.sdk.feature.service.TotalPayRecurringSaleService
import com.totalpay.sdk.model.api.TotalPayAction
import com.totalpay.sdk.model.api.TotalPayOption
import com.totalpay.sdk.toolbox.TotalPayValidation
import com.totalpay.sdk.model.request.options.TotalPayRecurringOptions
import com.totalpay.sdk.model.request.order.TotalPayOrder
import com.totalpay.sdk.model.response.sale.TotalPaySaleCallback
import com.totalpay.sdk.model.response.sale.TotalPaySaleResponse
import com.totalpay.sdk.toolbox.TotalPayAmountFormatter
import com.totalpay.sdk.toolbox.TotalPayHashUtil
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the RECURRING_SALE operation.
 * @see TotalPayRecurringSaleService
 * @see TotalPaySaleDeserializer
 * @see TotalPaySaleCallback
 * @see TotalPaySaleResponse
 */
object TotalPayRecurringSaleAdapter : TotalPayBaseAdapter<TotalPayRecurringSaleService>() {

    private val totalPayAmountFormatter = TotalPayAmountFormatter()

    override fun provideServiceClass(): Class<TotalPayRecurringSaleService> =
        TotalPayRecurringSaleService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<TotalPaySaleResponse>(),
            TotalPaySaleDeserializer()
        )
    }

    /**
     * Executes the [TotalPayRecurringSaleService.recurringSale] request.
     *
     * @param order the [TotalPayOrder].
     * @param options the [TotalPayRecurringOptions].
     * @param payerEmail customer’s email. String up to 256 characters.
     * @param cardNumber the credit card number.
     * @param auth indicates that transaction must be only authenticated, but not captured.
     * @param callback the [TotalPaySaleCallback].
     */
    fun execute(
        @NonNull
        order: TotalPayOrder,
        @NonNull
        options: TotalPayRecurringOptions,
        @NonNull
        @Size(max = TotalPayValidation.Text.REGULAR)
        payerEmail: String,
        @NonNull
        @Size(min = TotalPayValidation.Card.CARD_NUMBER_MIN, max = TotalPayValidation.Card.CARD_NUMBER_MAX)
        cardNumber: String,
        @NonNull
        auth: Boolean,
        @NonNull
        callback: TotalPaySaleCallback
    ) {
        val hash = TotalPayHashUtil.hash(
            email = payerEmail,
            cardNumber = cardNumber
        )

        execute(order, options, hash, auth, callback)
    }

    /**
     * Executes the [TotalPayRecurringSaleService.recurringSale] request.
     *
     * @param order the [TotalPayOrder].
     * @param options the [TotalPayRecurringOptions].
     * @param hash special signature to validate your request to payment platform.
     * @param auth indicates that transaction must be only authenticated, but not captured.
     * @param callback the [TotalPaySaleCallback].
     * @see com.totalpay.sdk.toolbox.TotalPayHashUtil
     */
    fun execute(
        @NonNull
        order: TotalPayOrder,
        @NonNull
        options: TotalPayRecurringOptions,
        @NonNull
        hash: String,
        @NonNull
        auth: Boolean,
        @NonNull
        callback: TotalPaySaleCallback
    ) {
        service.recurringSale(
            url = TotalPayCredential.paymentUrl(),
            action = TotalPayAction.RECURRING_SALE.action,
            clientKey = TotalPayCredential.clientKey(),
            orderId = order.id,
            orderAmount = totalPayAmountFormatter.amountFormat(order.amount),
            orderDescription = order.description,
            recurringFirstTransactionId = options.firstTransactionId,
            recurringToken = options.token,
            auth = TotalPayOption.map(auth).option,
            hash = hash
        ).totalPayEnqueue(callback)
    }
}
