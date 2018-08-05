package com.example.kemalmaulana.isolution.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kemalmaulana.isolution.R

class KehadiranFragment : Fragment() {

    companion object {
        fun newInstance(): KehadiranFragment {
            return KehadiranFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_kehadiran, container, false);
        return rootView
    }

}
