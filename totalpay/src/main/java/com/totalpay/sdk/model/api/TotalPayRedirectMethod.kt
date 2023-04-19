/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.api

import com.google.gson.annotations.SerializedName

/**
 * The method of transferring parameters (POST/GET).
 * @see com.totalpay.sdk.model.response.sale.TotalPaySale3ds
 *
 * @property redirectMethod the redirect method value.
 */
enum class TotalPayRedirectMethod(val redirectMethod: String) {
    /**
     * GET redirect method value.
     */
    @SerializedName("GET")
    GET("GET"),

    /**
     * POST redirect method value.
     */
    @SerializedName("POST")
    POST("POST"),
}
