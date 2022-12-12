package com.example.fistapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fistapp.databinding.ActivityMainBinding
import java.text.NumberFormat

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateBtn.setOnClickListener { calculatTip() }
    }

    private fun calculatTip() {
        val stringInTxtfield = binding.costOfService.text.toString()
        val cost = stringInTxtfield.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text = ""
            return
        }
        val selectedPercentage = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedPercentage){
            R.id.bist_percent -> 0.20
            R.id.fifteen_percent -> 0.15
            else -> 0.1
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }
}