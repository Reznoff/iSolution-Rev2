package com.example.kemalmaulana.isolution.Fragment.Report


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.example.kemalmaulana.isolution.Adapter.KeterampilanAdapter

import com.example.kemalmaulana.isolution.R


class ReportFragment : Fragment(), OnItemSelectedListener {

//    lateinit var adapterList: KeterampilanAdapter
//    lateinit var adapterSpinner: ArrayAdapter<String>
    lateinit var viewPager: ViewPager
    lateinit var pagerAdapter: SectionPagerAdapter

    companion object {
        fun newInstance(): ReportFragment {
            return ReportFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_report, container, false)
//
//        val spinnerTahunAjaran: Spinner = rootView.findViewById(R.id.spinnerTahunAjaran)
//        spinnerTahunAjaran.onItemSelectedListener = this
//
//        //setting the spinner
//        var tahunAjaran: ArrayList<String> = arrayListOf()
//        for (i: Int in 0 until DummyData.nilai.count()) {
//            tahunAjaran.add(DummyData.nilai[i].tahunAjaran)
//        }
//        adapterSpinner = ArrayAdapter(context, android.R.layout.simple_spinner_item, tahunAjaran)
//        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinnerTahunAjaran.adapter = adapterSpinner

        pagerAdapter = SectionPagerAdapter(fragmentManager)

        viewPager = rootView.findViewById(R.id.vp_report)
        viewPager.adapter = pagerAdapter

        val tabLayout: TabLayout = rootView.findViewById(R.id.tabs)

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

        return rootView
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
//        val item: String = parent!!.getItemAtPosition(pos).toString()
//        val listNilai = parent.rootView!!.findViewById<ListView>(R.id.listNilai)
//        if(item.isNotEmpty()) {
//            adapterList = KeterampilanAdapter(requireContext(), DummyData.nilai)
//            listNilai!!.adapter = adapterList
//        }
////        Toast.makeText(parent.context, "Selected : "+item, Toast.LENGTH_SHORT).show()
    }

    inner class SectionPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm) {


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

