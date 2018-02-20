package dk.submarine.viverra.submarine.Util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by sKrogh on 20/02/2018.
 */

class Prefs(ctx: Context) {
    val PREFS_FILENAME = "dk.submarine.viverra.submarine.prefs"

    val prefs: SharedPreferences = ctx.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    val DEVICE_ID = "device_id"
    var deviceId: String
        get() = prefs.getString(DEVICE_ID, "")
        set(value) = prefs.edit().putString(DEVICE_ID, value).apply()

    val PUSH_TOKEN = "push_token"
    var pushToken: String
        get() = prefs.getString(PUSH_TOKEN, "")
        set(value) = prefs.edit().putString(PUSH_TOKEN, value).apply()

    val SNS_ENDPOINT_ARN = "sns_endpoint_arn"
    var snsEndpointArn: String
        get() = prefs.getString(SNS_ENDPOINT_ARN, "")
        set(value) = prefs.edit().putString(SNS_ENDPOINT_ARN, value).apply()

    val SNS_NEW_DATA_SUBSCRIPTION_ARN = "sns_new_data_subscription_arn"
    var snsNewDataSubscriptionArn: String
        get() = prefs.getString(SNS_NEW_DATA_SUBSCRIPTION_ARN, "")
        set(value) = prefs.edit().putString(SNS_NEW_DATA_SUBSCRIPTION_ARN, value).apply()
}