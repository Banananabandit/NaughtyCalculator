package android.banananabandit.naughtycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var numbersDisplay : TextView? = null

    //These are the flags
    var lastNumIsDot : Boolean = false
    var lastNumIsNumber : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numbersDisplay = findViewById(R.id.numbersDisplay)

        val buttonOne = findViewById<Button>(R.id.buttonOne)
        val buttonTwo = findViewById<Button>(R.id.buttonTwo)
        val buttonThree = findViewById<Button>(R.id.buttonThree)
        val buttonFour = findViewById<Button>(R.id.buttonFour)
        val buttonFive = findViewById<Button>(R.id.buttonFive)
        val buttonSix = findViewById<Button>(R.id.buttonSix)
        val buttonSeven = findViewById<Button>(R.id.buttonSeven)
        val buttonEight = findViewById<Button>(R.id.buttonEight)
        val buttonNine = findViewById<Button>(R.id.buttonNein)
        val buttonZero = findViewById<Button>(R.id.buttonZero)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = findViewById<Button>(R.id.buttonMinus)
        val buttonDot = findViewById<Button>(R.id.buttonDot)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        val buttonEnter = findViewById<Button>(R.id.buttonEnter)

        setListeners()
        //Operators
        buttonMultiply.setOnClickListener{
            // If the variable is a nullable then we always need to use the let- to make sure that the code only executes if its not null
            numbersDisplay?.text?.let {
                if (lastNumIsNumber && !isOperatorUsed(it.toString())){
                    numbersDisplay?.append("x")
                    lastNumIsNumber = false
                    lastNumIsDot = false
                }
            }
        }
        buttonDivide.setOnClickListener {
            numbersDisplay?.text?.let {
                if (lastNumIsNumber && !isOperatorUsed(it.toString())){
                    numbersDisplay?.append("/")
                    lastNumIsNumber = false
                    lastNumIsDot = false
                }
            }
        }
        buttonAdd.setOnClickListener {
            numbersDisplay?.text?.let {
                if (lastNumIsNumber && !isOperatorUsed(it.toString())){
                    numbersDisplay?.append("+")
                    lastNumIsNumber = false
                    lastNumIsDot = false
                }
            }
        }
        buttonSubtract.setOnClickListener {
            numbersDisplay?.text?.let {
                if (lastNumIsNumber && !isOperatorUsed(it.toString())){
                    numbersDisplay?.append("-")
                    lastNumIsNumber = false
                    lastNumIsDot = false
                }
            }
        }

        buttonEnter.setOnClickListener {
            if (lastNumIsNumber) {
                // .text is a char sequence, this is why we need to convert it to String to do the operations
                val numbersDisplayValue = numbersDisplay?.text?.toString()
                // the String operations is something that potentially can go wrong so hence we have to use try/catch block
                try {
                    val splitDisplayValue = numbersDisplayValue?.split("-")
                    var firstValue = splitDisplayValue?.get(0)
                    var secondValue = splitDisplayValue?.get(1)

                    numbersDisplay?.text = (firstValue!!.toDouble() - secondValue!!.toDouble()).toString()

                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            }
        }


        //Numbers
        buttonOne.setOnClickListener{
            numbersDisplay?.append("1")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonTwo.setOnClickListener{
            numbersDisplay?.append("2")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonThree.setOnClickListener{
            numbersDisplay?.append("3")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonFour.setOnClickListener{
            numbersDisplay?.append("4")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonFive.setOnClickListener{
            numbersDisplay?.append("5")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonSix.setOnClickListener{
            numbersDisplay?.append("6")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonSeven.setOnClickListener{
            numbersDisplay?.append("7")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonEight.setOnClickListener{
            numbersDisplay?.append("8")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonNine.setOnClickListener{
            numbersDisplay?.append("9")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonZero.setOnClickListener{
            numbersDisplay?.append("0")
            lastNumIsNumber = true
            lastNumIsDot = false
        }
        buttonDot.setOnClickListener{
            if (lastNumIsNumber && !lastNumIsDot){
                if (!numbersDisplay!!.text.contains(".")){
                    numbersDisplay?.append(".")
                    lastNumIsNumber = false
                    lastNumIsDot = true
                }
            }
        }
        buttonClear.setOnClickListener{
            numbersDisplay?.text = ""
        }
        buttonDelete.setOnClickListener {
            val deleteLastChar = numbersDisplay?.text?.dropLast(1)
            numbersDisplay?.text = deleteLastChar
            lastNumIsNumber = true
            lastNumIsDot = false
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
    private fun setListeners() {

    }

}