package android.banananabandit.naughtycalculator

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {
    private var numbersDisplayResult : TextView? = null
    private var numbersDisplayWorkings : TextView? = null
    private var naughtyDisplay : TextView? = null
    private var calculations = ""
    var result : Double? = null

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

                naughtyDisplay?.text = "Nice! Now multiply by $adjustedValue"

            } else if (numbersDisplayValue.toDouble() > 5000) {
                if (numbersDisplayValue.toDouble() < 80085.0) {
                    val adjustedValue = 80085 - numbersDisplayValue.toDouble()

                    naughtyDisplay?.text = "Awesome! Now add $adjustedValue"
                } else if (numbersDisplayValue.toDouble() > 80086.0) {
                    val adjustedValue = numbersDisplayValue.toDouble() - 80085

                    naughtyDisplay?.text = "Now subtract $adjustedValue"
            }
            }
        }
    }

    private fun generateResult() {
        try {
            var calc = ScriptEngineManager().getEngineByName("rhino")
            result = calc.eval(calculations) as Double?

            numbersDisplayResult?.text = result.toString()
        } catch (e : ArithmeticException) {
            e.printStackTrace()
        }
    }

    private fun setListeners() {

        buttonEnter.setOnClickListener {
            naughtyCalculation()
            numbersDisplayWorkings?.text = ""
            calculations = result.toString()
            numbersDisplayWorkings?.text = calculations
            numbersDisplayResult?.text = ""
        }
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

        //Numbers
        buttonOne.setOnClickListener{
            setCalculations("1")
            generateResult()
        }
        buttonTwo.setOnClickListener{
            setCalculations("2")
            generateResult()
        }
        buttonThree.setOnClickListener{
            setCalculations("3")
            generateResult()
        }
        buttonFour.setOnClickListener{
            setCalculations("4")
            generateResult()
        }
        buttonFive.setOnClickListener{
            setCalculations("5")
            generateResult()
        }
        buttonSix.setOnClickListener{
            setCalculations("6")
            generateResult()
        }
        buttonSeven.setOnClickListener{
            setCalculations("7")
            generateResult()
        }
        buttonEight.setOnClickListener{
            setCalculations("8")
            generateResult()
        }
        buttonNine.setOnClickListener{
            setCalculations("9")
            generateResult()
        }
        buttonZero.setOnClickListener{
            setCalculations("0")
            generateResult()
        }
        buttonDot.setOnClickListener{
            setCalculations(".")
        }
        buttonClear.setOnClickListener{
            numbersDisplayResult?.text = ""
            numbersDisplayWorkings?.text = ""
            naughtyDisplay?.text = ""
            calculations = ""
        }
        buttonDelete.setOnClickListener {
            val deleteLastChar = numbersDisplayWorkings?.text?.dropLast(1)
            val deleteLastCharWorkings = calculations.dropLast(1)
            numbersDisplayWorkings?.text = deleteLastChar
            calculations = deleteLastCharWorkings
            if (!calculations.endsWith("+") &&
                !calculations.endsWith("-") &&
                !calculations.endsWith("/") &&
                !calculations.endsWith("*")) {
                generateResult()
            }
        }
    }
}