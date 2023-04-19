/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.service

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.totalpay.sdk.toolbox.TotalPayValidation
import com.totalpay.sdk.model.response.creditvoid.TotalPayCreditVoidResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * CREDITVOID Service for the [com.totalpay.sdk.feature.adapter.TotalPayCreditvoidAdapter].
 * @see TotalPayCreditvoidResponse
 *
 * CREDITVOID request is used to complete both REFUND and REVERSAL transactions.
 * REVERSAL transaction is used to cancel hold from funds on card account, previously authorized by AUTH transaction.
 * REFUND transaction is used to return funds to card account, previously submitted by SALE or CAPTURE transactions.
 */
interface TotalPayCreditVoidService {

    /**
     * Perform CREDITVOID request.
     *
     * @param url the [com.totalpay.sdk.core.TotalPayCredential.PAYMENT_URL].
     * @param action the [com.totalpay.sdk.model.api.TotalPayAction.CREDITVOID].
     * @param clientKey unique key [com.totalpay.sdk.core.TotalPayCredential.CLIENT_KEY]. UUID format value.
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param amount the amount for capture. Only one partial capture is allowed.
     * Numbers in the form XXXX.XX (without leading zeros).
     * @param hash special signature to validate your request to Payment Platform.
     * @see com.totalpay.sdk.toolbox.TotalPayHashUtil
     * @return the [Call] by [TotalPayCreditvoidResponse].
     */
    @FormUrlEncoded
    @POST
    fun creditvoid(
        @NonNull
        @Url
        url: String,
        @NonNull
        @Field("action")
        action: String,
        @NonNull
        @Size(TotalPayValidation.Text.UUID)
        @Field("client_key")
        clientKey: String,
        @NonNull
        @Size(TotalPayValidation.Text.UUID)
        @Field("trans_id")
        transactionId: String,
        @Nullable
        @Field("amount")
        amount: String?,
        @NonNull
        @Field("hash")
        hash: String,
    ): Call<TotalPayCreditVoidResponse>
}
