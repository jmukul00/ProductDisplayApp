package com.example.productapp

import com.example.productapp.database.Product
import java.util.*

class Common {

    companion object{
        lateinit var clickedProduct: Product
        val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"

        fun getRandomString() : String {
            val random = Random()
            val sb = StringBuilder(5)
            for (i in 0 until 5)
                sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
            return sb.toString()
        }
    }
}