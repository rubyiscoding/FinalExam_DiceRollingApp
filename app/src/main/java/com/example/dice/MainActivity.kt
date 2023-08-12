package com.example.dice

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val prefsKey = "MyPrefs"
    private val customValueKey = "CustomValue"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(prefsKey, Context.MODE_PRIVATE)

        var getSpinnerValue = ""

        var themeSwitch = findViewById<Switch>(R.id.themeSwitch)
        themeSwitch.setOnCheckedChangeListener { _, onSwitch ->
            if (onSwitch) {
                themeSwitch.text = "Dark"
                // here we are changing the theme to light mode.
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                themeSwitch.text = "Light"
                // here we are changing the theme to dark mode.
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        var viewRoll1 = findViewById<TextView>(R.id.viewRoll1)
        var viewRoll2 = findViewById<TextView>(R.id.viewRoll2)
        // Create array of dice with different sides
        var dice = arrayOf("4", "6", "8", "10", "12", "20")
        var diceSpinner = findViewById<Spinner>(R.id.diceSpinner)
        var arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, dice
        )
        diceSpinner.adapter = arrayAdapter

        // Get selected spinner value and store in a variable
        diceSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                getSpinnerValue = dice[position]
            }
        }
        var randomValues1 = 0
        var randomValues2 = 0
        var btnRoll1 = findViewById<Button>(R.id.btnRoll1)
        // Perform one roll when Roll1 is clicked
        btnRoll1.setOnClickListener {
            val number1 = getSpinnerValue.toInt()
            randomValues1 = Random.nextInt(number1) + 1
            viewRoll1.text = randomValues1.toString()
        }

        // Perform two rolls when Roll2 is clicked
        var btnRoll2 = findViewById<Button>(R.id.btnRoll2)
        btnRoll2.setOnClickListener {
            val number1 = getSpinnerValue.toInt()
            val randomValues1 = Random.nextInt(number1) + 1
            val randomValues2 = Random.nextInt(number1) + 1
            viewRoll1.text = randomValues1.toString()
            viewRoll2.text = randomValues2.toString()
        }

        // Get the RadioGroup for dice types
        val diceTypeRadioGroup = findViewById<RadioGroup>(R.id.diceRadioGroup)
        diceTypeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio4sided -> {
                    // Handle the selection of 4-sided die
                    Toast.makeText(this, "You have selected 4-sided die!", Toast.LENGTH_SHORT)
                        .show()
                }

                R.id.radio6sided -> {
                    // Handle the selection of 6-sided die
                    Toast.makeText(this, "You have selected 6-sided die!", Toast.LENGTH_SHORT)
                        .show()
                }

                R.id.radio8sided -> {
                    // Handle the selection of 8-sided die
                    Toast.makeText(this, "You have selected 8-sided die!", Toast.LENGTH_SHORT)
                        .show()
                }

                R.id.radio10sided -> {
                    // Handle the selection of 10-sided die
                    Toast.makeText(this, "You have selected 10-sided die!", Toast.LENGTH_SHORT)
                        .show()
                }

                R.id.radio12sided -> {
                    // Handle the selection of 12-sided die
                    Toast.makeText(this, "You have selected 12-sided die!", Toast.LENGTH_SHORT)
                        .show()
                }

                R.id.radio20sided -> {
                    // Handle the selection of 20-sided die
                    Toast.makeText(this, "You have selected 20-sided die!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        // Set the default selected radio button here
        diceTypeRadioGroup.check(R.id.radio4sided)

        // Load and set custom value from SharedPreferences
        val savedCustomValue = sharedPreferences.getInt(customValueKey, 0)
        val inputSides = findViewById<EditText>(R.id.inputSides)
        inputSides.setText(savedCustomValue.toString())

        // Save custom value when the "Add" button is clicked
        val addButton = findViewById<Button>(R.id.button3)
        addButton.setOnClickListener {
            val customValue = inputSides.text.toString().toIntOrNull() ?: 0
            val editor = sharedPreferences.edit()
            editor.putInt(customValueKey, customValue)
            editor.apply()

            Toast.makeText(this, "Your value is saved!: $customValue", Toast.LENGTH_SHORT).show()

            // Button to roll the selected die
            val rollButton = findViewById<Button>(R.id.btnRoll)
            rollButton.setOnClickListener {
                val selectedDieSides = getSpinnerValue.toInt()
                val randomValue = Random.nextInt(selectedDieSides) + 1
                viewRoll1.text = randomValue.toString()
            }

            // Button to roll the selected die twice
            val rollTwiceButton = findViewById<Button>(R.id.btnRollTwice)
            rollTwiceButton.setOnClickListener {
                val selectedDieSides = getSpinnerValue.toInt()
                val randomValue1 = Random.nextInt(selectedDieSides) + 1
                val randomValue2 = Random.nextInt(selectedDieSides) + 1
                viewRoll1.text = randomValue1.toString()
                viewRoll2.text = randomValue2.toString()
            }
        }

        val btnRoll: Button = findViewById(R.id.btnRoll)
        btnRoll.setOnClickListener {
            val selectedDieSides = getSelectedDieSides() // Implement this function to get the selected die's sides
            val randomValue = (Math.random() * selectedDieSides + 1).toInt()

            Toast.makeText(this, "Rolled: $randomValue", Toast.LENGTH_SHORT).show()
        }

        val btnRollTwice: Button = findViewById(R.id.btnRollTwice)
        btnRollTwice.setOnClickListener {
            val selectedDieSides = getSelectedDieSides() // Implement this function to get the selected die's sides
            val randomValue1 = (Math.random() * selectedDieSides + 1).toInt()
            val randomValue2 = (Math.random() * selectedDieSides + 1).toInt()

            Toast.makeText(this, "Rolled: $randomValue1 and $randomValue2", Toast.LENGTH_SHORT).show()
        }



    }
    private fun getSelectedDieSides(): Int {
        val radioGroup: RadioGroup = findViewById(R.id.diceRadioGroup)
        val checkedRadioButtonId = radioGroup.checkedRadioButtonId

        val selectedRadioButton: RadioButton = findViewById(checkedRadioButtonId)
        val selectedDieSidesText = selectedRadioButton.text.toString()

        return selectedDieSidesText.substringBefore("-").toInt()
    }


}
