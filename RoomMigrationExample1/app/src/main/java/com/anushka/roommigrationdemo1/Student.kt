package com.anushka.roommigrationdemo1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_info")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    val id : Int,

    @ColumnInfo(name = "student_name")
    var name : String,

    /** Adding the default value to the newly created column on database helps us to fill the
     * new column for the old data created on Database
     *
     * As exercise 2: we're going to delete this column*/
    /*@ColumnInfo(name = "student_email", defaultValue = "No email")
    var email: String,*/

    /** This is another way to declare a new column with no default value declare. just use the
     * safe call operator (?). We're saying this column is nullable.
     *
     * As exerise 1: we're going to rename this column from 'student_course' to 'subject_name'*/
    @ColumnInfo(name = "student_course")
    var course: String?
)
