package com.example.skkkeyboard

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import com.example.skkkeyboard.databinding.KeyboardLayoutBinding

class InputMethodService : InputMethodService() {
    override fun onCreateInputView(): View {
        val keyboardBinding = KeyboardLayoutBinding.inflate(layoutInflater)

        val buttonIds = arrayOf(
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn0,
            R.id.btnQ, R.id.btnW, R.id.btnE, R.id.btnR, R.id.btnT, R.id.btnY, R.id.btnU, R.id.btnI, R.id.btnO, R.id.btnP,
            R.id.btnA, R.id.btnS, R.id.btnD, R.id.btnF, R.id.btnG, R.id.btnH, R.id.btnJ, R.id.btnK, R.id.btnL,
            R.id.btnZ, R.id.btnX, R.id.btnC, R.id.btnV, R.id.btnB, R.id.btnN, R.id.btnM,
            R.id.btnComma, R.id.btnDot
        )
        for (buttonId in buttonIds) {
            val button = keyboardBinding.root.findViewById<Button>(buttonId)
            button.setOnClickListener {
                val inputConnection = currentInputConnection
                inputConnection?.commitText(button.text.toString(), 1)
            }
        }
        keyboardBinding.btnBackspace.setOnClickListener {
            val inputConnection = currentInputConnection
            inputConnection?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
            return@setOnClickListener
        }
        keyboardBinding.btnEnter.setOnClickListener {
            val inputConnection = currentInputConnection
            inputConnection?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER))
            return@setOnClickListener
        }
        keyboardBinding.btnSpace.setOnClickListener {
            val inputConnection = currentInputConnection
            inputConnection?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SPACE))
            return@setOnClickListener
        }

        return keyboardBinding.root
    }
}
