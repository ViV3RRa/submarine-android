package dk.submarine.viverra.submarine

import com.amazonaws.regions.Regions

/**
 * Created by sKrogh on 20/02/2018.
 */

object AWSConsts {
    @JvmField val REGION = Regions.EU_CENTRAL_1
    const val ACCESS_KEY = "AKIAJ6FJ73LHULQZUVZA"
    const val SECRET_KEY = "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"
    const val IDENTITY_POOL_ID = "eu-central-1:6b600f91-f978-45ed-9922-b54ae2a17080"
    const val SNS_PLATFORM_APPLICATION_ARN = "arn:aws:sns:eu-central-1:879112026985:app/GCM/Submarine"
    const val SNS_TOPIC_NEW_DATA_ARN = "arn:aws:sns:eu-central-1:879112026985:newDataInDatabases"
}