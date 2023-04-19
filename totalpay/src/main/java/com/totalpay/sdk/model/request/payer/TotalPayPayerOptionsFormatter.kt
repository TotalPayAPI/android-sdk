/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.request.payer

import java.text.SimpleDateFormat
import java.util.*

/**
 * The [TotalPayPayerOptions] values formatter.
 */
internal class TotalPayPayerOptionsFormatter {

    private val BIRTHDATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    /**
     * Validate and format [TotalPayPayerOptions.birthdate] value.
     *
     * @param payerOptions the [TotalPayPayerOptions].
     */
    fun birthdateFormat(payerOptions: TotalPayPayerOptions?): String? = payerOptions?.birthdate?.let {
        BIRTHDATE_FORMAT.format(it)
    }
}
