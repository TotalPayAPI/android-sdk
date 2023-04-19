/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.request.payer

import androidx.annotation.Nullable
import androidx.annotation.Size
import com.totalpay.sdk.toolbox.TotalPayValidation
import java.io.Serializable
import java.util.*

/**
 * The optional payer options data holder.
 * @see com.totalpay.sdk.feature.adapter.TotalPaySaleAdapter
 * @see TotalPayPayer
 *
 * @property middleName customer’s middle name. String up to 32 characters.
 * @property birthdate customer’s birthday. Format: yyyy-MM-dd, e.g. 1970-02-17.
 * @property address2 the adjoining road or locality of the сustomer’s address. String up to 255 characters.
 * @property state customer’s state. String up to 32 characters.
 */
data class TotalPayPayerOptions(
    @Nullable
    @Size(max = TotalPayValidation.Text.SHORT)
    val middleName: String? = null,
    @Nullable
    val birthdate: Date? = null,
    @Nullable
    @Size(max = TotalPayValidation.Text.REGULAR)
    val address2: String? = null,
    @Nullable
    @Size(max = TotalPayValidation.Text.SHORT)
    val state: String? = null,
) : Serializable
