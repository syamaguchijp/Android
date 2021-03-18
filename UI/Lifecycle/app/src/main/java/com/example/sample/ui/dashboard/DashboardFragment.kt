package com.example.sample.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sample.R
import com.example.sample.log.Logging

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onAttach(context: Context) {
        Logging.d("")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Logging.d("")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Logging.d("")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Logging.d("")
        super.onStart()
    }

    override fun onResume() {
        Logging.d("")
        super.onResume()
    }

    override fun onPause() {
        Logging.d("")
        super.onPause()
    }

    override fun onStop() {
        Logging.d("")
        super.onStop()
    }

    override fun onDestroyView() {
        Logging.d("")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Logging.d("")
        super.onDestroy()
    }

    override fun onDetach() {
        Logging.d("")
        super.onDetach()
    }

}