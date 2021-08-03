package com.example.sample

import android.app.AlertDialog
import android.content.Context

class MyDialogManager {

    fun show(context: Context, title: String, message: String, yesClosure:() -> Unit,
             noClosure:() -> Unit, cancelClosure:() -> Unit) {

        val dialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false) // モーダルとなり、ダイアログ外の領域をタップしてもダイアログがキャンセルされない
            .setPositiveButton("YES") { dialog, which ->
                println("YES")
                yesClosure()
            }
            .setNegativeButton("NO") { dialog, which ->
                println("NO")
                noClosure()
            }
            .setNeutralButton("Cancel") { dialog, which ->
                println("Cancel")
                cancelClosure()
            }
            .create()

        dialog.show()
    }

}