package dk.submarine.viverra.submarine

/**
 * Created by sKrogh on 18/02/2018.
 */
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.amazonaws.ClientConfiguration
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import dk.submarine.viverra.submarine.Modle.Product
import dk.submarine.viverra.submarine.Util.AWSUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

fun Context.MainActivityIntent(): Intent {
    return Intent(this, MainActivity::class.java).apply {
        //putExtra(INTENT_USER_ID, user.id)
    }
}

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val LOG_TAG = "MainActivity"

    private var result = listOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            if ( !result.isEmpty()) {
                val random = Random()
                var randomNumber = random.nextInt(result.size - 0)
                val index = Math.max(randomNumber, 0)
                Log.i(LOG_TAG, "resultSize: " + result.size + ", randomNumber: " + randomNumber + ", index: " + index)

                val product = result[index]
                Snackbar.make(view, "ProductName:   " + product.name + "   " + (product.totalPriceWithoutDiscount - product.discountAmount) + ",-", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            } else {
                Snackbar.make(view, "NO Products!! - " + result.size, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val ddbClient = Region.getRegion(Regions.EU_CENTRAL_1) // CRUCIAL
                .createClient(
                        AmazonDynamoDBClient::class.java,
                        AWSUtil.getCreadentialProvider(this),
                        ClientConfiguration()
                )

        val mapper = DynamoDBMapper.builder()
                .dynamoDBClient(ddbClient)
                .build()

        val runnable = Runnable {
            //getData(mapper)
            getProductsNewerThan(mapper)
        }
        val mythread = Thread(runnable)
        mythread.start()

        bottom_navigation.selectedItemId = R.id.navigation_budget
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        bottom_navigation.selectedItemId = R.id.navigation_budget
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun getData(mapper: DynamoDBMapper) {
        Log.i(LOG_TAG, "getData()")
        val scanExpression = DynamoDBScanExpression()
        result = mapper.scan(Product::class.java, scanExpression)

    }

    fun getProductsNewerThan(mapper: DynamoDBMapper) {
        Log.i(LOG_TAG, "getProductsNewerThan()")

        val eav = HashMap<String, AttributeValue>()
        eav.put(":val1", AttributeValue().withN("1514764801000"))

        val scanExpression = DynamoDBScanExpression()
                .withFilterExpression("purchase_time > :val1").withExpressionAttributeValues(eav)

        result = mapper.scan(Product::class.java, scanExpression)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                main_text.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_budget -> {
                main_text.setText(R.string.title_budget)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                main_text.setText(R.string.title_settings)
                //startActivity(SettingsActivityIntent("DETTE ER EN KNAP"))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
