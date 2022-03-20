package com.example.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import com.example.tip.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    //    With a little more work up front, view binding makes it much easier
//    and faster to call methods on the views in your UI. You'll need to enable view binding for your app in Gradle, and make some code changes.
//    Instead of calling findViewById() for each View in your app, you'll create and initialize a binding object once.
//    The lateinit keyword is something new.
//    It's a promise that your code will initialize the variable before using it. If you don't, your app will crash.
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        This line initializes the binding object which you'll use to access Views in the activity_main.xml layout.
        binding = ActivityMainBinding.inflate(layoutInflater)
//        change this from R.layout.activity_main
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

    }

    private fun calculateTip() {
////        binding root way
//        val stringInTextField = binding.costOfService.text.toString()
//        val cost = stringInTextField.toDoubleOrNull() ?: return
//        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
//            R.id.option_twenty_percent -> 0.20
//            R.id.option_eighteen_percent -> 0.18
//            else -> 0.15
//        }
////    Note the use of var instead of val.
////    This is because you may need to round up the value if the user selected that option, so the value might change.
//        var tip = tipPercentage * cost
//        if (binding.roundUpSwitch.isChecked) {
//            tip = kotlin.math.ceil(tip)
//        }
//        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
//        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

///*Regular way with findviewbyid */
        val costText: EditText = findViewById(R.id.cost_of_service)
        val costOfService = costText.text.toString().toDouble()
        val selected: RadioGroup = findViewById(R.id.tipOptions)
        val id = selected.checkedRadioButtonId
        val tipPercent = when (id) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tipping = tipPercent * costOfService
        val roundingSwitch: Boolean = findViewById<Switch?>(R.id.round_up_switch).isChecked
        if (roundingSwitch) {
            tipping = kotlin.math.ceil(tipping)
        }
        val formatTip = NumberFormat.getCurrencyInstance().format(tipping)
        val tippingResult: TextView = findViewById(R.id.tip_result)
        tippingResult.text = getString(R.string.tip_amount, formatTip)
    }
}
