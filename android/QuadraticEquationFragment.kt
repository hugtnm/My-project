package com.example.quadraticequationapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.math.sqrt

class QuadraticEquationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quadratic_equation, container, false)

        val editTextA = view.findViewById<EditText>(R.id.editTextA)
        val editTextB = view.findViewById<EditText>(R.id.editTextB)
        val editTextC = view.findViewById<EditText>(R.id.editTextC)
        val solveButton = view.findViewById<Button>(R.id.solveButton)
        val resultText = view.findViewById<TextView>(R.id.resultText)

        solveButton.setOnClickListener {
            try {
                val a = editTextA.text.toString().toDouble()
                val b = editTextB.text.toString().toDouble()
                val c = editTextC.text.toString().toDouble()

                if (a == 0.0) {
                    resultText.text = "Not a quadratic equation (a cannot be 0)"
                    return@setOnClickListener
                }

                val delta = b * b - 4 * a * c
                when {
                    delta > 0 -> {
                        val x1 = (-b + sqrt(delta)) / (2 * a)
                        val x2 = (-b - sqrt(delta)) / (2 * a)
                        resultText.text = "Two solutions: x₁ = $x1, x₂ = $x2"
                    }
                    delta == 0.0 -> {
                        val x = -b / (2 * a)
                        resultText.text = "One solution: x = $x"
                    }
                    else -> {
                        resultText.text = "No real solutions"
                    }
                }
            } catch (e: NumberFormatException) {
                resultText.text = "Please enter valid numbers"
            }
        }

        return view
    }
}