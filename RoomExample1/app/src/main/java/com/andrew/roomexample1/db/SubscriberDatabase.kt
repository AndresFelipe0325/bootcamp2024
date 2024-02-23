package com.andrew.roomexample1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/** Using Database annotation we need to declare the entities (table classes) and the corresponding
 * version of the database (useful for migrations). This must be declare abstract **/
@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {

    /** Here we need to declare an abstract reference for the DAO interface of every entity class
     * we have.**/
    abstract val subscriberDAO: SubscriberDAO

    /** The best practice is create a singleton in order to have only one reference of the
     * database **/
    companion object {
        /** Volatile annotation makes the field immediately visible to other threads **/
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null
        fun getInstance(context: Context) : SubscriberDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subcriber_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}