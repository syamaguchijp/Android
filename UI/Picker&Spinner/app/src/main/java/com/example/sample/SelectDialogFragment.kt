package com.example.sample

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

// ラジオボタンで候補を表示して選択させるDialogFragment
class SelectDialogFragment: DialogFragment() {

    var closure: ((result: String) -> Unit)? = null
    var list: Array<String>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var selectItem:Int? = null
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Select prefecture below")
        //ラジオボタンを設置
        builder.setSingleChoiceItems(
            list,-1,{ dialog, which ->
                selectItem = which
            })
        builder.setPositiveButton("OK", { dialog, which ->
            if (closure != null && selectItem != null && list != null) {
                closure!!(list!![selectItem!!])
            }
        })

        val dialog = builder.create()
        return dialog
    }
}