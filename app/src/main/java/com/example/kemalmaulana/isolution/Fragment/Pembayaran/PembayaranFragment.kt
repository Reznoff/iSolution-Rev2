package com.example.kemalmaulana.isolution.Fragment.Pembayaran

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.Fragment.Report.ReportFragment

import com.example.kemalmaulana.isolution.R


class PembayaranFragment : Fragment() {

    lateinit var viewPager: ViewPager
    lateinit var pagerAdapter: SectionPagerAdapter

    companion object {
        fun newInstance(): PembayaranFragment {
            return PembayaranFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_pembayaran, container, false)
        val tabLayout: TabLayout = rootView.findViewById(R.id.tabs)

        pagerAdapter = SectionPagerAdapter(fragmentManager)
        viewPager = rootView.findViewById(R.id.vp_payment)
        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

        return rootView
    }

    inner class SectionPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm) {

        override fun getItem(pos: Int): Fragment? {
            when(pos) {
                0 -> return PembayaranBulanFragment()
                1 -> return PembayaranSemesterFragment()
                2 -> return PembayaranTunggalFragment()
            }
            return null
        }

        override fun getCount(): Int {
            return 3
        }

    }


}
