package com.example.lens_clone.barcode

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer: ImageAnalysis.Analyzer
{
    var scanner=BarcodeScanning.getClient()
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy)
    {
        Log.d("BARCODE","image analysed")
        imageProxy.image?.let{
            var inputImage=InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )
            scanner.process(inputImage).addOnSuccessListener {codes->
                codes.forEach { barcode ->
                    Log.d(
                        "BARCODE", """
                        Format=${barcode.format}
                        Value=${barcode.rawValue}
                        """.trimIndent()

                    )
                }
            }.addOnFailureListener{ex ->
                Log.e("BARCODE","Detection Failed" ,ex)
        }.addOnCompleteListener{
            imageProxy.close()
            }

        } ?: imageProxy.close()

    }
}