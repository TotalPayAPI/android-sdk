/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sample.app

import android.content.Context
import androidx.core.content.edit
import com.totalpay.sdk.model.api.TotalPayAction
import com.totalpay.sdk.model.api.TotalPayResult
import com.totalpay.sdk.model.api.TotalPayStatus
import com.totalpay.sdk.model.response.base.result.ITotalPayResult
import com.google.gson.Gson
import java.util.*

internal class TotalPayTransactionStorage(context: Context) {

    companion object {
        private const val AKURATECO_TRANSACTION_STORAGE = "AKURATECO_TRANSACTION_STORAGE"
    }

    private val storage = context.getSharedPreferences(AKURATECO_TRANSACTION_STORAGE, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun addTransaction(transaction: Transaction) {
        storage.edit {
            putString(
                UUID.randomUUID().toString(),
                gson.toJson(transaction)
            )
        }
    }

    fun getAllTransactions() = storage.all.map {
        gson.fromJson(it.value as String, Transaction::class.java)
    }

    fun getRecurringSaleTransactions() = getAllTransactions().filter {
        it.action == TotalPayAction.SALE && it.recurringToken.isNotEmpty()
    }

    fun getCaptureTransactions() = getAllTransactions().filter {
        it.action == TotalPayAction.SALE && it.isAuth
    }

    fun getCreditvoidTransactions() = getAllTransactions().filter {
        it.action == TotalPayAction.SALE || it.action == TotalPayAction.CAPTURE || it.isAuth
    }

    fun clearTransactions() = storage.edit {
        clear()
    }

    data class Transaction(
        val payerEmail: String,
        val cardNumber: String,
    ) {
        var id: String = ""
        var action: TotalPayAction? = null
        var result: TotalPayResult? = null
        var status: TotalPayStatus? = null

        var recurringToken: String = ""
        var isAuth: Boolean = false

        fun fill(result: ITotalPayResult) {
            id = result.transactionId
            action = result.action
            this.result = result.result
            status = result.status
        }
    }
}
