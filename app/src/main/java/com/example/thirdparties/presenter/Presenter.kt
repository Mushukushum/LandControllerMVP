package com.example.thirdparties.presenter

import android.app.Application
import com.example.thirdparties.database.DatabaseDao
import com.example.thirdparties.database.SoilDatabase
import com.example.thirdparties.model.SoilCondition
import com.example.thirdparties.MainContract
import com.example.thirdparties.repository.SoilConditionRepository
import kotlinx.coroutines.*

class Presenter(private val view: MainContract.View, application: Application): MainContract.Presenter {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    private val soilConditionDao:DatabaseDao = SoilDatabase.getInstance(application).soilDatabaseDao
    private val repository:SoilConditionRepository = SoilConditionRepository(soilConditionDao)


    override fun addInfo(soilCondition: SoilCondition){
        scope.launch(Dispatchers.IO) {
            repository.addInfo(soilCondition)
        }
    }

    override fun updateInfo(soilCondition: SoilCondition){
        scope.launch(Dispatchers.IO){
            repository.updateInfo(soilCondition)
        }
    }

    override fun deleteInfo(soilCondition: SoilCondition){
        scope.launch(Dispatchers.IO){
            repository.deleteInfo(soilCondition)
        }
    }

    override fun deleteAll(){
        scope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    override fun getAllInfo(){
        scope.launch(Dispatchers.IO){
            val info = soilConditionDao.getAllInfo()
            withContext(Dispatchers.Main){view.showInfo(info)}
        }
    }
}