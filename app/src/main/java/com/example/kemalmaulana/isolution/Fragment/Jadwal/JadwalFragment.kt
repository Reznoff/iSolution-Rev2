package com.example.kemalmaulana.isolution.Fragment.Jadwal


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.kemalmaulana.isolution.R

class JadwalFragment : Fragment() {

    lateinit var viewPager: ViewPager
    lateinit var pagerAdapter: JadwalFragment.SectionPagerAdapter

    companion object {
        fun newInstance(): JadwalFragment {
            return JadwalFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_jadwal, container, false)
        val tabLayout: TabLayout = rootView.findViewById(R.id.tabs)

        pagerAdapter = SectionPagerAdapter(fragmentManager)
        viewPager = rootView.findViewById(R.id.vp_jadwal)
        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

        return rootView
    }

    inner class SectionPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm) {
        override fun getItem(pos: Int): Fragment? {
            when(pos) {
                0 -> return JadwalPelajaranFragment()
                1 -> return JadwalEkskulFragment()
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }

    }
}
