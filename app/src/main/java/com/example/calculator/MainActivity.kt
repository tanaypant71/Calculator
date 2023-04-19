package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view:View)
    {
        //Toast.makeText(this,"Button Clicked",Toast.LENGTH_SHORT).show()
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View)
    {
        tvInput?.text = ""
    }

    fun onDecimalPoint(view: View)
    {
        if (lastNumeric && !lastDot)
        {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View)
    {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString()))
            {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }


    @SuppressLint("SetTextI18n")
    fun onEqual(view: View)
    {
        if (lastNumeric)
        {
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-"))
                {
                    prefix = "-"
                    //the below statement does the following task
                    //if the value entered is "-99"
                    //it will get the string from the index 1
                    //i.e. "99" since "-" -> 0 , "9" -> 1 , "9" -> 2
                    tvValue = tvValue.substring(1)
                }
                //subtraction
                if (tvValue.contains("-"))
                {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]  // 99
                    var two = splitValue[1]  //  1

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    //perform a subtraction operation between two values, one and two,
                    // convert the result to a string, and
                    // set it as the text of a text view or
                    // an input field referred to as tvInput.
                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                //addition
                else if (tvValue.contains("+"))
                {
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]  // 99
                    var two = splitValue[1]  //  1

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
                //multiplication
                else if (tvValue.contains("*"))
                {
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]  // 99
                    var two = splitValue[1]  //  1

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
                //divison
                else if (tvValue.contains("/"))
                {
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]  // 99
                    var two = splitValue[1]  //  1

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
            }
            catch (e: ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String
    {
        var value = result
        if (result.contains(".0"))

            //here we are eliminating ".0" from the substring
            value = result.substring(0,result.length-2)
        return value
    }


    private fun isOperatorAdded(value: String): Boolean
    {
        return if (value.startsWith("-"))
        {
            false
        }
        else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+")
        }
    }

}