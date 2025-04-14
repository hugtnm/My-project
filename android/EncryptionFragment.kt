package com.example.quadraticequationapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import android.util.Base64

class EncryptionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_encryption, container, false)

        val editTextInput = view.findViewById<EditText>(R.id.editTextInput)
        val editTextKey = view.findViewById<EditText>(R.id.editTextKey)
        val encryptButton = view.findViewById<Button>(R.id.encryptButton)
        val decryptButton = view.findViewById<Button>(R.id.decryptButton)
        val resultText = view.findViewById<TextView>(R.id.resultText)

        encryptButton.setOnClickListener {
            try {
                val input = editTextInput.text.toString()
                val key = editTextKey.text.toString()
                if (input.isNotEmpty() && key.isNotEmpty()) {
                    val encrypted = encrypt(input, key)
                    resultText.text = "Encrypted: $encrypted"
                } else {
                    resultText.text = "Please enter text and key"
                }
            } catch (e: Exception) {
                resultText.text = "Encryption error"
            }
        }

        decryptButton.setOnClickListener {
            try {
                val input = editTextInput.text.toString()
                val key = editTextKey.text.toString()
                if (input.isNotEmpty() && key.isNotEmpty()) {
                    val decrypted = decrypt(input, key)
                    resultText.text = "Decrypted: $decrypted"
                } else {
                    resultText.text = "Please enter text and key"
                }
            } catch (e: Exception) {
                resultText.text = "Decryption error"
            }
        }

        return view
    }

    private fun encrypt(text: String, key: String): String {
        val keyBytes = key.toByteArray().copyOf(16)
        val secretKey = SecretKeySpec(keyBytes, "AES")
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encrypted = cipher.doFinal(text.toByteArray())
        return Base64.encodeToString(encrypted, Base64.DEFAULT)
    }

    private fun decrypt(text: String, key: String): String {
        val keyBytes = key.toByteArray().copyOf(16)
        val secretKey = SecretKeySpec(keyBytes, "AES")
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decrypted = cipher.doFinal(Base64.decode(text, Base64.DEFAULT))
        return String(decrypted)
    }
}