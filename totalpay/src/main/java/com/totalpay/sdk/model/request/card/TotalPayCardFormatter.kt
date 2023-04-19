/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.request.card

import java.text.DecimalFormat

/**
 * The [TotalPayCard] values formatter.
 */
internal class TotalPayCardFormatter {

    private val EXPIRE_MONTH_FORMAT = DecimalFormat("00")

    /**
     * Validate and format the [TotalPayCard.expireMonth].
     *
     * @param card the [TotalPayCard].
     */
    fun expireMonthFormat(card: TotalPayCard): String = EXPIRE_MONTH_FORMAT.format(card.expireMonth)

    /**
     * Validate and format the [TotalPayCard.expireYear].
     *
     * @param card the [TotalPayCard].
     */
    fun expireYearFormat(card: TotalPayCard): String = card.expireYear.toString()
}
