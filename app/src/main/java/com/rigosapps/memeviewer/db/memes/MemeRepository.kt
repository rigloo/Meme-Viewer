package com.rigosapps.imageorganizer.db.imageItem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rigosapps.memeviewer.helpers.MemeToMemeEntity
import com.rigosapps.memeviewer.model.MemeEntity
import com.rigosapps.memeviewer.retroFit.RetrofitInstance
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class MemeRepository(private val memeDao: MemeDao) {

    val readAllData: List<MemeEntity> = memeDao.loadAll()

    suspend fun addMeme(meme: MemeEntity): Long {
        return memeDao.insertMeme(meme)
    }


    suspend fun updateMeme(meme: MemeEntity) {
        memeDao.updateMeme(meme)
    }

    suspend fun deleteImageItem(meme: MemeEntity) {
        memeDao.deleteMeme(meme)
    }

    suspend fun getImageItem(id: Long): MemeEntity {
        return memeDao.loadMeme(id)
    }


}