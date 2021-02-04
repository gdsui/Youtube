package com.arslan.youtube.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arslan.youtube.converter.ContentDetailsConverter
import com.arslan.youtube.converter.ImageInfoConverter
import com.arslan.youtube.converter.MediumConverter
import com.arslan.youtube.converter.SnippetConverter
import com.arslan.youtube.data.models.PlaylistItem

@Database(entities = [PlaylistItem::class], version = 1, exportSchema = false)
@TypeConverters(
    ContentDetailsConverter::class,
    ImageInfoConverter::class,
    MediumConverter::class,
    SnippetConverter::class
)abstract class YoutubeDatabase:RoomDatabase() {
    abstract fun wordDao(): YoutubeDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: YoutubeDatabase? = null

        fun getDatabase(context: Context): YoutubeDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YoutubeDatabase::class.java,
                    "word_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}