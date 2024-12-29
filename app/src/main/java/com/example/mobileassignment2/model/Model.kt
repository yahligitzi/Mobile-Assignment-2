package com.example.mobileassignment2.model

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        for(i in 0..20) {
            val student = Student(
                name = "Name $i",
                id = "h",
                phone = "0",
                address = "wa",
                isChecked =  true
            )

            students.add(student)
        }
    }
}