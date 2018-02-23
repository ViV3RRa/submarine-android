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
import android.support.v4.app.Fragment
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
import dk.submarine.viverra.submarine.Fragments.BudgetOverviewFragment
import dk.submarine.viverra.submarine.Fragments.ProductDetailsListFragment
import dk.submarine.viverra.submarine.Modle.Product
import dk.submarine.viverra.submarine.Modle.ProductItem
import dk.submarine.viverra.submarine.Util.AWSUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*

fun Context.MainActivityIntent(): Intent {
    return Intent(this, MainActivity::class.java).apply {
        //putExtra(INTENT_USER_ID, user.id)
    }
}

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val LOG_TAG = "MainActivity"

    private var defaultFragment = BudgetOverviewFragment.newInstance()
    private var currentFragment: Fragment = defaultFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.main_content_layout, currentFragment, "BudgetOverviewFragment")
                    .commit()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Do you wanna go home?", Snackbar.LENGTH_LONG)
                    .setAction("HOME", { _ ->
                        if (currentFragment.javaClass.kotlin != defaultFragment.javaClass.kotlin) {
                            changeFragment(defaultFragment)
                            bottom_navigation.selectedItemId = R.id.navigation_home
                        }
                    }).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val ddbClient = Region.getRegion(AWSConsts.REGION) // CRUCIAL
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
            getProductItemsNewerThan(mapper)
        }
        val mythread = Thread(runnable)
        mythread.start()

        bottom_navigation.selectedItemId = R.id.navigation_home
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        bottom_navigation.selectedItemId = R.id.navigation_home
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else if (currentFragment.javaClass.kotlin != defaultFragment.javaClass.kotlin) {
            changeFragment(defaultFragment)
            bottom_navigation.selectedItemId = R.id.navigation_home
        } else {
            super.onBackPressed()
        }
    }

    fun getData(mapper: DynamoDBMapper) {
        Log.i(LOG_TAG, "getData()")
        val scanExpression = DynamoDBScanExpression()
        //result = mapper.scan(Product::class.java, scanExpression)

    }

    fun getProductItemsNewerThan(mapper: DynamoDBMapper) {
        Log.i(LOG_TAG, "getProductItemsNewerThan()")

        val eav = HashMap<String, AttributeValue>()
        eav.put(":val1", AttributeValue().withN("1518739200000"))

        val scanExpression = DynamoDBScanExpression()
                .withFilterExpression("purchase_time > :val1").withExpressionAttributeValues(eav)

        val result = mapper.scan(ProductItem::class.java, scanExpression)

        for (item: ProductItem in result) {
            appDB.productItemModel().addProductItem(item)
        }
    }

    fun getProductsNewerThan(mapper: DynamoDBMapper) {
        Log.i(LOG_TAG, "getProductsNewerThan()")

        val eav = HashMap<String, AttributeValue>()
        eav.put(":val1", AttributeValue().withN("1518739200000"))

        val scanExpression = DynamoDBScanExpression()
                .withFilterExpression("insert_time > :val1").withExpressionAttributeValues(eav)

        val result = mapper.scan(Product::class.java, scanExpression)

        for (product: Product in result) {
            appDB.productModel().addProduct(product)
        }
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
                changeFragment(BudgetOverviewFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_budget -> {
                changeFragment(ProductDetailsListFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                //main_text.setText(R.string.title_settings)
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

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        // Replace whatever is in the fragment_container view with this fragment.
        transaction.replace(R.id.main_content_layout, fragment)
        currentFragment = fragment

        // Commit the transaction
        transaction.commit()
    }
}
