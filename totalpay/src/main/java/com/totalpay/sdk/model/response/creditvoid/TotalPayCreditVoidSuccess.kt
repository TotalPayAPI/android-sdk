/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.creditvoid

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.totalpay.sdk.model.api.TotalPayAction
import com.totalpay.sdk.model.api.TotalPayResult
import com.totalpay.sdk.model.api.TotalPayStatus
import com.totalpay.sdk.model.response.base.result.ITotalPayResult
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * The CREDITVOID success result of the [TotalPayCreditvoidResult].
 * @see TotalPayCreditvoidResponse
 */
data class TotalPayCreditVoidSuccess(
    @NonNull
    @SerializedName("action")
    override val action: TotalPayAction,
    @NonNull
    @SerializedName("result")
    override val result: TotalPayResult,
    @Nullable
    @SerializedName("status")
    override val status: TotalPayStatus,
    @NonNull
    @SerializedName("order_id")
    override val orderId: String,
    @NonNull
    @SerializedName("trans_id")
    override val transactionId: String
) : ITotalPayResult, Serializable
