/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.request.options

import androidx.annotation.Nullable
import androidx.annotation.Size
import com.totalpay.sdk.toolbox.TotalPayValidation
import java.io.Serializable

/**
 * The optional sale options for the [com.totalpay.sdk.feature.adapter.TotalPaySaleAdapter].
 * @see com.totalpay.sdk.feature.adapter.TotalPayRecurringSaleAdapter
 *
 * @property channelId payment channel (Sub-account). String up to 16 characters.
 * @property recurringInit initialization of the transaction with possible following recurring.
 */
data class TotalPaySaleOptions(
    @Nullable
    @Size(max = TotalPayValidation.Text.CHANNEL_ID)
    val channelId: String? = null,
    @Nullable
    val recurringInit: Boolean? = null,
) : Serializable
