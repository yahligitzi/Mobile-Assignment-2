package com.example.mobileassignment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobileassignment2.model.Model

class StudentDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backToolbarIcon: ImageView = findViewById(R.id.back_button);

        backToolbarIcon.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        val position = intent.getIntExtra("position", -1)

        val editStudentButton: Button = findViewById(R.id.student_details_activity_edit_button);
        editStudentButton.setOnClickListener {
            val intent = Intent(this, EditStudent::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }

        if(position != -1) {
            val student = Model.shared.students[position]
            Log.d("TAG", "print student now: ${student.toString()}")


            val nameTextView: TextView = findViewById(R.id.student_details_activity_name_text_view)
            val idTextView: TextView = findViewById(R.id.student_details_activity_id_text_view)
            val phoneTextView: TextView = findViewById(R.id.student_details_activity_phone_text_view)
            val addressTextView: TextView = findViewById(R.id.student_details_activity_address_text_view)
            val checkedCheckbox: CheckBox = findViewById(R.id.student_details_activity_checked_checkbox)

            nameTextView.text = student.name
            idTextView.text = student.id
            phoneTextView.text = student.phone
            addressTextView.text = student.address
            checkedCheckbox.isChecked = student.isChecked
        }
    }
}