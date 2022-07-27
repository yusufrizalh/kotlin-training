package id.inixindo.kotlinapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter

    private var items = arrayListOf(
        NavigationItemModel(R.drawable.icon_home, "Home"),
        NavigationItemModel(R.drawable.icon_gallery, "Gallery"),
        NavigationItemModel(R.drawable.icon_settings, "Settings"),
        NavigationItemModel(R.drawable.icon_product, "Products"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        updateAdapter(0)    // halaman home dibuka pertama kali

        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_content_id, homeFragment).commit()

        // memannggil toolbar
        setSupportActionBar(activity_main_toolbar)

        // memanggil recycler view
        navigation_rv.layoutManager = LinearLayoutManager(this)
        navigation_rv.setHasFixedSize(true)

        // ketika salah satu menu dipilih
        navigation_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        // membuka menu home
                        val homeFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, homeFragment).commit()
                    }
                    1 -> {
                        // membuka menu gallery
                        val galleryFragment = GalleryFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, galleryFragment).commit()
                    }
                    2 -> {
                        // membuka menu settings
                        val settingsFragment = SettingsFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, settingsFragment).commit()
                    }
                    3 -> {
                        // membuka menu products
                        val intent = Intent(this@MainActivity, ProductActivity::class.java)
                        startActivity(intent)
                    }
                }

                updateAdapter(position)

                Handler().postDelayed({
                    drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)
            }

        }))

        val toggle: ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(
                this, drawerLayout, activity_main_toolbar,
                R.string.open_drawer, R.string.close_drawer
            ) {
                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    try {
                        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        manager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }

                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    try {
                        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        manager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigation_header_img.setImageResource(R.mipmap.icon_app)
        navigation_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))
    }

    private fun updateAdapter(position: Int) {
        adapter = NavigationRVAdapter(items, position)
        navigation_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}