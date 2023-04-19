/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.service

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.totalpay.sdk.toolbox.TotalPayValidation
import com.totalpay.sdk.model.response.capture.TotalPayCaptureResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * CAPTURE Service for the [com.totalpay.sdk.feature.adapter.TotalPayCaptureAdapter].
 * @see TotalPayCaptureResponse
 *
 * CAPTURE request is used to submit previously authorized transaction (created by SALE request with
 * parameter auth = Y). Hold funds will be transferred to Merchants account.
 */
interface TotalPayCaptureService {

    /**
     * Perform CAPTURE request.
     *
     * @param url the [com.totalpay.sdk.core.TotalPayCredential.PAYMENT_URL].
     * @param action the [com.totalpay.sdk.model.api.TotalPayAction.CAPTURE].
     * @param clientKey unique key [com.totalpay.sdk.core.TotalPayCredential.CLIENT_KEY]. UUID format value.
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param amount the amount for capture. Only one partial capture is allowed.
     * Numbers in the form XXXX.XX (without leading zeros).
     * @param hash special signature to validate your request to Payment Platform.
     * @see com.totalpay.sdk.toolbox.TotalPayHashUtil
     * @return the [Call] by [TotalPayCaptureResponse].
     */
    @FormUrlEncoded
    @POST
    fun capture(
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
    ): Call<TotalPayCaptureResponse>
}
