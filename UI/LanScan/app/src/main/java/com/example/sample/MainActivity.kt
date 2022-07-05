package com.example.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stealthcopter.networktools.SubnetDevices
import com.stealthcopter.networktools.subnet.Device

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanLan()
    }

    // https://github.com/stealthcopter/AndroidNetworkTools を利用したサンプルです。
    // （https://github.com/csicar/Ning も良さそうだが。）
    private fun scanLan() {

        Logging.d("Scan started")

        val subnetDevices =
            SubnetDevices.fromLocalAddress().findDevices(object :
                SubnetDevices.OnSubnetDeviceFound {
                override fun onDeviceFound(device: Device?) {}
                override fun onFinished(devicesFound: ArrayList<Device?>) {
                    Logging.d("Scan Finished")
                    for (device in devicesFound) {
                        Logging.d("${device?.hostname}")
                        Logging.d("${device?.ip}")
                        Logging.d("${device?.mac}")
                    }
                }
            })

        // Below is example of how to cancel a running scan
        // subnetDevices.cancel();
    }

}