![View SDK Wiki](https://github.com/TotalPayAPI/android-sdk/wiki) | [Report new issue](https://github.com/TotalPayAPI/android-sdk/issues/new)

# TotalPay Android SDK

TotalPay is a white-label payment software provider. Thanks to our 15+ years of experience in the payment industry, we’ve developed a state-of-the-art white-label payment system that ensures smooth and uninterrupted payment flow for merchants across industries.

<p align="center">
  <a href="https://totalpay.global">
      <img src="/media/header.png" alt="TotalPay" width="400px"/>
  </a>
</p>

TotalPay Android SDK was developed and designed with one purpose: to help the Android developers easily integrate the TotalPay Payment Platform for a specific merchant. 

The main aspects of the TotalPay Android SDK:

- [Kotlin](https://developer.android.com/kotlin) is the main language
- [Retrofit](http://square.github.io/retrofit/) is the API machine 
- [KDoc](https://kotlinlang.org/docs/reference/kotlin-doc.html) code coverage
- API debug [logging](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
- Minimum SDK 16+
- Sample Application

To properly set up the SDK, read [Wiki](https://github.com/TotalPayAPI/totalpay-android-sdk/wiki) first.
To get used to the SDK, download a [sample app](https://github.com/TotalPayAPI/totalpay-android-sdk/tree/main/sample).

## Setup

Add to the root build.gradle:

```groovy
allprojects {
    repositories {
        ...
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

Add to the package build.gradle:

```groovy
dependencies {
    implementation 'com.github.totalpay:totalpay-android-sdk:{latest-version}'
}
```

Also, it is possible to download the latest artifact from the [releases page](https://github.com/TotalPayAPI/totalpay-android-sdk/releases).

## Sample

| Sale | Recurring Sale | Capture |
|-|-|-|
| ![](/media/sale.gif) | ![](/media/recurring-sale.gif) | ![](/media/capture.gif) |

| Creditvoid | Get Trans Status | Get Trans Details |
|-|-|-|
| ![](/media/creditvoid.gif) | ![](/media/get-trans-status.gif) | ![](/media/get-trans-details.gif) |

## Getting help

To report a specific issue or feature request, open a [new issue](https://github.com/TotalPayAPI/totalpay-android-sdk/issues/new).

Or write a direct letter to the [info@totalpay.global](mailto:info@totalpay.global).

## License

MIT License. See the [LICENSE](https://github.com/TotalPayAPI/totalpay-android-sdk/blob/main/LICENSE) file for more details.

## Contacts

![](/media/logo_516.png)

Website: https://totalpay.global  
Phone: [+971 4 4578506](tel:+97144578506)  
Email: [info@totalpay.global](mailto:info@totalpay.global)  
Address: i-Rise Tower, Barsha Heights, Dubai, UAE.

© 2022 - 2023 TotalPay. All rights reserved.
