package ro.serdiz.se.core

import android.util.Log
import ro.serdiz.se.core.Constants.TAG

class Utils {
    companion object {
        fun printMessage(message: String) {
            Log.d(TAG, message)

        }
        fun print(e: Exception) = Log.e(TAG, e.stackTraceToString())

    }
}