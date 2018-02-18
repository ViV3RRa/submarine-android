package dk.submarine.viverra.submarine.Modle

/**
 * Created by sKrogh on 21/11/2017.
 */
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*

@DynamoDBTable(tableName = "products")
class BoughtItem {

    @get:DynamoDBHashKey(attributeName = "uuid")
    var uuid: String = ""

    @get:DynamoDBAttribute(attributeName = "receipt_id")
    var receiptId: String = ""

    @get:DynamoDBAttribute(attributeName = "insert_time")
    var insertTime: Long = 0

    @get:DynamoDBAttribute(attributeName = "purchase_time")
    var purchaseTime: Long = 0

    @get:DynamoDBAttribute(attributeName = "product_number")
    var ean: String? = ""

    @get:DynamoDBAttribute(attributeName = "product_name")
    var name: String = ""

    @get:DynamoDBAttribute(attributeName = "tag")
    var tag: String = ""

    @get:DynamoDBAttribute(attributeName = "count")
    var count: Float = 0f

    @get:DynamoDBAttribute(attributeName = "item_price")
    var itemPrice: Float = 0f

    @get:DynamoDBAttribute(attributeName = "total_price_without_discount")
    var totalPriceWithoutDiscount: Float = 0f

    @get:DynamoDBAttribute(attributeName = "discount")
    var discount: Boolean = false

    @get:DynamoDBAttribute(attributeName = "discount_amount")
    var discountAmount: Float = 0f
}