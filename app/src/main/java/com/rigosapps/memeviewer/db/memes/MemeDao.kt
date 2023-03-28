package com.rigosapps.imageorganizer.db.imageItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

import com.rigosapps.memeviewer.model.MemeEntity

/*

The DAO for the ImageItem database. Provides methods to access , write, update and delete from the ImageItem db.
 */

@Dao
interface MemeDao {

    @Query("SELECT * FROM MemeEntity ORDER BY key DESC")
    fun loadAll(): List<MemeEntity>

    @Query("SELECT * FROM MemeEntity WHERE key = :memeId")
    fun loadMeme(memeId: Long): MemeEntity


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMeme(meme: MemeEntity): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateMeme(meme: MemeEntity)

    @Delete
    fun deleteMeme(meme: MemeEntity)


}