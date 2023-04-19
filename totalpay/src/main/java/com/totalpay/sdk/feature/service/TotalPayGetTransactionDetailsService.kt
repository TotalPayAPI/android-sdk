/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.feature.service

import androidx.annotation.NonNull
import androidx.annotation.Size
import com.totalpay.sdk.model.response.gettransactiondetails.TotalPayGetTransactionDetailsResponse
import com.totalpay.sdk.toolbox.TotalPayValidation
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * GET_TRANS_DETAILS Service for the [com.totalpay.sdk.feature.adapter.TotalPayGetTransactionDetailsAdapter].
 * @see TotalPayGetTransactionDetailsResponse
 *
 * Gets all history of transactions by the order.
 */
interface TotalPayGetTransactionDetailsService {

    /**
     * Perform GET_TRANS_DETAILS request.
     *
     * @param url the [com.totalpay.sdk.core.TotalPayCredential.PAYMENT_URL].
     * @param action the [com.totalpay.sdk.model.api.TotalPayAction.GET_TRANS_DETAILS].
     * @param clientKey unique key [com.totalpay.sdk.core.TotalPayCredential.CLIENT_KEY]. UUID format value.
     * @param transactionId transaction ID in the Payment Platform. UUID format value.
     * @param hash special signature to validate your request to Payment Platform.
     * @see com.totalpay.sdk.toolbox.TotalPayHashUtil
     * @return the [Call] by [TotalPayGetTransactionDetailsResponse].
     */
    @FormUrlEncoded
    @POST
    fun getTransactionDetails(
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
        @NonNull
        @Field("hash")
        hash: String,
    ): Call<TotalPayGetTransactionDetailsResponse>
}
