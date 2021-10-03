package com.example.prueba
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.prueba.databinding.ActivityPickerBinding
import com.google.android.material.snackbar.Snackbar
class PickerActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityPickerBinding
    lateinit var view: View
    private val options = arrayListOf("DatePicker", "TimePicker")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickerBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)
// Enable back button in the Native Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val spinnerAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            options,
        )
        binding.spPickerSelected.apply {
            adapter = spinnerAdapter
            setSelection(0, true)
            onItemSelectedListener = this@PickerActivity
        }
        showPickerSelected()
        addListener()
    }
    private fun addListener() {
        binding.btnShowInfo.setOnClickListener {
            when (binding.spPickerSelected.selectedItemPosition) {
                0 -> {
                    binding.pickerDate.apply {
                        val message = "Date is $dayOfMonth/$month/$year"
                        Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    binding.pickerTime.apply {
                        val message = "Time is $hour:$minute"
                        Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private fun showPickerSelected() {
        when (binding.spPickerSelected.selectedItemPosition) {
            0 -> {
                binding.pickerDate.visibility = View.VISIBLE
                binding.pickerTime.visibility = View.GONE
                binding.btnShowInfo.text = "Get Date"
            }
            1 -> {
                binding.pickerDate.visibility = View.GONE
                binding.pickerTime.visibility = View.VISIBLE
                binding.btnShowInfo.text = "Get Time"
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        showPickerSelected()
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {}
}