/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.request.options

import androidx.annotation.NonNull
import androidx.annotation.Size
import com.totalpay.sdk.toolbox.TotalPayValidation
import java.io.Serializable

/**
 * The required recurring options for the [com.totalpay.sdk.feature.adapter.TotalPayRecurringSaleAdapter].
 * @see com.totalpay.sdk.feature.adapter.TotalPaySaleAdapter
 *
 * @property firstTransactionId transaction ID of the primary transaction in the Payment Platform. UUID format value.
 * @property token value obtained during the primary transaction. UUID format value.
 */
data class TotalPayRecurringOptions(
    @NonNull
    @Size(TotalPayValidation.Text.UUID)
    val firstTransactionId: String,
    @NonNull
    @Size(TotalPayValidation.Text.UUID)
    val token: String,
) : Serializable
