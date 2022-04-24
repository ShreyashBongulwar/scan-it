package com.example.lens_clone.imagelaber

import androidx.camera.core.ImageAnalysis
import androidx.core.content.ContextCompat
import com.example.lens_clone.BaseLensActivity

class ImageLabelingActivity : BaseLensActivity() {

    override val imageAnalyzer = ImageLableAnalyser()

    override fun startScanner() {
        startImageLabeling()
    }

    private fun startImageLabeling(){
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }
}