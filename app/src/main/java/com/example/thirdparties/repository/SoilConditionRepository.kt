package com.example.thirdparties.repository

import com.example.thirdparties.MainContract
import com.example.thirdparties.database.DatabaseDao
import com.example.thirdparties.model.SoilCondition

class SoilConditionRepository(private val databaseDao: DatabaseDao): MainContract.SoilConditionRepository  {

    override fun addInfo(soilCondition: SoilCondition){
        databaseDao.insert(soilCondition)
    }

    override fun updateInfo(soilCondition: SoilCondition){
        databaseDao.update(soilCondition)
    }

    override fun deleteInfo(soilCondition: SoilCondition){
        databaseDao.delete(soilCondition)
    }

    override fun deleteAll(){
        databaseDao.deleteAll()
    }

    override fun getAllInfo(): List<SoilCondition> {
        return databaseDao.getAllInfo()
    }
}