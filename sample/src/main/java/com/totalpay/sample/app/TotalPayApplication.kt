/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sample.app

import android.app.Application
import com.totalpay.sdk.core.TotalPaySdk
import io.kimo.lib.faker.Faker

class TotalPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Faker.with(this)
        TotalPaySdk.init(this)
    }
}
