/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.request.payer

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.Size
import com.totalpay.sdk.toolbox.TotalPayValidation
import java.io.Serializable

/**
 * The required payer data holder.
 * @see com.totalpay.sdk.feature.adapter.TotalPaySaleAdapter
 * @see TotalPayPayerOptions
 *
 * @property firstName customer’s name. String up to 32 characters.
 * @property lastName customer’s surname. String up to 32 characters.
 * @property address customer’s address. String up to 255 characters.
 * @property country customer’s country. 2-letter code.
 * @property city customer’s city. String up to 32 characters.
 * @property zip ZIP-code of the Customer. String up to 32 characters.
 * @property email customer’s email. String up to 256 characters.
 * @property phone customer’s phone. String up to 32 characters.
 * @property ip IP-address of the Customer. XXX.XXX.XXX.XXX.
 * @property options the optional [TotalPayPayerOptions].
 */
data class TotalPayPayer(
    @NonNull
    @Size(max = TotalPayValidation.Text.SHORT)
    val firstName: String,
    @NonNull
    @Size(max = TotalPayValidation.Text.SHORT)
    val lastName: String,
    @NonNull
    @Size(max = TotalPayValidation.Text.REGULAR)
    val address: String,
    @NonNull
    @Size(max = TotalPayValidation.Text.COUNTRY)
    val country: String,
    @NonNull
    @Size(max = TotalPayValidation.Text.SHORT)
    val city: String,
    @NonNull
    @Size(max = TotalPayValidation.Text.SHORT)
    val zip: String,
    @NonNull
    @Size(max = TotalPayValidation.Text.REGULAR)
    val email: String,
    @NonNull
    @Size(max = TotalPayValidation.Text.SHORT)
    val phone: String,
    @NonNull
    @Size(min = TotalPayValidation.Text.IP_MIN, max = TotalPayValidation.Text.IP_MAX)
    val ip: String,
    @Nullable
    val options: TotalPayPayerOptions? = null,
) : Serializable
