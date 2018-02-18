package dk.submarine.viverra.submarine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

fun Context.SettingsActivityIntent(buttonText: String): Intent {
    return Intent(this, SettingsActivity::class.java).apply {
        putExtra(INTENT_BUTTON_TEXT, buttonText)
    }
}

private const val INTENT_BUTTON_TEXT = "button_text"

class SettingsActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                startActivity(MainActivityIntent())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_budget -> {
                message.setText(R.string.title_budget)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                message.setText(R.string.title_settings)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onResume() {
        super.onResume()
        navigation.selectedItemId = R.id.navigation_settings
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        test_button.text = intent.getStringExtra(INTENT_BUTTON_TEXT)

        navigation.selectedItemId = R.id.navigation_settings
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
