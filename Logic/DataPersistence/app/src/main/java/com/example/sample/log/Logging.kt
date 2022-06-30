package com.example.sample.log

import android.content.Context
import android.util.Log
import java.io.File

class Logging {

    companion object {
        var context: Context? = null

        private val DEBUG = true
        private val TAG = "Sample"

        private val WRITE_FILE = false
        private val FILE_NAME = "logger.txt"

        fun v(message: String?) {
            if (DEBUG) {
                val msg = getMeta() +  message ?: ""
                Log.v(TAG, msg)
                writeFile(msg)
            }
        }

        fun d(message: String?) {
            if (DEBUG) {
                val msg = getMeta() +  message ?: ""
                Log.d(TAG, msg)
                writeFile(msg)
            }
        }

        fun i(message: String?) {
            if (DEBUG) {
                val msg = getMeta() +  message ?: ""
                Log.i(TAG, msg)
                writeFile(msg)
            }
        }

        fun w(message: String?) {
            if (DEBUG) {
                val msg = getMeta() +  message ?: ""
                Log.w(TAG, msg)
                writeFile(msg)
            }
        }

        fun e(message: String?) {
            if (DEBUG) {
                val msg = getMeta() +  message ?: ""
                Log.e(TAG, msg)
                writeFile(msg)
            }
        }

        private fun getMeta(): String {
            val element = Thread.currentThread().stackTrace[4]
            val fullClassName = element.className
            val simpleClassName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1)
            val methodName = element.methodName
            val lineNumber = element.lineNumber
            return "[$simpleClassName $methodName:$lineNumber] "
        }

        //region file

        // ファイルに記録する
        private fun writeFile(message: String) {
            if (!WRITE_FILE) {
                return
            }
            context?.let {
                File(it.filesDir, FILE_NAME).appendText(message + "\n")
            }
        }

        fun deleteFile() {
            context?.let {
                val readFile = File(it.filesDir, FILE_NAME)
                if (readFile.exists()) {
                    readFile.delete()
                }
            }
        }

        fun readFile():String  {
            context?.let {
                val readFile = File(it.filesDir, FILE_NAME)
                if (readFile.exists()) {
                    return readFile.readText()
                }
            }
            return ""
        }

        //endregion
    }
}