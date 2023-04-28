package com.B34.nutracker

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.File

class CameraActivity : AppCompatActivity() {
    private val cameraRequest = 1
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, cameraRequest)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            val photoURI: Uri = getImageURI(applicationContext, photo)
            // CALL THIS METHOD TO GET THE ACTUAL PATH FOR ML algorithm
            val photoFile: File = File(getRealPathFromURI(photoURI))


            val selected = "Selected"
            val extras = Bundle()
            extras.putString("food", selected)

            val intent = Intent (this, FoodInfo::class.java)
            intent.putExtras(extras)
            startActivity(intent)
        }
    }
    private fun getImageURI(context: Context, image: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String = MediaStore.Images.Media.insertImage(context.contentResolver, image, "Title", null)
        return Uri.parse(path)
    }

    private fun getRealPathFromURI(uri: Uri): String {
        var path = ""
        if (contentResolver != null) {
            contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                    path = cursor.getString(idx)
                }
            }
        }
        return path
    }
}