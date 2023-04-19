/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.adapter

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.totalpay.sdk.core.TotalPayCredential
import com.totalpay.sdk.feature.deserializer.TotalPaySaleDeserializer
import com.totalpay.sdk.feature.service.TotalPaySaleService
import com.totalpay.sdk.model.api.TotalPayAction
import com.totalpay.sdk.model.api.TotalPayOption
import com.totalpay.sdk.model.request.card.TotalPayCard
import com.totalpay.sdk.model.request.card.TotalPayCardFormatter
import com.totalpay.sdk.model.request.options.TotalPaySaleOptions
import com.totalpay.sdk.model.request.order.TotalPaySaleOrder
import com.totalpay.sdk.model.request.payer.TotalPayPayer
import com.totalpay.sdk.model.request.payer.TotalPayPayerOptionsFormatter
import com.totalpay.sdk.model.response.sale.TotalPaySaleCallback
import com.totalpay.sdk.model.response.sale.TotalPaySaleResponse
import com.totalpay.sdk.toolbox.TotalPayAmountFormatter
import com.totalpay.sdk.toolbox.TotalPayHashUtil
import com.totalpay.sdk.toolbox.TotalPayValidation
import com.google.gson.GsonBuilder

/**
 * The API Adapter for the SALE operation.
 * @see TotalPaySaleService
 * @see TotalPaySaleDeserializer
 * @see TotalPaySaleCallback
 * @see TotalPaySaleResponse
 */
object TotalPaySaleAdapter : TotalPayBaseAdapter<TotalPaySaleService>() {

    private val totalPayAmountFormatter = TotalPayAmountFormatter()
    private val totalPayCardFormatter = TotalPayCardFormatter()
    private val totalPayPayerOptionsFormatter = TotalPayPayerOptionsFormatter()

    override fun provideServiceClass(): Class<TotalPaySaleService> = TotalPaySaleService::class.java

    override fun configureGson(builder: GsonBuilder) {
        super.configureGson(builder)
        builder.registerTypeAdapter(
            responseType<TotalPaySaleResponse>(),
            TotalPaySaleDeserializer()
        )
    }

    /**
     * Executes the [TotalPaySaleService.sale] request.
     *
     * @param order the [TotalPaySaleOrder].
     * @param card the [TotalPayCard].
     * @param payer the [TotalPayPayer].
     * @param termUrl3ds URL to which Customer should be returned after 3D-Secure. String up to 1024 characters.
     * @param options the [TotalPaySaleOptions]. Optional.
     * @param auth indicates that transaction must be only authenticated, but not captured.
     * @param callback the [TotalPaySaleCallback].
     */
    fun execute(
        @NonNull
        order: TotalPaySaleOrder,
        @NonNull
        card: TotalPayCard,
        @NonNull
        payer: TotalPayPayer,
        @NonNull
        @Size(max = TotalPayValidation.Text.LONG)
        termUrl3ds: String,
        @Nullable
        options: TotalPaySaleOptions? = null,
        @NonNull
        auth: Boolean,
        @NonNull
        callback: TotalPaySaleCallback
    ) {
        val hash = TotalPayHashUtil.hash(
            email = payer.email,
            cardNumber = card.number
        )
        val payerOptions = payer.options

        service.sale(
            url = TotalPayCredential.paymentUrl(),
            action = TotalPayAction.SALE.action,
            clientKey = TotalPayCredential.clientKey(),
            orderId = order.id,
            orderAmount = totalPayAmountFormatter.amountFormat(order.amount),
            orderCurrency = order.currency,
            orderDescription = order.description,
            cardNumber = card.number,
            cardExpireMonth = totalPayCardFormatter.expireMonthFormat(card),
            cardExpireYear = totalPayCardFormatter.expireYearFormat(card),
            cardCvv2 = card.cvv,
            payerFirstName = payer.firstName,
            payerLastName = payer.lastName,
            payerAddress = payer.address,
            payerCountry = payer.country,
            payerCity = payer.city,
            payerZip = payer.zip,
            payerEmail = payer.email,
            payerPhone = payer.phone,
            payerIp = payer.ip,
            termUrl3ds = termUrl3ds,
            hash = hash,
            auth = TotalPayOption.map(auth).option,
            channelId = if (options?.channelId.isNullOrEmpty()) null else options?.channelId,
            recurringInit = options?.recurringInit?.let { TotalPayOption.map(it).option },
            payerMiddleName = if (payerOptions?.middleName.isNullOrEmpty()) null else payerOptions?.middleName,
            payerAddress2 = if (payerOptions?.address2.isNullOrEmpty()) null else payerOptions?.address2,
            payerState = if (payerOptions?.state.isNullOrEmpty()) null else payerOptions?.state,
            payerBirthDate = totalPayPayerOptionsFormatter.birthdateFormat(payerOptions)
        ).totalPayEnqueue(callback)
    }
}
