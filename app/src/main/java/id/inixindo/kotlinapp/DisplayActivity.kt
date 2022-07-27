package id.inixindo.kotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_display.*

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        // memunculkan actionbar back button
        val actionBar = supportActionBar
        actionBar!!.title = "Display"
        actionBar.subtitle = "Pass data with Intent"
        actionBar.setIcon(R.mipmap.icon_app)

        actionBar.setDisplayHomeAsUpEnabled(true)   // backbutton
        actionBar.setDisplayUseLogoEnabled(true)    // icon

        val bundle: Bundle? = intent.extras
        bundle?.let {
            bundle.apply {
                val display: String? = getString("NILAI")
                txtDisplayFromIntent.text = display
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    //
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
}