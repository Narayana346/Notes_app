package com.example.noteapp.Database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.models.Note
import com.example.noteapp.utilites.DATABASE_NAME

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase(){

    abstract fun getNoteDao(): Dao
    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Application): NoteDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,DATABASE_NAME).build()
                INSTANCE = instance
                instance
            }
        }
    }
}