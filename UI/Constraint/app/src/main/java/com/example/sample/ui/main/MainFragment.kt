package com.example.sample.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.CHAIN_SPREAD
import androidx.fragment.app.Fragment
import com.example.sample.R


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_main, container, false)

        val layout = v.findViewById(R.id.main) as ConstraintLayout
        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)

        /////////// 普通にview1とview2を水平方向に並べるときのサンプル
        val view1 = View(context)
        view1.id = View.generateViewId()
        view1.setBackgroundColor(Color.BLUE)
        layout.addView(view1)
        constraintSet.constrainHeight(
            view1.getId(), 200
        )
        constraintSet.constrainWidth(
            view1.getId(), 200
        )
        constraintSet.connect(
            view1.getId(), ConstraintSet.TOP,
            ConstraintSet.PARENT_ID, ConstraintSet.TOP,
            50
        )
        constraintSet.connect(
            view1.getId(),
            ConstraintSet.LEFT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.LEFT,
            50
        )
        val view2 = View(context)
        view2.id = View.generateViewId()
        view2.setBackgroundColor(Color.RED)
        layout.addView(view2)
        constraintSet.connect(
            view2.getId(), ConstraintSet.TOP,
            view1.getId(), ConstraintSet.TOP,
            0
        )
        constraintSet.connect(
            view2.getId(), ConstraintSet.LEFT,
            view1.getId(), ConstraintSet.RIGHT,
            100
        )
        constraintSet.connect(
            view2.getId(), ConstraintSet.RIGHT,
            ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,
            50
        )
        constraintSet.connect(
            view2.getId(), ConstraintSet.BOTTOM,
            view1.getId(), ConstraintSet.BOTTOM,
            0
        )

        /////////// 水平方向の位置を左端から30%の位置にするサンプル
        val view3 = View(context)
        view3.id = View.generateViewId()
        view3.setBackgroundColor(Color.YELLOW)
        layout.addView(view3)
        constraintSet.connect(
            view3.getId(), ConstraintSet.TOP,
            view1.getId(), ConstraintSet.BOTTOM,
            10
        )
        constraintSet.connect(
            view3.getId(), ConstraintSet.LEFT,
            ConstraintSet.PARENT_ID, ConstraintSet.LEFT,
            0
        )
        constraintSet.connect(
            view3.getId(), ConstraintSet.RIGHT,
            ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,
            0
        )
        // 水平方向の位置を左端から30%の位置にする
        // （setVerticalBiasを併用すると、画面上のxy座標の好きな位置に置ける）
        constraintSet.setHorizontalBias(
            view3.getId(),
            0.3F
        )
        constraintSet.constrainWidth(
            view3.getId(),
            300
        )
        // 縦横比を2:1にする
        constraintSet.setDimensionRatio(
            view3.getId(),
            "H,1:2"
        )

        /////////// チェーンを使って線形グループを管理するサンプル。垂直方向
        /////////// 左側に位置する黄色のView3の縦幅いっぱいに、垂直方向に均等にView6とView7を割り付けるサンプル。
        val view6 = View(context)
        view6.id = View.generateViewId()
        view6.setBackgroundColor(Color.LTGRAY)
        layout.addView(view6)
        val view7 = View(context)
        view7.id = View.generateViewId()
        view7.setBackgroundColor(Color.DKGRAY)
        layout.addView(view7)
        constraintSet.constrainWidth(
            view6.getId(), 200
        )
        constraintSet.constrainWidth(
            view7.getId(), 200
        )
        constraintSet.connect(
            view6.getId(), ConstraintSet.TOP,
            view3.getId(), ConstraintSet.TOP
        )
        constraintSet.connect(
            view7.getId(), ConstraintSet.BOTTOM,
            view3.getId(), ConstraintSet.BOTTOM
        )
        constraintSet.connect(
            view6.getId(), ConstraintSet.LEFT,
            view3.getId(), ConstraintSet.RIGHT,
            50
        )
        constraintSet.connect(
            view7.getId(), ConstraintSet.LEFT,
            view3.getId(), ConstraintSet.RIGHT,
            50
        )
        val viewIds2 = intArrayOf(view6.getId(), view7.getId())
        constraintSet.createVerticalChain(
            view3.getId(), ConstraintSet.TOP,
            view3.getId(), ConstraintSet.BOTTOM,
            viewIds2,
            null,
            CHAIN_SPREAD // ここ
        )

        /////////// チェーンを使って線形グループを管理するサンプル。水平方向
        // https://developer.android.com/training/constraint-layout?hl=ja
        val view4 = View(context)
        view4.id = View.generateViewId()
        view4.setBackgroundColor(Color.CYAN)
        layout.addView(view4)
        val view5 = View(context)
        view5.id = View.generateViewId()
        view5.setBackgroundColor(Color.MAGENTA)
        layout.addView(view5)
        constraintSet.constrainHeight(
            view4.getId(), 100
        )
        constraintSet.constrainHeight(
            view5.getId(), 100
        )
        constraintSet.connect(
            view4.getId(), ConstraintSet.TOP,
            view3.getId(), ConstraintSet.BOTTOM,
            30
        )
        constraintSet.connect(
            view5.getId(), ConstraintSet.TOP,
            view4.getId(), ConstraintSet.TOP,
            0
        )
        constraintSet.connect(
            view4.getId(), ConstraintSet.LEFT,
            ConstraintSet.PARENT_ID, ConstraintSet.LEFT
        )
        constraintSet.connect(
            view5.getId(), ConstraintSet.RIGHT,
            ConstraintSet.PARENT_ID, ConstraintSet.RIGHT
        )
        val viewIds = intArrayOf(view4.getId(), view5.getId())
        constraintSet.createHorizontalChain(
            ConstraintSet.PARENT_ID, ConstraintSet.LEFT,
            ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,
            viewIds,
            null,
            CHAIN_SPREAD // ここ
        )
        
        constraintSet.applyTo(layout)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}