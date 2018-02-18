package dk.submarine.viverra.submarine.Util

import android.content.Context
import com.amazonaws.regions.Regions
import com.amazonaws.auth.CognitoCachingCredentialsProvider


/**
 * Created by sKrogh on 21/11/2017.
 */

object AWSUtil {

    fun getCreadentialProvider(ctx: Context): CognitoCachingCredentialsProvider {
        // Initialize the Amazon Cognito credentials provider
        return CognitoCachingCredentialsProvider(
                ctx,
                "eu-central-1:6b600f91-f978-45ed-9922-b54ae2a17080", // Identity pool ID
                Regions.EU_CENTRAL_1 // Region
        )
    }
}