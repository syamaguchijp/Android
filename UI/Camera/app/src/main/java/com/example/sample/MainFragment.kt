package com.example.sample

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment


class MainFragment : Fragment() {

    private val Image_Capture_Code = 1
    private var imageView: ImageView? = null
    private var cameraObserver: CameraObserver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Logging.d("")
        val v = inflater.inflate(R.layout.fragment_main, container, false)

        imageView = v.findViewById<ImageView>(R.id.imageView3)
        val cameraButton = v.findViewById<Button>(R.id.button2)
        cameraButton.setOnClickListener { v ->
            // インテントでカメラ起動
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, Image_Capture_Code)
        }

        getContext()?.let { getActivity()?.let { it1 ->
            cameraObserver = CameraObserver(it, it1) } }

        return v
    }

    override fun onResume() {

        Logging.d("")
        super.onResume()

        cameraObserver?.let {
            it.checkCameraPermission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Logging.d("")
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != Image_Capture_Code) {
            return
        }
        val bitmap: Bitmap?
        data?.let {
            if (it.extras == null) {
                Logging.d("canceled")
                return
            } else {
                bitmap = it.extras!!["data"] as Bitmap?
                if (bitmap != null) {
                    // 画像サイズ
                    Logging.d("${bitmap.width} ${bitmap.height}")
                }
            }
            // 撮影した画像をimageViewに表示する
            imageView!!.setImageBitmap(bitmap)
        }

        // なお、画像をストレージに保存する場合は、WRITE_EXTERNAL_STORAGEのパーミッションなどが必要。


    }
}