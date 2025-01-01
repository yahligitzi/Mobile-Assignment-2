package com.example.mobileassignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobileassignment2.model.Model
import com.example.mobileassignment2.model.Student

class NewStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backToolbarIcon: ImageView = findViewById(R.id.back_button);

        backToolbarIcon.setOnClickListener {
            finish()
        }

        val saveButton: Button = findViewById(R.id.new_student_activity_save_button);
        val cancelButton: Button = findViewById(R.id.new_student_activity_cancel_button);

        val nameEditText: EditText = findViewById(R.id.new_student_activity_name_edit_text);
        val idEditText: EditText = findViewById(R.id.new_student_activity_id_edit_text);
        val phoneEditText: EditText = findViewById(R.id.new_student_activity_phone_edit_text);
        val addressEditText: EditText = findViewById(R.id.new_student_activity_address_edit_text);
        val checkedCheckbox: CheckBox = findViewById(R.id.new_student_activity_checked_checkbox);

        val students = Model.shared.students

        cancelButton.setOnClickListener {
            finish()
        }


        saveButton.setOnClickListener {
            val student = Student(
                name = nameEditText.text.toString(),
                id = idEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                address = addressEditText.text.toString(),
                isChecked = checkedCheckbox.isChecked
            )

            students.add(student)
            finish()
        }
    }
}