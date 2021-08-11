package com.example.sample

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onResume() {

        super.onResume()

        val v = requireView()
        val constraintLayout = v.findViewById<ConstraintLayout>(R.id.my_constraint_layout)
        val targetView = v.findViewById<TextView>(R.id.textView2)
        val message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex"
        context?.let {
            BalloonViewManager().startCoachMarkBottomRight(
                v,
                it,
                constraintLayout,
                message,
                targetView,
                0.7f,
                Color.GRAY
            )
        }
    }
}