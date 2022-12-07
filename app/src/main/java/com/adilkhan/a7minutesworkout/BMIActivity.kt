package com.adilkhan.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.adilkhan.a7minutesworkout.databinding.ActivityBmiactivityBinding
import com.adilkhan.a7minutesworkout.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    private var bmiBinding:ActivityBmiactivityBinding? = null
    private var radioButton : RadioButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bmiBinding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(bmiBinding?.root)
        setSupportActionBar(bmiBinding?.bmiToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // to add text write
        supportActionBar?.title = "CALCULATE BMI"
        bmiBinding?.bmiToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }
        bmiBinding?.rgUnits?.setOnCheckedChangeListener{
            _,id->
            radioButton = findViewById(id)
            if(radioButton==bmiBinding?.rbMetricUnits) displayMetricUnitView()
            else if(radioButton==bmiBinding?.rbUsUnits) displayUsUnitView()

        }
        bmiBinding?.btnCalculateBmi?.setOnClickListener {
            if(!validateMetricUnit())
            {
                Toast.makeText(this@BMIActivity, "Please enter the valid value",Toast.LENGTH_LONG).show()
            }
            else
            {
                val heightValue = bmiBinding?.etHeight?.text.toString().toFloat()/100
                val weightValue = bmiBinding?.etWeight?.text.toString().toFloat()
                val bmiValue = weightValue/(heightValue*heightValue)
                displayBmiResult(bmiValue)
            }
        }

    }
    private fun displayMetricUnitView()
    {
        bmiBinding?.llUSUnits?.visibility = View.INVISIBLE
        bmiBinding?.textBoxHeight?.visibility = View.VISIBLE
        bmiBinding?.textBoxWeight?.hint = "WEIGHT(in kg)"

    }
    private fun displayUsUnitView()
    {
        bmiBinding?.textBoxHeight?.visibility = View.INVISIBLE
        bmiBinding?.llUSUnits?.visibility = View.VISIBLE
        bmiBinding?.textBoxWeight?.hint = "WEIGHT(in pnd)"
    }
    private fun validateMetricUnit():Boolean{
        var isValid = true
            if (bmiBinding?.etHeight?.text.toString().isEmpty()) isValid = false
            else if (bmiBinding?.etWeight?.text.toString().isEmpty()) isValid = false

        return isValid
    }
    private fun displayBmiResult(bmiValue:Float)
    {     var bmiType:String = ""
          var bmiDescription : String = ""

        when{
            bmiValue in 0.0..18.4->
            {
                bmiType = "Under Weight"
                bmiDescription = "Oops You really need to take better care of yourself! Eat more"
            }
            bmiValue in 18.5..24.9->
            {
                bmiType = "Normal"
                bmiDescription = "WOW!! You are in good shape"
            }
            bmiValue in 25.0..30.0->
            {
                bmiType = "Over weight"
                bmiDescription = "Oops! You have to take better care of your self! Consumed less Calories"
            }
            bmiValue>30.0->{
                bmiType = "Obesity"
                bmiDescription = "Oops! You really need to take better care of yourself! Avoid Eating " +
                        "more calories product"
            }
        }

        bmiBinding?.tvYourBmi?.text = "Your bmi"
        bmiBinding?.tvBmiValue?.text = BigDecimal(bmiValue.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        bmiBinding?.tvBmiType?.text = bmiType
        bmiBinding?.tvDescription?.text = bmiDescription
        bmiBinding?.bmiResult?.visibility = View.VISIBLE

    }
}