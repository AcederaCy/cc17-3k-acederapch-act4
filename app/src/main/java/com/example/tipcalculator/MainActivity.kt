package com.example.tipcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var billAmountEditText: EditText
    private lateinit var tipResultTextView: TextView
    private lateinit var calculateButton: Button
    private lateinit var amazingRadioButton: RadioButton
    private lateinit var goodRadioButton: RadioButton
    private lateinit var okRadioButton: RadioButton
    private lateinit var roundUpSwitch: androidx.appcompat.widget.SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        billAmountEditText = findViewById(R.id.billAmountEditText)
        tipResultTextView = findViewById(R.id.tipResultTextView)
        calculateButton = findViewById(R.id.calculateButton)
        amazingRadioButton = findViewById(R.id.amazingRadioButton)
        goodRadioButton = findViewById(R.id.goodRadioButton)
        okRadioButton = findViewById(R.id.okRadioButton)
        roundUpSwitch = findViewById(R.id.roundUpSwitch)

        calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringInTextField = billAmountEditText.text.toString()
        if (stringInTextField.isEmpty()) {
            displayTip(0.0)
            return
        }
        val billAmount = stringInTextField.toDoubleOrNull() ?: 0.0
        val tipPercentage = when {
            amazingRadioButton.isChecked -> 0.20
            goodRadioButton.isChecked -> 0.18
            okRadioButton.isChecked -> 0.15
            else -> 0.0
        }

        var tip = billAmount * tipPercentage
        if (roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun displayTip(tip: Double) {
        val formattedTip = String.format("%.2f", tip)
        tipResultTextView.text = "Tip Amount: $$formattedTip"
    }
}