package com.obvioustest.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.obviouspracticaltest.models.GalleryModel

@Dao
interface GalleryDao {

    @Query("SELECT * FROM NasaPhotos ORDER BY date DESC")
    fun getGalleryList():List<GalleryModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gallery: List<GalleryModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gallery: GalleryModel)


}

