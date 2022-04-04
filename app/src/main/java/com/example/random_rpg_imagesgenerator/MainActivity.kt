package com.example.random_rpg_imagesgenerator

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val images = assets.list("images")
        val buttonClick = findViewById<Button>(R.id.generate_button)
        val imageView = findViewById<ImageView>(R.id.image_view)

        buttonClick.setOnClickListener {
            val randomIndex = (0..images!!.size).random()
            val inputStream = assets.open("images/" + images[randomIndex])
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageView.setImageBitmap(bitmap)
            inputStream.close()
        }
    }
}