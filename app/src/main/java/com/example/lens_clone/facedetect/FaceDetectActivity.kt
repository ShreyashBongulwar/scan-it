package com.example.lens_clone.facedetect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import com.example.lens_clone.BaseLensActivity

class FaceDetectActivity : BaseLensActivity() {
    override val imageAnalyzer= FaceDetectAnalyzer()

    override fun startScanner() {
        startFaceDetect()
    }

    private  fun startFaceDetect(){
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}