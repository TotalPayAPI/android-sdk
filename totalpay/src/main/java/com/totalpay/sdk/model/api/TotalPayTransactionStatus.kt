/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.api

import com.google.gson.annotations.SerializedName

/**
 * The transaction status types.
 * @see com.totalpay.sdk.model.response.gettransactiondetails.TotalPayTransaction
 *
 * @property transactionStatus the transaction status value.
 */
enum class TotalPayTransactionStatus(val transactionStatus: String) {
    /**
     * Failed or "0" status.
     */
    @SerializedName("fail")
    FAIL("fail"),

    /**
     * Success or "1" status.
     */
    @SerializedName("success")
    SUCCESS("success"),
}
