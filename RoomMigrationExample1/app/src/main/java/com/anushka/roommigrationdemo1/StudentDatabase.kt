package com.anushka.roommigrationdemo1

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

/** Adding autoMigration parameter helps to create the migration with the new column in Student table*/
@Database(entities = [Student::class],
    version = 5,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4, spec = StudentDatabase.MigrationFrom3to4::class),
        AutoMigration(from = 4, to = 5, spec = StudentDatabase.MigrationFrom4to5::class)
    ])
abstract class StudentDatabase : RoomDatabase() {

    abstract val subscriberDAO: StudentDAO

    /** Exercise 1*/
    @RenameColumn(tableName = "student_info", fromColumnName = "student_course", toColumnName = "subject_name")
    class MigrationFrom3to4 : AutoMigrationSpec

    /** Exercise 2*/
    @DeleteColumn(tableName = "student_info", columnName = "student_email")
    class MigrationFrom4to5 : AutoMigrationSpec

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null
        fun getInstance(context: Context): StudentDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

