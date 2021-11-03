package com.example.thirdparties.repository

import androidx.lifecycle.LiveData
import com.example.thirdparties.database.DatabaseDao
import com.example.thirdparties.model.SoilCondition

class SoilConditionRepository(private val databaseDao: DatabaseDao)  {

    val readAllData: LiveData<List<SoilCondition>> = databaseDao.getAllInfo()

    fun addInfo(soilCondition: SoilCondition){
        databaseDao.insert(soilCondition)
    }

    fun updateInfo(soilCondition: SoilCondition){
        databaseDao.update(soilCondition)
    }

    fun deleteInfo(soilCondition: SoilCondition){
        databaseDao.delete(soilCondition)
    }

    fun deleteAll(){
        databaseDao.deleteAll()
    }
}