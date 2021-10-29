package com.example.thirdparties.repository

import androidx.lifecycle.LiveData
import com.example.thirdparties.database.DatabaseDao
import com.example.thirdparties.model.SoilCondition

class SoilConditionRepository(private val databaseDao: DatabaseDao)  {

    val readAllData: LiveData<List<SoilCondition>> = databaseDao.getAllInfo()

    suspend fun addInfo(soilCondition: SoilCondition){
        databaseDao.insert(soilCondition)
    }

    suspend fun updateInfo(soilCondition: SoilCondition){
        databaseDao.update(soilCondition)
    }

    suspend fun deleteInfo(soilCondition: SoilCondition){
        databaseDao.delete(soilCondition)
    }

    suspend fun deleteAll(){
        databaseDao.deleteAll()
    }
}