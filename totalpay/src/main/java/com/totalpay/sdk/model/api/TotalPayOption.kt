/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.api

import com.google.gson.annotations.SerializedName

/**
 * "Y" or "N" value holder.
 *
 * @property option the option value.
 */
enum class TotalPayOption(val option: String) {
    /**
     * "Y" value.
     */
    @SerializedName("Y")
    YES("Y"),

    /**
     * "N" value.
     */
    @SerializedName("N")
    NO("N");

    companion object {
        /**
         * Maps the [boolean] to the [TotalPayOption].
         *
         * @param boolean value to map.
         */
        fun map(boolean: Boolean): TotalPayOption = if (boolean) YES else NO
    }
}
