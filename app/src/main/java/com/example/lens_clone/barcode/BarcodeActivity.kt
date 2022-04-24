package com.example.lens_clone.barcode

import android.content.pm.PackageManager

import android.os.Bundle

import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.lens_clone.BaseLensActivity
import com.example.lens_clone.R
import kotlinx.android.synthetic.main.activity_lens.*


class BarcodeActivity : BaseLensActivity() {


    override val imageAnalyzer = BarcodeAnalyzer()
    override fun startScanner() {
        scanBarcode()
    }

    private fun scanBarcode() {
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )


    }

}