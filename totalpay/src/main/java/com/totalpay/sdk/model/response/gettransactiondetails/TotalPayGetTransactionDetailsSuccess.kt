/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.gettransactiondetails

import androidx.annotation.NonNull
import com.totalpay.sdk.model.api.TotalPayAction
import com.totalpay.sdk.model.api.TotalPayResult
import com.totalpay.sdk.model.api.TotalPayStatus
import com.totalpay.sdk.model.response.base.result.IOrderTotalPayResult
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * The GET_TRANS_DETAILS success result of the [TotalPayGetTransactionDetailsResult].
 * @see TotalPayGetTransactionDetailsResponse
 * @see TotalPayTransaction
 *
 * @property name payer name.
 * @property mail payer mail.
 * @property ip payer IP.
 * @property card payer card number. Format: XXXXXXXX****XXXX.
 * @property transactions the [TotalPayTransaction] list.
 */
data class TotalPayGetTransactionDetailsSuccess(
    @NonNull
    @SerializedName("action")
    override val action: TotalPayAction,
    @NonNull
    @SerializedName("result")
    override val result: TotalPayResult,
    @NonNull
    @SerializedName("status")
    override val status: TotalPayStatus,
    @NonNull
    @SerializedName("order_id")
    override val orderId: String,
    @NonNull
    @SerializedName("trans_id")
    override val transactionId: String,
    @NonNull
    @SerializedName("name")
    val name: String,
    @NonNull
    @SerializedName("mail")
    val mail: String,
    @NonNull
    @SerializedName("ip")
    val ip: String,
    @SerializedName("amount")
    override val orderAmount: Double,
    @NonNull
    @SerializedName("currency")
    override val orderCurrency: String,
    @NonNull
    @SerializedName("card")
    val card: String,
    @NonNull
    @SerializedName("transactions")
    val transactions: List<TotalPayTransaction>
) : IOrderTotalPayResult, Serializable
