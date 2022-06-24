package android.banananabandit.naughtycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var numbersDisplayResult : TextView? = null
    private var numbersDisplayWorkings : TextView? = null
    private var naughtyDisplay : TextView? = null

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
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
            // These will return true. The logic here is that it will allow us using negative numbers for the operations
        }
    }

    private fun generateResult() {

    }
    
    private fun subtractFunction(numbersDisplayValue : String) {
        val splitDisplayValue = numbersDisplayValue.split("-")
        var firstValue = splitDisplayValue[0]
        val secondValue = splitDisplayValue[1]

        if (prefix.isNotEmpty()) {
            firstValue = prefix + firstValue
        }

        numbersDisplayResult?.text = (firstValue.toDouble() - secondValue.toDouble()).toString()
    }

    private fun setListeners() {

        // Operators
        buttonMultiply.setOnClickListener{
            // If the variable is a nullable then we always need to use the let- to make sure that the code only executes if its not null
            numbersDisplayWorkings?.text?.let {
                if (lastNumIsNumber && !isOperatorUsed(it.toString())){
                    numbersDisplayWorkings?.append("x")
                    lastNumIsNumber = false
                    lastNumIsDot = false
                }
            }
        }
        buttonDivide.setOnClickListener {
            numbersDisplayWorkings?.text?.let {
                if (lastNumIsNumber && !isOperatorUsed(it.toString())){
                    numbersDisplayWorkings?.append("/")
                    lastNumIsNumber = false
                    lastNumIsDot = false
                }
            }
        }
        buttonAdd.setOnClickListener {
            numbersDisplayWorkings?.text?.let {
                if (lastNumIsNumber && !isOperatorUsed(it.toString())){
                    numbersDisplayWorkings?.append("+")
                    lastNumIsNumber = false
                    lastNumIsDot = false
                }
            }
        }
        buttonSubtract.setOnClickListener {
            numbersDisplayWorkings?.text?.let {
                if (lastNumIsNumber && !isOperatorUsed(it.toString())){
                    numbersDisplayWorkings?.append("-")
                    lastNumIsNumber = false
                    lastNumIsDot = false
                }
            }
        }

        buttonEnter.setOnClickListener {
            if (lastNumIsNumber) {
                // .text is a char sequence, this is why we need to convert it to String to do the operations
                var numbersDisplayValue = numbersDisplayWorkings?.text.toString()
                prefix = ""
                // the String operations is something that potentially can go wrong so hence we have to use try/catch block
                try {
                    if (numbersDisplayValue.startsWith("-")) {
                        prefix = "-"
                        // Substring method will get rid of the first character in the string
                        numbersDisplayValue = numbersDisplayValue.substring(1)
                    }
                    if (numbersDisplayValue.contains("-")) {
      // You are here
                        subtractFunction(numbersDisplayValue)

                    } else if (numbersDisplayValue.contains("+")) {

                        val splitDisplayValue = numbersDisplayValue.split("+")
                        var firstValue = splitDisplayValue[0]
                        val secondValue = splitDisplayValue[1]

                        if (prefix.isNotEmpty()) {
                            firstValue = prefix + firstValue
                        }

                        numbersDisplayResult?.text = (firstValue.toDouble() + secondValue.toDouble()).toString()
                    } else if (numbersDisplayValue.contains("x")) {

                        val splitDisplayValue = numbersDisplayValue.split("x")
                        var firstValue = splitDisplayValue[0]
                        val secondValue = splitDisplayValue[1]

                        if (prefix.isNotEmpty()) {
                            firstValue = prefix + firstValue
                        }

                        numbersDisplayResult?.text = (firstValue.toDouble() * secondValue.toDouble()).toString()
                    } else if (numbersDisplayValue.contains("/")) {

                        val splitDisplayValue = numbersDisplayValue.split("/")
                        var firstValue = splitDisplayValue[0]
                        val secondValue = splitDisplayValue[1]

                        if (prefix.isNotEmpty()) {
                            firstValue = prefix + firstValue
                        }

                        numbersDisplayResult?.text = (firstValue.toDouble() / secondValue.toDouble()).toString()
                    }

                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
                naughtyCalculation()
            }
        }


        //Numbers
        buttonOne.setOnClickListener{
            numbersDisplayWorkings?.append("1")
            lastNumIsNumber = true
            lastNumIsDot = false
            // Method to start automatically generate result (constantly updating)
            if (isOperatorUsed(numbersDisplayWorkings?.text.toString())) {

            }
        }
        buttonTwo.setOnClickListener{
            numbersDisplayWorkings?.append("2")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonThree.setOnClickListener{
            numbersDisplayWorkings?.append("3")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonFour.setOnClickListener{
            numbersDisplayWorkings?.append("4")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonFive.setOnClickListener{
            numbersDisplayWorkings?.append("5")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonSix.setOnClickListener{
            numbersDisplayWorkings?.append("6")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonSeven.setOnClickListener{
            numbersDisplayWorkings?.append("7")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonEight.setOnClickListener{
            numbersDisplayWorkings?.append("8")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonNine.setOnClickListener{
            numbersDisplayWorkings?.append("9")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonZero.setOnClickListener{
            numbersDisplayWorkings?.append("0")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonDot.setOnClickListener{
            if (lastNumIsNumber && !lastNumIsDot){
                if (numbersDisplayWorkings?.text?.contains(".") != true){
                    numbersDisplayWorkings?.append(".")
                    lastNumIsNumber = false
                    lastNumIsDot = true
                }
            }
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