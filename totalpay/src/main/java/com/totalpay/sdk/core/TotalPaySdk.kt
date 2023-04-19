/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.core

import android.content.Context
import android.content.pm.PackageManager
import com.totalpay.sdk.feature.adapter.TotalPayCaptureAdapter
import com.totalpay.sdk.feature.adapter.TotalPayCreditVoidAdapter
import com.totalpay.sdk.feature.adapter.TotalPayGetTransactionDetailsAdapter
import com.totalpay.sdk.feature.adapter.TotalPayGetTransactionStatusAdapter
import com.totalpay.sdk.feature.adapter.TotalPayRecurringSaleAdapter
import com.totalpay.sdk.feature.adapter.TotalPaySaleAdapter

/**
 * The initial point of the [TotalPaySdk].
 *
 * Before you get an account to access Payment Platform, you must provide the following data to the Payment Platform
 * administrator:
 * IP list - List of your IP addresses, from which requests to Payment Platform will be sent.
 * Notification URL - URL which will be receiving the notifications of the processing results of your request to
 * Payment Platform.
 * Contact email -  Email address of Responsible Person who will monitor transactions, conduct refunds, etc.
 *
 * Note: Notification URL is mandatory if your account supports 3D-Secure. The length of Notification URL should not be
 * more than 255 symbols.
 * With all Payment Platform POST requests at Notification URL the Merchant must return the string "OK" if he
 * successfully received data or return "ERROR".
 *
 * You should get the following information from administrator to begin working with the Payment Platform:
 * [TotalPayCredential.CLIENT_KEY] - Unique key to identify the account in Payment Platform
 * (used as request parameter).
 * [TotalPayCredential.CLIENT_PASS] - Password for Client authentication in Payment Platform
 * (used for calculating hash parameter).
 * [TotalPayCredential.PAYMENT_URL] - URL to request the Payment Platform.
 * @see com.totalpay.sdk.feature.adapter.TotalPayBaseAdapter
 * @see com.totalpay.sdk.toolbox.TotalPayHashUtil.md5
 *
 * For the transaction, you must send the server to server HTTPS POST request with fields listed below to Payment
 * Platform URL ([TotalPayCredential.PAYMENT_URL]). In response Payment Platform will return the JSON  encoded string.
 * If your account supports 3D-Secure and credit card supports 3D-Secure, then Payment Platform will return the link to
 * the 3D-Secure Access Control Server to perform 3D-Secure verification. In this case, you need to redirect the
 * cardholder at this link. If there are also some parameters except the link in the result, you will need to redirect
 * the cardholder at this link together with the parameters using the method of data transmitting indicated in the same
 * result. In the case of 3D-Secure after verification on the side of the 3D-Secure server, the owner of a credit card
 * will come back to your site using the link you specify in the sale request, and Payment Platform will return the
 * result of transaction processing to the Notification URL action.
 *
 * To initialize the [TotalPaySdk] call one of the following methods: [init].
 * The initialization can be done programmatically or through the Application AndroidManifest.xml.
 * [TotalPaySdk] requires the Internet permission: <uses-permission android:name="android.permission.INTERNET" />
 * In case the [TotalPaySdk] is not initialized the [TotalPaySdkIsNotInitializedException] will be thrown.
 *
 * To test/simulate the Platon Payment System use [com.totalpay.sdk.model.request.card.TotalPayTestCard].
 */
object TotalPaySdk {

    /**
     * Initialize the [TotalPaySdk] implicitly using the meta-data of the AndroidManifest.xml
     *
     * @param context the app context.
     * @throws TotalPaySdkIsNotInitializedException
     */
    fun init(context: Context) {
        val metaData = context
            .packageManager
            .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            .metaData

        TotalPayCredential.init(context, metaData)
        TotalPayCredential.requireInit()
    }

    /**
     * Initialize the [TotalPaySdk] explicitly using the [TotalPayCredential] values.
     *
     * @param context the app context.
     * @param clientKey the [TotalPayCredential.CLIENT_KEY] value.
     * @param clientPass the [TotalPayCredential.CLIENT_PASS] value.
     * @param paymentUrl the [TotalPayCredential.PAYMENT_URL] value.
     * @throws TotalPaySdkIsNotInitializedException
     */
    fun init(
        context: Context,
        clientKey: String,
        clientPass: String,
        paymentUrl: String,
    ) {
        TotalPayCredential.init(context, clientKey, clientPass, paymentUrl)
        TotalPayCredential.requireInit()
    }

    /**
     * Indicating of the [TotalPaySdk] initialize status.
     */
    fun isInitialized(): Boolean = TotalPayCredential.isInitialized()

    /**
     * The [com.totalpay.sdk.feature.adapter]s holder to check the [TotalPaySdk] initialization status before use.
     * @throws TotalPaySdkIsNotInitializedException
     */
    object Adapter {
        /**
         * Adapter for the [com.totalpay.sdk.model.api.TotalPayAction.RECURRING_SALE] request.
         * @throws TotalPaySdkIsNotInitializedException
         */
        val RECURRING_SALE: TotalPayRecurringSaleAdapter = TotalPayRecurringSaleAdapter
            get() {
                TotalPayCredential.requireInit()
                return field
            }

        /**
         * Adapter for the [com.totalpay.sdk.model.api.TotalPayAction.SALE] request.
         * @throws TotalPaySdkIsNotInitializedException
         */
        val SALE: TotalPaySaleAdapter = TotalPaySaleAdapter
            get() {
                TotalPayCredential.requireInit()
                return field
            }

        /**
         * Adapter for the [com.totalpay.sdk.model.api.TotalPayAction.CAPTURE] request.
         * @throws TotalPaySdkIsNotInitializedException
         */
        val CAPTURE: TotalPayCaptureAdapter = TotalPayCaptureAdapter
            get() {
                TotalPayCredential.requireInit()
                return field
            }

        /**
         * Adapter for the [com.totalpay.sdk.model.api.TotalPayAction.CREDITVOID] request.
         * @throws TotalPaySdkIsNotInitializedException
         */
        val CREDITVOID: TotalPayCreditVoidAdapter = TotalPayCreditVoidAdapter
            get() {
                TotalPayCredential.requireInit()
                return field
            }

        /**
         * Adapter for the [com.totalpay.sdk.model.api.TotalPayAction.GET_TRANS_STATUS] request.
         * @throws TotalPaySdkIsNotInitializedException
         */
        val GET_TRANSACTION_STATUS: TotalPayGetTransactionStatusAdapter = TotalPayGetTransactionStatusAdapter
            get() {
                TotalPayCredential.requireInit()
                return field
            }

        /**
         * Adapter for the [com.totalpay.sdk.model.api.TotalPayAction.GET_TRANS_DETAILS] request.
         * @throws TotalPaySdkIsNotInitializedException
         */
        val GET_TRANSACTION_DETAILS: TotalPayGetTransactionDetailsAdapter = TotalPayGetTransactionDetailsAdapter
            get() {
                TotalPayCredential.requireInit()
                return field
            }
    }
}
