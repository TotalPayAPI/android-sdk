/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.core

import android.content.Context
import androidx.core.content.edit

/**
 * The [TotalPayCredential] values storage.
 *
 * @param context the app context.
 */
internal class TotalPayStorage(context: Context) {

    companion object {
        private const val TOTALPAY_STORAGE = "TOTALPAY_STORAGE"
    }

    private val storage = context.getSharedPreferences(TOTALPAY_STORAGE, Context.MODE_PRIVATE)

    fun setCredential(key: String, value: String) = storage.edit {
        putString(key, value)
    }

    fun getCredentials(key: String) = storage.getString(key, null).orEmpty()
}
