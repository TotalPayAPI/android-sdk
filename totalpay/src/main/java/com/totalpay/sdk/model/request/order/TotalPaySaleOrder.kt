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
 * The sale order data holder.
 * @see com.totalpay.sdk.feature.adapter.TotalPaySaleAdapter
 * @see ITotalPayOrder
 *
 * @property currency the currency. 3-letter code.
 */
data class TotalPaySaleOrder(
    @NonNull
    @Size(max = TotalPayValidation.Text.REGULAR)
    override val id: String,
    @NonNull
    @FloatRange(from = TotalPayValidation.Amount.VALUE_MIN)
    override val amount: Double,
    @NonNull
    @Size(max = TotalPayValidation.Text.CURRENCY)
    val currency: String,
    @NonNull
    @Size(max = TotalPayValidation.Text.LONG)
    override val description: String,
) : ITotalPayOrder, Serializable
