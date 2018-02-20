package dk.submarine.viverra.submarine.Services

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest
import com.amazonaws.services.sns.AmazonSNSClient
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import dk.submarine.viverra.submarine.AWSConsts
import dk.submarine.viverra.submarine.prefs
import com.amazonaws.services.sns.model.SubscribeRequest




class SubmarineFirebaseInstanceIdService : FirebaseInstanceIdService() {
    private val LOG_TAG = "FirebaseInstanceIdServi"

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val pushToken = FirebaseInstanceId.getInstance().token
        Log.d(LOG_TAG, "Push token: " + pushToken!!)

        prefs.pushToken = pushToken
        val endpointArn = createAWSPlatformEndpoint(pushToken)
        subscripeToNewDataTopic(endpointArn)
    }

    private fun createAWSPlatformEndpoint(pushToken: String): String {
        val awsCredentials = BasicAWSCredentials(AWSConsts.ACCESS_KEY, AWSConsts.SECRET_KEY)

        val snsClient = AmazonSNSClient(awsCredentials)
        snsClient.setRegion(Region.getRegion(AWSConsts.REGION))

        val platformEndpointRequest = CreatePlatformEndpointRequest()
        platformEndpointRequest.customUserData = prefs.deviceId
        platformEndpointRequest.token = pushToken
        platformEndpointRequest.platformApplicationArn = AWSConsts.SNS_PLATFORM_APPLICATION_ARN

        val result = snsClient.createPlatformEndpoint(platformEndpointRequest)
        prefs.snsEndpointArn = result.endpointArn
        Log.d(LOG_TAG, "createAWSPlatformEndpoint - Response: " + result.endpointArn)

        return result.endpointArn
    }

    private fun subscripeToNewDataTopic(endpointArn: String) {
        val awsCredentials = BasicAWSCredentials(AWSConsts.ACCESS_KEY, AWSConsts.SECRET_KEY)

        val snsClient = AmazonSNSClient(awsCredentials)
        snsClient.setRegion(Region.getRegion(AWSConsts.REGION))

        val subscribeRequest = SubscribeRequest()
        subscribeRequest.withTopicArn(AWSConsts.SNS_TOPIC_NEW_DATA_ARN)
        subscribeRequest.withProtocol("application")
        subscribeRequest.withEndpoint(endpointArn)

        val result = snsClient.subscribe(subscribeRequest)
        prefs.snsNewDataSubscriptionArn = result.subscriptionArn
        Log.d(LOG_TAG, "subscripeToNewDataTopic - Response: " + result.subscriptionArn)
    }
}
