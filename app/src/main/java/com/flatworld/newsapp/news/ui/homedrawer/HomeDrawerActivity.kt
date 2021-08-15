package com.flatworld.newsapp.news.ui.homedrawer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.flatworld.newsapp.R
import com.flatworld.newsapp.databinding.ActivityHomeBinding
import com.flatworld.newsapp.news.ui.homedrawer.adapter.CategoryPagerAdapter
import com.flatworld.newsapp.news.utils.Constants
import com.flatworld.newsapp.news.utils.MyProgressDialog
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class HomeDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeBinding
    var mProgressDialog: MyProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set toolbar and bind wih drawer menu
        setSupportActionBar(binding.appBarHomeDrawer.toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarHomeDrawer.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        // make alias of view pager and tabLayout
        val viewPager = binding.appBarHomeDrawer.contentHome.viewpager
        val tabLayout = binding.appBarHomeDrawer.slidingTabs

        // Give the TabLayout the ViewPager
        tabLayout.setupWithViewPager(viewPager)
        // Set gravity for tab bar
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL


        binding.navView.setNavigationItemSelectedListener(this)
        // Set the default fragment when starting the app
        onNavigationItemSelected(binding.navView.menu.getItem(0).setChecked(true))


        // Set category fragment pager adapter
        val pagerAdapter = CategoryPagerAdapter(
            supportFragmentManager, this
        )
        // Set the pager adapter onto the view pager
        viewPager.adapter = pagerAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_drawer, menu)
        return true
    }

    // override backPressed method and check is drawer open or no
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed()
        }
    }

    // handling drawer menu
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        // Switch Fragments in a ViewPager on clicking items in Navigation Drawer
        if (id == R.id.nav_home) {
            binding.appBarHomeDrawer.contentHome.viewpager.currentItem = Constants.GENERAL
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun showProgressBar() {
        if (mProgressDialog == null) {
            mProgressDialog = MyProgressDialog()
            mProgressDialog!!.myProgressDialog(this)

        }
        if (!(mProgressDialog!!.isShowing())) {
            try {
                mProgressDialog!!.show()
            } catch (e: WindowManager.BadTokenException) {
                e.printStackTrace()
            }
        }
        mProgressDialog!!.setCancelable(false)
    }

    fun hideProgressBar() {
        mProgressDialog?.dismiss()
        mProgressDialog = null
    }


}