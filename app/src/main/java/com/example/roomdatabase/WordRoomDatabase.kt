package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase () {
    abstract  fun wordDao(): WordDao

    private class WordDatabaseCallBack(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }
        suspend fun populateDatabase(wordDao: WordDao) {
// Delete all content here.
            wordDao.deleteAll()
// Add sample words.
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
// TODO: Add your own words!
        }
    }

    companion object{ //singleton prevent multiple instances of
                        // database openinnf at the same time
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(
            context: Context,
            viewModelScope: CoroutineScope
        ): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,WordRoomDatabase::class.java,"word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}