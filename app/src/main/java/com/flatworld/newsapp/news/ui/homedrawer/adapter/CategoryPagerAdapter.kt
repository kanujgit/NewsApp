package com.flatworld.newsapp.news.ui.homedrawer.adapter


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.flatworld.newsapp.R
import com.flatworld.newsapp.news.ui.homedrawer.category.business.BusinessFragment
import com.flatworld.newsapp.news.ui.homedrawer.category.entertainment.EntertainmentFragment
import com.flatworld.newsapp.news.ui.homedrawer.category.general.GeneralFragment
import com.flatworld.newsapp.news.ui.homedrawer.category.health.HealthFragment
import com.flatworld.newsapp.news.ui.homedrawer.category.science.ScienceFragment
import com.flatworld.newsapp.news.ui.homedrawer.category.sports.SportsFragment
import com.flatworld.newsapp.news.ui.homedrawer.category.technology.TechnologyFragment
import com.flatworld.newsapp.news.ui.homedrawer.category.world.WorldFragment
import com.flatworld.newsapp.news.utils.Constants


/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */

class CategoryPagerAdapter(fragment: FragmentManager, private val mContext: Context) :
    FragmentPagerAdapter(fragment) {

    /**
     * Return the [Fragment] that should be displayed for the given page number.
     */
    override fun getItem(position: Int): Fragment {
        return when (position) {
            Constants.WORLD -> WorldFragment()
            Constants.GENERAL -> GeneralFragment()
            Constants.HEALTH -> HealthFragment()
            Constants.SCIENCE -> ScienceFragment()
            Constants.SPORTS -> SportsFragment()
            Constants.TECHNOLOGY -> TechnologyFragment()
            Constants.BUSINESS -> BusinessFragment()
            Constants.ENTERTAINMENT -> EntertainmentFragment()
            else -> WorldFragment()
        }
    }

    /**
     * Return the total number of pages.
     * Currently make static because we don't have api for category
     */
    override fun getCount(): Int = 8

    /**
     * Return page title of the tap
     */
    override fun getPageTitle(position: Int): CharSequence {
        val titleResId: Int = when (position) {
            Constants.WORLD -> R.string.ic_title_world
            Constants.GENERAL -> R.string.ic_title_science
            Constants.HEALTH -> R.string.ic_title_health
            Constants.SCIENCE -> R.string.ic_title_science
            Constants.SPORTS -> R.string.ic_title_sports
            Constants.TECHNOLOGY -> R.string.ic_title_technology
            Constants.BUSINESS -> R.string.ic_title_business
            Constants.ENTERTAINMENT -> R.string.ic_title_entertainment
            else -> R.string.ic_title_world
        }
        return mContext.getString(titleResId)
    }
}