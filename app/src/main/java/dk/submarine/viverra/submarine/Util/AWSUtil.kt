package dk.submarine.viverra.submarine.Util

import android.content.Context
import com.amazonaws.regions.Regions
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import dk.submarine.viverra.submarine.AWSConsts


/**
 * Created by sKrogh on 21/11/2017.
 */

object AWSUtil {

    fun getCreadentialProvider(ctx: Context): CognitoCachingCredentialsProvider {
        // Initialize the Amazon Cognito credentials provider
        return CognitoCachingCredentialsProvider(
                ctx,
                AWSConsts.IDENTITY_POOL_ID, // Identity pool ID
                Regions.EU_CENTRAL_1 // Region
        )
    }
}