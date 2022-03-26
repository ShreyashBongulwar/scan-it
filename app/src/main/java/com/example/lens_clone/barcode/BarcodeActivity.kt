package com.example.lens_clone.barcode

import android.content.pm.PackageManager

import android.os.Bundle

import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.lens_clone.BaseLensActivity
import com.example.lens_clone.R
import kotlinx.android.synthetic.main.activity_barcode.*


class BarcodeActivity : BaseLensActivity() {


    override val imageAnalyzer = BarcodeAnalyzer()

    private fun scanBarcode() {
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        askCameraPermission()

        btnstartScanner.setOnClickListener {
            scanBarcode()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Permission Error")
                    .setMessage("Camera Permission not provided")
                    .setPositiveButton("OK") { _, _ -> finish() }
                    .setCancelable(false)
                    .show()
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}