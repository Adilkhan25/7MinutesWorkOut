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

    // radio button use to checked which view is enable
    // by default metric unit selected
    private var radioButton :RadioButton? = null
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

        // radio group
        bmiBinding?.rgUnits?.setOnCheckedChangeListener{
            _,id->
            // checked which radio button is enable
            radioButton = findViewById(id)
            // if metric unit enable then  call enable metric view
            if(radioButton==bmiBinding?.rbMetricUnits) displayMetricUnitView()
            // if US unit enable then call US view
            else if(radioButton==bmiBinding?.rbUsUnits) displayUsUnitView()

        }
        bmiBinding?.btnCalculateBmi?.setOnClickListener {
            // if any edit text field is empty then show a toast message to the user
            if(!validateMetricUnit())
            {
                Toast.makeText(this@BMIActivity, "Please enter the valid value",Toast.LENGTH_LONG).show()
            }
            else
            {   // if all edit text filled by user then call calculate bmi
                calculateBmi()
            }
        }

    }

    // Enable metric View
    private fun displayMetricUnitView()
    {
        bmiBinding?.llUSUnits?.visibility = View.INVISIBLE
        bmiBinding?.textBoxHeight?.visibility = View.VISIBLE
        bmiBinding?.textBoxWeight?.hint = "WEIGHT(in kg)"

    }

    // Enable US View
    private fun displayUsUnitView()
    {
        bmiBinding?.textBoxHeight?.visibility = View.INVISIBLE
        bmiBinding?.llUSUnits?.visibility = View.VISIBLE
        bmiBinding?.textBoxWeight?.hint = "WEIGHT(in pounds)"
    }

    // below given method will checked that all edit text is fill or not by user
    private fun validateMetricUnit():Boolean{
        var isValid = true

        // in both Unit weight is same just changing the unit
        if (bmiBinding?.etWeight?.text.toString().isEmpty()) isValid = false
        //if metric view is enable then check height
        if(radioButton==bmiBinding?.rbMetricUnits || radioButton==null)
            if (bmiBinding?.etHeight?.text.toString().isEmpty()) isValid = false
        // if US unit View is enable then check fit and inch
        else if(radioButton==bmiBinding?.rbUsUnits)
            {
                    if(bmiBinding?.etFit?.text.toString().isEmpty()) isValid = false
                    else if(bmiBinding?.etInch?.text.toString().isEmpty()) isValid = false
            }

        return isValid
    }

    //below method will calculate bmi
    private fun calculateBmi()
    {
        // metric view is enable then calculate bmi in metric unit
        if(radioButton==bmiBinding?.rbMetricUnits || radioButton==null)
        {
            val weight = bmiBinding?.etWeight?.text.toString().toFloat()
            val height = bmiBinding?.etHeight?.text.toString().toFloat()/100
            val bmi = weight/(height*height)
            displayBmiResult(bmi)

        }
        // Us view is enable then calculate bmu in US unit
        else if(radioButton==bmiBinding?.rbUsUnits)
        {
            val weight = bmiBinding?.etWeight?.text.toString().toFloat()
            val fit = bmiBinding?.etFit?.text.toString().toFloat()
            val inch = bmiBinding?.etInch?.text.toString().toFloat()
            val height  = inch + fit*12
            val bmi = 703*(weight/(height*height))
            displayBmiResult(bmi)
        }
    }


    private fun displayBmiResult(bmiValue:Float)
    {     var bmiType:String = ""
          var bmiDescription : String = ""

        when{
            bmiValue in 0.0..18.49->
            {
                bmiType = "Under Weight"
                bmiDescription = "Oops You really need to take better care of yourself! Eat more"
            }
            bmiValue in 18.5..24.99->
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