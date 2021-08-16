package com.flatworld.newsapp.news.ui.homedrawer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.flatworld.newsapp.R
import com.flatworld.newsapp.databinding.ActivityHomeBinding
import com.flatworld.newsapp.news.ui.details.NewsDetailActivity
import com.flatworld.newsapp.news.ui.homedrawer.adapter.CategoryPagerAdapter
import com.flatworld.newsapp.news.ui.homedrawer.bookmark.BookmarkFragment
import com.flatworld.newsapp.news.ui.homedrawer.category.viewmodel.CommonCategoryViewModel
import com.flatworld.newsapp.news.utils.Constants
import com.flatworld.newsapp.news.utils.MyProgressDialog
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class HomeDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityHomeBinding
    var mProgressDialog: MyProgressDialog? = null

    // Using the viewModels() Kotlin property delegate from the activity-ktx
    // artifact to retrieve the ViewModel in the activity scope
    private val viewModel: CommonCategoryViewModel by viewModels()


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

        // set off screen limit to 4
        // for stop multiple time loading during the swiping
        // later we can change as per requirement
        viewPager.offscreenPageLimit = 4


        // observe changes
        viewModel.selectedItem.observe(this, Observer { item ->
            // Perform an action with the latest item data
            val intent = Intent(this, NewsDetailActivity::class.java)
            intent.putExtra("data", item)
            startActivity(intent)
        })
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
        return when (item.itemId) {
            R.id.nav_home -> {
                binding.appBarHomeDrawer.contentHome.viewpager.currentItem = Constants.GENERAL
                binding.appBarHomeDrawer.slidingTabs.visibility = View.VISIBLE
                binding.appBarHomeDrawer.toolbar.title = getString(R.string.menu_home)
                binding.appBarHomeDrawer.contentHome.frameView.visibility = View.GONE
                true
            }
            R.id.nav_bookmark -> {
                binding.appBarHomeDrawer.slidingTabs.visibility = View.GONE
                binding.appBarHomeDrawer.toolbar.title = getString(R.string.menu_bookmark)
                binding.appBarHomeDrawer.contentHome.frameView.visibility = View.VISIBLE
                startBookmarkFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }.also {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun startBookmarkFragment() {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.frame_view, BookmarkFragment(), BookmarkFragment::class.java.name)
        transaction.addToBackStack(null)
        transaction.commit()
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