/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.core

import android.content.Context
import android.os.Bundle
import com.totalpay.sdk.core.TotalPayCredential.init

/**
 * The [TotalPaySdk] credentials holder.
 * It stores and retrieve it automatically after the first successful [init].
 * [TotalPayCredential] stores values in the [TotalPayStorage].
 * @throws TotalPaySdkIsNotInitializedException if [TotalPaySdk] is not initialized.
 */
internal object TotalPayCredential {

    /**
     * Unique key to identify the account in Payment Platform (used as request parameter).
     */
    private const val CLIENT_KEY = "com.totalpay.sdk.CLIENT_KEY"

    /**
     * Password for Client authentication in Payment Platform (used for calculating hash parameter).
     */
    private const val CLIENT_PASS = "com.totalpay.sdk.CLIENT_PASS"

    /**
     * URL to request the Payment Platform.
     */
    private const val PAYMENT_URL = "com.totalpay.sdk.PAYMENT_URL"

    private var _clientKey: String? = null
    private var _clientPass: String? = null
    private var _paymentUrl: String? = null

    private var _isInitialized: Boolean? = null
    private var totalPayStorage: TotalPayStorage? = null

    /**
     * Initialize the [TotalPayCredential] values.
     *
     * @param context the app context.
     * @param metadata the meta-data of the Application AndroidManifest.xml.
     */
    fun init(
        context: Context,
        metadata: Bundle
    ) {
        init(
            context,
            metadata.getString(CLIENT_KEY) ?: return,
            metadata.getString(CLIENT_PASS) ?: return,
            metadata.getString(PAYMENT_URL) ?: return
        )
    }

    /**
     * Initialize the [TotalPayCredential] values.
     *
     * @param context the app context.
     * @param clientKey the [TotalPayCredential.CLIENT_KEY] value.
     * @param clientPass the [TotalPayCredential.CLIENT_PASS] value.
     * @param paymentUrl the [TotalPayCredential.PAYMENT_URL] value.
     */
    fun init(
        context: Context,
        clientKey: String,
        clientPass: String,
        paymentUrl: String,
    ) {
        totalPayStorage = TotalPayStorage(context)

        setClientKey(clientKey)
        setClientPass(clientPass)
        setPaymentUrl(paymentUrl)
    }

    private fun setClientKey(key: String) {
        _clientKey = key
        totalPayStorage?.setCredential(CLIENT_KEY, key)
    }

    /**
     * Get the [CLIENT_KEY] value.
     * @return [_clientKey].
     * @throws TotalPaySdkIsNotInitializedException
     */
    fun clientKey(): String {
        requireInit()

        if (_clientKey == null) {
            _clientKey = totalPayStorage?.getCredentials(CLIENT_KEY)
        }

        return _clientKey ?: throw TotalPaySdkIsNotInitializedException()
    }

    private fun setClientPass(password: String) {
        _clientPass = password
        totalPayStorage?.setCredential(CLIENT_PASS, password)
    }

    /**
     * Get the [CLIENT_PASS] value.
     * @return [_clientPass].
     * @throws TotalPaySdkIsNotInitializedException
     */
    fun clientPass(): String {
        requireInit()

        if (_clientPass == null) {
            _clientPass = totalPayStorage?.getCredentials(CLIENT_PASS)
        }

        return _clientPass ?: throw TotalPaySdkIsNotInitializedException()
    }

    private fun setPaymentUrl(paymentUrl: String) {
        _paymentUrl = paymentUrl
        totalPayStorage?.setCredential(PAYMENT_URL, paymentUrl)
    }

    /**
     * Get the [PAYMENT_URL] value.
     * @return [_paymentUrl].
     * @throws TotalPaySdkIsNotInitializedException
     */
    fun paymentUrl(): String {
        requireInit()

        if (_paymentUrl == null) {
            _paymentUrl = totalPayStorage?.getCredentials(PAYMENT_URL)
        }

        return _paymentUrl ?: throw TotalPaySdkIsNotInitializedException()
    }

    /**
     * Soft check of the [TotalPayCredential] initialization status.
     * @return the true if all [TotalPayCredential] values are provided.
     * @throws TotalPaySdkIsNotInitializedException
     */
    fun isInitialized(): Boolean {
        if (_isInitialized == null) {
            _isInitialized =
                !_clientKey.isNullOrEmpty() && !_clientPass.isNullOrEmpty() && !_paymentUrl.isNullOrEmpty()
        }

        return _isInitialized ?: throw TotalPaySdkIsNotInitializedException()
    }

    /**
     * Hard check of the [TotalPayCredential] initialization status.
     * @throws TotalPaySdkIsNotInitializedException
     */
    fun requireInit() {
        if (!isInitialized()) {
            throw TotalPaySdkIsNotInitializedException()
        }
    }
}
