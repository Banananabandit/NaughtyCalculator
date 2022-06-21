package android.banananabandit.naughtycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var numbersDisplay : TextView? = null

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

        buttonOne.setOnClickListener{
            numbersDisplay?.append("1")
        }
        buttonTwo.setOnClickListener{
            numbersDisplay?.append("2")
        }
        buttonThree.setOnClickListener{
            numbersDisplay?.append("3")
        }
        buttonFour.setOnClickListener{
            numbersDisplay?.append("4")
        }
        buttonFive.setOnClickListener{
            numbersDisplay?.append("5")
        }
        buttonSix.setOnClickListener{
            numbersDisplay?.append("6")
        }
        buttonSeven.setOnClickListener{
            numbersDisplay?.append("7")
        }
        buttonEight.setOnClickListener{
            numbersDisplay?.append("8")
        }
        buttonNine.setOnClickListener{
            numbersDisplay?.append("9")
        }
        buttonZero.setOnClickListener{
            numbersDisplay?.append("0")
        }
        buttonClear.setOnClickListener{
            numbersDisplay?.text = ""
        }


    }
}