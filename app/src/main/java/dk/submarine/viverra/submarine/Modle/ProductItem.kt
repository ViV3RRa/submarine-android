package dk.submarine.viverra.submarine.Modle

/**
 * Created by sKrogh on 21/11/2017.
 */
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*
import dk.submarine.viverra.submarine.Util.DateConverter

@Entity(tableName = "product_items")
@DynamoDBTable(tableName = "products")
class ProductItem {

    @PrimaryKey
    @get:DynamoDBHashKey(attributeName = "uuid")
    var uuid: String = ""

    @ColumnInfo(name = "receipt_id")
    @get:DynamoDBAttribute(attributeName = "receipt_id")
    var receiptId: String = ""

    @ColumnInfo(name = "insert_time")
    @TypeConverters(DateConverter::class)
    @get:DynamoDBAttribute(attributeName = "insert_time")
    var insertTime: Long = 0

    @ColumnInfo(name = "purchase_time")
    @TypeConverters(DateConverter::class)
    @get:DynamoDBAttribute(attributeName = "purchase_time")
    var purchaseTime: Long = 0

    @ColumnInfo(name = "product_number")
    @get:DynamoDBAttribute(attributeName = "product_number")
    var ean: String? = ""

    @ColumnInfo(name = "product_name")
    @get:DynamoDBAttribute(attributeName = "product_name")
    var name: String = ""

    @get:DynamoDBAttribute(attributeName = "tag")
    var tag: String = ""

    @get:DynamoDBAttribute(attributeName = "count")
    var count: Float = 0f

    @ColumnInfo(name = "item_price")
    @get:DynamoDBAttribute(attributeName = "item_price")
    var itemPrice: Float = 0f

    @ColumnInfo(name = "total_price_without_discount")
    @get:DynamoDBAttribute(attributeName = "total_price_without_discount")
    var totalPriceWithoutDiscount: Float = 0f

    @get:DynamoDBAttribute(attributeName = "discount")
    var discount: Boolean = false

    @ColumnInfo(name = "discount_amount")
    @get:DynamoDBAttribute(attributeName = "discount_amount")
    var discountAmount: Float = 0f
}