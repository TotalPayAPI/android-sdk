/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.toolbox

import java.io.File

/**
 * The general [com.totalpay.sdk.core.TotalPaySdk] utils.
 */
internal object TotalPayUtil {

    /**
     * Validate and return the base url (Payment URL).
     * @see com.totalpay.sdk.core.TotalPayCredential.PAYMENT_URL
     *
     * @param baseUrl the base url.
     * @return the validated base url String.
     */
    fun validateBaseUrl(baseUrl: String): String {
        return if (baseUrl.endsWith(File.separatorChar)) {
            baseUrl
        } else {
            baseUrl.plus(File.separatorChar)
        }
    }
}
