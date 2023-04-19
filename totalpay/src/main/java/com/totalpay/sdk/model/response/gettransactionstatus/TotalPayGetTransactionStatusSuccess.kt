/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.gettransactionstatus

import androidx.annotation.NonNull
import com.totalpay.sdk.model.api.TotalPayAction
import com.totalpay.sdk.model.api.TotalPayResult
import com.totalpay.sdk.model.api.TotalPayStatus
import com.totalpay.sdk.model.response.base.result.ITotalPayResult
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * The GET_TRANS_STATUS success result of the [TotalPayGetTransactionStatusResult].
 * @see TotalPayGetTransactionStatusResponse
 */
data class TotalPayGetTransactionStatusSuccess(
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
    override val transactionId: String
) : ITotalPayResult, Serializable
