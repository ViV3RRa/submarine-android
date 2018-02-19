package dk.submarine.viverra.submarine.Services

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class DynamoDBIntentService : IntentService("DynamoDBIntentService") {
    private val LOG_TAG = "DynamoDBIntentService"

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val action = intent.action
            if (ACTION_FETCH_PRODUCTS == action) {
                getNewProducts()
            } else if (ACTION_FETCH_PRODUCT_ITEMS == action) {
                getNewProductItems()
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun getNewProducts() {
        Log.d(LOG_TAG, "Get new Products...")
        // TODO: Handle action getNewProducts
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun getNewProductItems() {
        Log.d(LOG_TAG, "Get new ProductItems...")
        // TODO: Handle action getNewProductItems
    }

    companion object {

        val ACTION_FETCH_PRODUCTS = "products"
        val ACTION_FETCH_PRODUCT_ITEMS = "product_items"

        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        fun startActionGetNewProducts(context: Context) {
            val intent = Intent(context, DynamoDBIntentService::class.java)
            intent.action = ACTION_FETCH_PRODUCTS
            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        fun startActionGetNewProductItems(context: Context) {
            val intent = Intent(context, DynamoDBIntentService::class.java)
            intent.action = ACTION_FETCH_PRODUCT_ITEMS
            context.startService(intent)
        }
    }
}
