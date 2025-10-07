package com.example.baitaptuan2 // đổi theo package của bạn

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    private val ELDERLY_MIN = 66
    private val ADULT_MIN = 6
    private val CHILD_MIN = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCheck.setOnClickListener {
            val name = etName.text.toString().trim()
            val age = etAge.text.toString().trim().toIntOrNull()

            if (name.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (age == null || age < 0 || age > 120) {
                Toast.makeText(this, "Tuổi không hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val group = classifyAge(age)
            tvResult.text = "Xin chào $name!\nBạn thuộc nhóm: $group (tuổi $age)"
        }
    }

    private fun classifyAge(age: Int): String = when {
        age >= ELDERLY_MIN -> "Người già"
        age in ADULT_MIN..65 -> "Người lớn"
        age in CHILD_MIN..5 -> "Trẻ em"
        else -> "Em bé" // ≤ 1 hoặc 2 tùy bạn chỉnh CHILD_MIN
    }
}
