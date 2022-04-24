package com.example.lens_clone.textrecog

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class TextAnalyzer :ImageAnalysis.Analyzer {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {

        Log.d("TEXT","image analysed")
        imageProxy.image?.let{
            var inputImage= InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )
            recognizer.process(inputImage).addOnSuccessListener {text->
                text.textBlocks.forEach { blocks ->
                    Log.d(
                        "TEXT", """
                        LINES=${blocks.lines.joinToString("\n"){it.text}}
                        """.trimIndent()

                    )
                }
            }.addOnFailureListener{ex ->
                Log.e("TEXT","Detection Failed" ,ex)
            }.addOnCompleteListener{
                imageProxy.close()
            }

        } ?: imageProxy.close()

    }
}