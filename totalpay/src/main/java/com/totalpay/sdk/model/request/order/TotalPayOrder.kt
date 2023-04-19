/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.request.order

import androidx.annotation.FloatRange
import androidx.annotation.NonNull
import androidx.annotation.Size
import com.totalpay.sdk.toolbox.TotalPayValidation
import java.io.Serializable

/**
 * The required order data holder.
 * @see com.totalpay.sdk.feature.adapter.TotalPayRecurringSaleAdapter
 * @see ITotalPayOrder
 */
data class TotalPayOrder(
    @NonNull
    @Size(max = TotalPayValidation.Text.REGULAR)
    override val id: String,
    @NonNull
    @FloatRange(from = TotalPayValidation.Amount.VALUE_MIN)
    override val amount: Double,
    @NonNull
    @Size(max = TotalPayValidation.Text.LONG)
    override val description: String,
) : ITotalPayOrder, Serializable
