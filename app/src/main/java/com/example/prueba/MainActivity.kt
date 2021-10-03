package com.example.prueba
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.prueba.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
// Inflate the Activity
        setContentView(view)
// Method to add all the Button listeners
        addListeners()
    }
    private fun addListeners() {
// Name Button
        binding.btnName.setOnClickListener {
            hideKeyboard()
// Message behaviour
            var message = "${binding.etName.text}, You are using a field"
            if (binding.etName.text.isBlank()) {
                message = "Name field is empty"
            }
// Show SnackBar
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
// Next controller Button
        binding.btnNextController.setOnClickListener {
            startActivity(Intent(this, PickerActivity::class.java))
        }
    }
    private fun hideKeyboard() {
        val manager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}