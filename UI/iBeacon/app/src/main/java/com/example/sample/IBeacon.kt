package com.example.sample

import java.util.*

class IBeacon constructor(
    private var scanRecord: ByteArray, var rssi: Int) {

    var uuid: String
    var major: String
    var minor: String

    init {
        uuid = convertUUID(scanRecord)
        major = convertMajor(scanRecord)
        minor = convertMinor(scanRecord)
    }

    private fun convertUUID(scanRecord: ByteArray): String {

        return (IntToHex2(scanRecord[9].toInt() and 0xff)
                + IntToHex2(scanRecord[10].toInt() and 0xff)
                + IntToHex2(scanRecord[11].toInt() and 0xff)
                + IntToHex2(scanRecord[12].toInt() and 0xff)
                + "-"
                + IntToHex2(scanRecord[13].toInt() and 0xff)
                + IntToHex2(scanRecord[14].toInt() and 0xff)
                + "-"
                + IntToHex2(scanRecord[15].toInt() and 0xff)
                + IntToHex2(scanRecord[16].toInt() and 0xff)
                + "-"
                + IntToHex2(scanRecord[17].toInt() and 0xff)
                + IntToHex2(scanRecord[18].toInt() and 0xff)
                + "-"
                + IntToHex2(scanRecord[19].toInt() and 0xff)
                + IntToHex2(scanRecord[20].toInt() and 0xff)
                + IntToHex2(scanRecord[21].toInt() and 0xff)
                + IntToHex2(scanRecord[22].toInt() and 0xff)
                + IntToHex2(scanRecord[23].toInt() and 0xff)
                + IntToHex2(scanRecord[24].toInt() and 0xff))
    }

    private fun convertMajor(scanRecord: ByteArray): String {

        val hexMajor = IntToHex2(scanRecord[25].toInt() and 0xff) +
                IntToHex2(scanRecord[26].toInt() and 0xff)
        return hexMajor.toInt(16).toString()
    }

    private fun convertMinor(scanRecord: ByteArray): String {

        val hexMinor = IntToHex2(scanRecord[27].toInt() and 0xff) +
                IntToHex2(scanRecord[28].toInt() and 0xff)
        return hexMinor.toInt(16).toString()
    }

    private fun IntToHex2(i: Int): String {

        val hex_2 = charArrayOf(
            Character.forDigit(i shr 4 and 0x0f, 16),
            Character.forDigit(i and 0x0f, 16)
        )
        val hex_2_str = String(hex_2)
        return hex_2_str.uppercase(Locale.getDefault())
    }
}