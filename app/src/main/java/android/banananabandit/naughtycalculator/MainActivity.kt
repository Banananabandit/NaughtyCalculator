package android.banananabandit.naughtycalculator

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {
    private var numbersDisplayResult : TextView? = null
    private var numbersDisplayWorkings : TextView? = null
    private var naughtyDisplay : TextView? = null
    private var calculations = ""

    private lateinit var buttonOne : Button
    private lateinit var buttonTwo : Button
    private lateinit var buttonThree : Button
    private lateinit var buttonFour : Button
    private lateinit var buttonFive : Button
    private lateinit var buttonSix : Button
    private lateinit var buttonSeven : Button
    private lateinit var buttonEight : Button
    private lateinit var buttonNine : Button
    private lateinit var buttonZero : Button
    private lateinit var buttonMultiply : Button
    private lateinit var buttonDivide : Button
    private lateinit var buttonAdd : Button
    private lateinit var buttonSubtract : Button
    private lateinit var buttonDot : Button
    private lateinit var buttonDelete : Button
    private lateinit var buttonClear : Button
    private lateinit var buttonEnter : Button

    //These are the flags
    var lastNumIsDot : Boolean = false
    var lastNumIsNumber : Boolean = false

    private lateinit var prefix : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numbersDisplayResult = findViewById(R.id.numbersDisplayResult)
        numbersDisplayWorkings = findViewById(R.id.numbersDisplayWorkings)
        naughtyDisplay = findViewById(R.id.naughtyDisplay)

        buttonOne = findViewById(R.id.buttonOne)
        buttonTwo = findViewById(R.id.buttonTwo)
        buttonThree = findViewById(R.id.buttonThree)
        buttonFour = findViewById(R.id.buttonFour)
        buttonFive = findViewById(R.id.buttonFive)
        buttonSix = findViewById(R.id.buttonSix)
        buttonSeven = findViewById(R.id.buttonSeven)
        buttonEight = findViewById(R.id.buttonEight)
        buttonNine = findViewById(R.id.buttonNein)
        buttonZero = findViewById(R.id.buttonZero)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSubtract = findViewById(R.id.buttonMinus)
        buttonDot = findViewById(R.id.buttonDot)
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonClear = findViewById(R.id.buttonClear)
        buttonEnter = findViewById(R.id.buttonEnter)

        setListeners()
    }

    // Leave this code for later use on another project
