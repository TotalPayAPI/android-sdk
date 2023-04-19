/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.core

/**
 * The [TotalPaySdk] not initialized exception. Thrown when [TotalPayCredential.requireInit] is not satisfied with
 * the [TotalPayCredential.isInitialized] soft check.
 */
class TotalPaySdkIsNotInitializedException : IllegalAccessError(MESSAGE) {

    companion object {
        private const val MESSAGE = """
           TotalPay SDK is not initialized.
           Please, provide your Payment Platform credentials in AndroidManifest.xml,
           and call TotalPaySdk.init(yourAppContext) in YourApplication.class.
           You can also initialize the TotalPay SDK programmatically.
        """
    }
}
