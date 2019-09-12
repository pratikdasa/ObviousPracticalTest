package com.obvioustest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.obviouspracticaltest.models.GalleryModel

@Database(entities = arrayOf(GalleryModel::class), version = 1)
abstract class GalleryRoomDatabase : RoomDatabase() {
    abstract fun galleryDao(): GalleryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GalleryRoomDatabase? = null

        fun getDatabase(context: Context): GalleryRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, GalleryRoomDatabase::class.java, "gallery_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }

}