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

class EditStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backToolbarIcon: ImageView = findViewById(R.id.back_button);

        backToolbarIcon.setOnClickListener {
            finish()
        }

        val students = Model.shared.students

        val position = intent.getIntExtra("position", -1)

        val saveButton: Button = findViewById(R.id.edit_student_activity_save_button);
        val cancelButton: Button = findViewById(R.id.edit_student_activity_cancel_button);
        val deleteButton: Button = findViewById(R.id.edit_student_activity_delete_button);

        val nameEditText: EditText = findViewById(R.id.edit_student_activity_name_edit_text);
        val idEditText: EditText = findViewById(R.id.edit_student_activity_id_edit_text);
        val phoneEditText: EditText = findViewById(R.id.edit_student_activity_phone_edit_text);
        val addressEditText: EditText = findViewById(R.id.edit_student_activity_address_edit_text);
        val checkedCheckbox: CheckBox = findViewById(R.id.edit_student_activity_checked_checkbox);

        if(position != -1) {
            var student = students[position]

            nameEditText.setText(student.name)
            idEditText.setText(student.id)
            phoneEditText.setText(student.phone)
            addressEditText.setText(student.address)
            checkedCheckbox.isChecked = student.isChecked
        }


        cancelButton.setOnClickListener {
            finish()
        }

        deleteButton.setOnClickListener {
            if(position != -1) {
                students.removeAt(position)
            }

            val intent = Intent(this, StudentsList::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        saveButton.setOnClickListener {
            if(position != -1) {
                val student = Student(
                    name = nameEditText.text.toString(),
                    id = idEditText.text.toString(),
                    phone = phoneEditText.text.toString(),
                    address = addressEditText.text.toString(),
                    isChecked = checkedCheckbox.isChecked
                )

                students[position] = student
            }

            finish()

        }
    }
}