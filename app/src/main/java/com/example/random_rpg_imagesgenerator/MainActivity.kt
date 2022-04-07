package com.example.random_rpg_imagesgenerator

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val images = assets.list("images")
        val dices = assets.list("dices")
        val buttonClick = findViewById<Button>(R.id.generate_button)
        val symbol = findViewById<ImageView>(R.id.symbol)
        val letter = findViewById<TextView>(R.id.letter)
        val digit = findViewById<TextView>(R.id.digit)
        val dice = findViewById<ImageView>(R.id.dice)

        buttonClick.setOnClickListener {
            // SYMBOL
            val randomIndexForSymbol = generateRandomNumber(images!!.size)
            val inputStreamForSymbol = assets.open("images/" + images[randomIndexForSymbol])
            val bitmapSymbol = BitmapFactory.decodeStream(inputStreamForSymbol)
            val coloredBitmapSymbol = paintBitmap(bitmapSymbol, Color.rgb(generateRandomNumber(256), generateRandomNumber(256), generateRandomNumber(256)))
            symbol.setImageBitmap(coloredBitmapSymbol)
            inputStreamForSymbol.close()

            // LETTER
            letter.text = alphabet.toCharArray()[generateRandomNumber(alphabet.length)].toString()

            // DIGIT
            digit.text = generateRandomNumber(100).toString()

            // DICE
            val randomIndexForDice = generateRandomNumber(dices!!.size)
            val inputStreamForDice = assets.open("dices/" + dices[randomIndexForDice])
            val bitmapDice = BitmapFactory.decodeStream(inputStreamForDice)
            dice.setImageBitmap(bitmapDice)
            inputStreamForDice.close()
        }
    }

    fun generateRandomNumber(size: Int): Int {
        return (0 until size).random()
    }

    fun paintBitmap(bitmap: Bitmap, color: Int): Bitmap? {
        val width = bitmap.width
        val height = bitmap.height
        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        for(i in 1..pixels.lastIndex) {
            if (pixels[i] == Color.BLACK) {
                pixels[i] = color
            }
        }
        val result = Bitmap.createBitmap(width, height, bitmap.config)
        result.setPixels(pixels, 0, width, 0, 0, width, height)
        return result
    }
}