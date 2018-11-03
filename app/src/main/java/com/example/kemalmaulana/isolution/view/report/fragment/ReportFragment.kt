package com.example.kemalmaulana.isolution.view.report.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener

import com.example.kemalmaulana.isolution.R


class ReportFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: SectionPagerAdapter
    private lateinit var toolbar: Toolbar

    companion object {
        fun newInstance(): ReportFragment {
            return ReportFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_report, container, false)
        initToolbar(rootView)
        pagerAdapter = SectionPagerAdapter(fragmentManager)

        viewPager = rootView.findViewById(R.id.vp_report)
        viewPager.adapter = pagerAdapter

        val tabLayout: TabLayout = rootView.findViewById(R.id.tabs)

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

        return rootView
    }


    private fun initToolbar(rootView: View) {
        toolbar = rootView.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.report)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.subtitle = getString(R.string.subtitle_report)
        toolbar.setSubtitleTextColor(Color.WHITE)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    inner class SectionPagerAdapter(fm: FragmentManager?): FragmentStatePagerAdapter(fm) {

        override fun getItem(pos: Int): Fragment? {
            when(pos) {
                0 -> return ReportSikapFragment()
                1 -> return ReportKeterampilanFragment()
                2 -> return ReportEkskulFragment()
            }
            return null
        }

        override fun getCount(): Int {
            return 3
        }

    }

}

