package dk.submarine.viverra.submarine.Modle

/**
 * Created by sKrogh on 18/02/2018.
 */

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*
import dk.submarine.viverra.submarine.Util.DateConverter

@Entity(tableName = "products")
@DynamoDBTable(tableName = "product_descriptions")
class Product {

    @PrimaryKey
    @get:DynamoDBHashKey(attributeName = "ean")
    var ean: String = ""

    @ColumnInfo(name = "insert_time")
    @TypeConverters(DateConverter::class)
    @get:DynamoDBAttribute(attributeName = "insert_time")
    var insertTime: Long = 0

    @get:DynamoDBAttribute(attributeName = "name")
    var name: String = ""

    @get:DynamoDBAttribute(attributeName = "tag")
    var tag: String = ""
}