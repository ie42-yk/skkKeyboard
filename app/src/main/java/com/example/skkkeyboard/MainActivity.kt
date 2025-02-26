package com.example.skkkeyboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skkkeyboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
              // Check keyboard status
              if (isKeyboardEnabled())
                  btnEnableKeyboard.isEnabled = false
              btnEnableKeyboard.setOnClickListener {
                  if (!isKeyboardEnabled())
                    openKeyboardSettings()
              }
              btnChooseKeyboard.setOnClickListener {
                  if (isKeyboardEnabled())
                      openKeyboardChooserSettings()
                  else Toast.makeText(
                    this@MainActivity,
                    "Choose the keyboard activation button",
                    Toast.LENGTH_SHORT
                  ).show()
              }
        }
    }

    private fun openKeyboardSettings() {
        val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
        startActivity(intent)
    }

    private fun isKeyboardEnabled(): Boolean {
        val inputmethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val enableInputMethods = inputmethodManager.enabledInputMethodList.map { it.id }
        return enableInputMethods.contains("com.example.skkKeyboard/.InputMethodService")
    }

    private fun openKeyboardChooserSettings() {
        val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.showInputMethodPicker()
    }
}
