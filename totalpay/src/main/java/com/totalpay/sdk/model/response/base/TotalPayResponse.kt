/*
 * Property of TotalPay (https://totalpay.global).
 */

package com.totalpay.sdk.model.response.base

import com.totalpay.sdk.model.response.base.error.TotalPayError

/**
 * The base response for the [com.totalpay.sdk.core.TotalPaySdk].
 * Used to declare the generic type for the TotalPay Request/Response mechanism.
 * @see com.totalpay.sdk.feature.adapter.TotalPayBaseAdapter
 * @see com.totalpay.sdk.feature.deserializer.TotalPayBaseDeserializer
 * @see com.totalpay.sdk.feature.adapter.TotalPayCallback
 *
 * @param Result the result type for [TotalPayResponse.Result].
 */
sealed class TotalPayResponse<Result> {

    /**
     * The error response model.
     *
     * @param Result the result type.
     * @property error the [TotalPayError].
     */
    data class Error<Result>(val error: TotalPayError) : TotalPayResponse<Result>()

    /**
     * The result response model.
     *
     * @param Result the result type.
     * @property result the actual [Result].
     */
    data class Result<Result>(val result: Result) : TotalPayResponse<Result>()
}

/* Sample response (synchronous mode):

The response if the sale is successful:
{
    "action": "SALE",
    "result": "SUCCESS",
    "status": "SETTLED",
    "order_id": "ORDER12345",
    "trans_id": "aaaff66a-904f-11ea-833e-0242ac1f0007",
    "trans_date": "2012-0403 16:02:01",
    "descriptor": "test",
    "amount": "0.02",
    "currency": "USD"
}

The response if the sale is unsuccessful:
{
    "action": "SALE",
    "result": "DECLINED",
    "status": "DECLINED",
    "order_id": "ORDER-12345",
    "trans_id": "aaaff66a-904f-11ea-833e-0242ac1f0007",
    "trans_date": "2012-04-03 16:02:01",
    "decline_reason": "Declined by processing"
}

The response if the transaction supports 3D-Secure:
{
    "action": "SALE",
    "result": "REDIRECT",
    "status": "3DS",
    "order_id": "1588856266Intelligent",
    "trans_id": "595ceeea-9062-11ea-aa1b0242ac1f0007",
    "trans_date": "2012-04-03 16:02:01",
    "descriptor": "Descriptor",
    "amount": "0.02",
    "currency": "USD",
    "redirect_url": "https://some.acs.endpoint.com",
    "redirect_params": {
        "PaReq": "M0RTIE1hc3RlciBVU 0QgU1VDQ0VTUw==",
        "MD": "595ceeea-9062-11ea-aa1b0242ac1f0007",
        "TermUrl": "https://192.168.0.1:8101/verify/3ds/595ceeea-9062-11eaaa1b-0242ac1f0007/7d6b9b240ff2779b7209aef786f808d1"
    },
    "redirect_method": "POST"
}

In case of an error:
{
    "result": "ERROR",
    "error_message": "Error description"
}
*/