//    private fun whatsApp() {
//        val text = numbersDisplayWorkings?.text.toString()
//        val intent = Intent()
//        intent.action = Intent.ACTION_SEND
//        intent.putExtra(Intent.EXTRA_TEXT, text)
//        intent.type = "text/plain"
//        intent.setPackage("com.whatsapp")
//        startActivity(intent)
//    }

    private fun setCalculations(givenValue : String) {
        calculations += givenValue
        numbersDisplayWorkings?.text = calculations
    }

    private fun naughtyCalculation() {
        val numbersDisplayValue = numbersDisplayResult?.text.toString()

        if (numbersDisplayValue.toDouble() > 0) {
            if (numbersDisplayValue.toDouble() == 80085.0) {
                naughtyDisplay?.text = "NOICE!"
            } else if (numbersDisplayValue.toDouble() < 500) {
                val adjustedValue = (80085 / numbersDisplayValue.toDouble()) - 53.0

                naughtyDisplay?.text = "Nice! Now nultiply by $adjustedValue"

            } else if (numbersDisplayValue.toDouble() > 5000) {
                if (numbersDisplayValue.toDouble() < 80085.0) {
                    val adjustedValue = 80085 - numbersDisplayValue.toDouble()

                    naughtyDisplay?.text = "Awesome! Now add $adjustedValue"
                } else if (numbersDisplayValue.toDouble() > 80086.0) {
                    val adjustedValue = numbersDisplayValue.toDouble() - 80085

                    naughtyDisplay?.text = "Now subtruct $adjustedValue"
            }
            }
        }
    }

    private fun isOperatorUsed(value : String) : Boolean {
        return if (value.startsWith("-")){
            false
        } else {
            value.contains("/")
                    || value.contains("x")
                    || value.contains("+")
                    || value.contains("-")
            // These will return true. The logic here is that it will allow us using negative numbers for the operations
        }
    }

    private fun generateResult() {
        var numbersDisplayValue = numbersDisplayWorkings?.text.toString()
        prefix = ""
        try {
            if (numbersDisplayValue.startsWith("-")) {
                prefix = "-"
                // Substring method will get rid of the first character in the string
                numbersDisplayValue = numbersDisplayValue.substring(1)
            }
            if (numbersDisplayValue.contains("-")) {
                operatorFunction(numbersDisplayValue, "-")

            } else if (numbersDisplayValue.contains("+")) {
            operatorFunction(numbersDisplayValue, "+")

            } else if (numbersDisplayValue.contains("x")) {
            operatorFunction(numbersDisplayValue, "x")

            } else if (numbersDisplayValue.contains("/")) {
            operatorFunction(numbersDisplayValue, "/")
        }
            naughtyCalculation()

        } catch (e : java.lang.ArithmeticException){
            e.printStackTrace()
        }


    }

    private fun operatorFunction(numbersDisplayValue : String, operator : String) {
        val splitDisplayValue = numbersDisplayValue.split(operator)
        var firstValue = splitDisplayValue[0]
        val secondValue = splitDisplayValue[1]

        if (prefix.isNotEmpty()) {
            firstValue = prefix + firstValue
        }
        when(operator) {
            "-" -> numbersDisplayResult?.text = (firstValue.toDouble() - secondValue.toDouble()).toString()
            "+" -> numbersDisplayResult?.text = (firstValue.toDouble() + secondValue.toDouble()).toString()
            "/" -> numbersDisplayResult?.text = (firstValue.toDouble() / secondValue.toDouble()).toString()
            "x" -> numbersDisplayResult?.text = (firstValue.toDouble() * secondValue.toDouble()).toString()
        }

    }


    private fun setListeners() {
        // Operators
        buttonMultiply.setOnClickListener{
            setCalculations("*")
        }
        buttonDivide.setOnClickListener {
            setCalculations("/")
        }
        buttonAdd.setOnClickListener {
            setCalculations("+")
        }
        buttonSubtract.setOnClickListener {
            setCalculations("-")
        }

        buttonEnter.setOnClickListener {
            var result : Double? = null

            var calc = ScriptEngineManager().getEngineByName("rhino")
            result = calc.eval(calculations) as Double?

            numbersDisplayResult?.text = result.toString()
        }


        //Numbers
        buttonOne.setOnClickListener{
            setCalculations("1")
        }
        buttonTwo.setOnClickListener{
            setCalculations("2")
        }
        buttonThree.setOnClickListener{
            setCalculations("3")
        }
        buttonFour.setOnClickListener{
            setCalculations("4")
        }
        buttonFive.setOnClickListener{
            setCalculations("5")
        }
        buttonSix.setOnClickListener{
            setCalculations("6")
        }
        buttonSeven.setOnClickListener{
            setCalculations("7")
        }
        buttonEight.setOnClickListener{
            setCalculations("8")
        }
        buttonNine.setOnClickListener{
            setCalculations("9")
        }
        buttonZero.setOnClickListener{
            setCalculations("0")
        }
        buttonDot.setOnClickListener{
            setCalculations(".")
        }
        buttonClear.setOnClickListener{
            numbersDisplayResult?.text = ""
            numbersDisplayWorkings?.text = ""
            naughtyDisplay?.text = ""
        }
        buttonDelete.setOnClickListener {
            val deleteLastChar = numbersDisplayWorkings?.text?.dropLast(1)
            numbersDisplayWorkings?.text = deleteLastChar
            lastNumIsNumber = true
            lastNumIsDot = false
        }
    }


}