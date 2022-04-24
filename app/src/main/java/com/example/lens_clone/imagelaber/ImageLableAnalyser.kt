package com.example.lens_clone.imagelaber

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions


class ImageLableAnalyser : ImageAnalysis.Analyzer {


    private val labler = ImageLabeling.getClient(
        ImageLabelerOptions.Builder()
            .setConfidenceThreshold(0.7F)
            .build()
    )


    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {

        Log.d("LABEL","image analysed")
        imageProxy.image?.let{
            var inputImage= InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )
            labler.process(inputImage).addOnSuccessListener {labels->
                labels.forEach { label ->
                    Log.d(
                        "LABEL", """
                        Format=${label.text}
                        Value=${label.confidence}
                        """.trimIndent()

                    )
                }
            }.addOnFailureListener{ex ->
                Log.e("LABEL","Detection Failed" ,ex)
            }.addOnCompleteListener{
                imageProxy.close()
            }

        } ?: imageProxy.close()

    }
}