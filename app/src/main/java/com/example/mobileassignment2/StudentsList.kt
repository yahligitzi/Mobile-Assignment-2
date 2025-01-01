package com.example.mobileassignment2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileassignment2.model.Model
import com.example.mobileassignment2.model.Student

interface CustomOnItemClickListener {
    fun onItemClick(position: Int)
}

class StudentsList: AppCompatActivity() {

    private var students: MutableList<Student>? = null
    private lateinit var adapter: StudentsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val addStudentButton: Button = findViewById(R.id.students_list_activity_add_student_button);


        addStudentButton.setOnClickListener {
            val intent = Intent(this, NewStudent::class.java)
            startActivity(intent)
        }

        val backToolbarIcon: ImageView = findViewById(R.id.back_button);

        backToolbarIcon.setOnClickListener {
            finish()
        }

        students = Model.shared.students

        val studentsRecyclerView: RecyclerView = findViewById(R.id.students_list_activity_recycler_view)
        studentsRecyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        studentsRecyclerView.layoutManager = layoutManager

        adapter = StudentsRecyclerAdapter(students)

        adapter.listener = object : CustomOnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@StudentsList, StudentDetails::class.java)
                intent.putExtra("position", position)
                startActivity(intent)
            }
        }

        studentsRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    class StudentViewHolder(itemView: View, listener: CustomOnItemClickListener?): RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView? = null
        var idTextView: TextView? = null
        var checkbox: CheckBox ?= null
        var student: Student? = null

        init {
            nameTextView = itemView.findViewById(R.id.student_row_name_text_view)
            idTextView = itemView.findViewById(R.id.student_row_id_text_view)
            checkbox = itemView.findViewById(R.id.student_row_checked_checkbox)

            checkbox?.apply {
                setOnClickListener { view ->
                    (tag as? Int)?.let { tag ->
                        student?.isChecked = (view as? CheckBox)?.isChecked ?: false
                    }
                }
            }

            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }

        fun bind(student: Student?, position: Int) {
            this.student = student

            nameTextView?.text = student?.name
            idTextView?.text = student?.id

            checkbox?.apply {
                isChecked = student?.isChecked ?: false
                tag = position
            }
        }
    }

    class StudentsRecyclerAdapter(private val students: List<Student>?): RecyclerView.Adapter<StudentViewHolder>() {

        var listener: CustomOnItemClickListener? = null

        override fun getItemCount(): Int = students?.size ?:0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val inflation = LayoutInflater.from(parent?.context)
            val view = inflation.inflate(
                R.layout.student_list_row,
                parent,
                false
            )

            return StudentViewHolder(view, listener)
        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            holder.bind(students?.get(position), position)
        }
    }
}