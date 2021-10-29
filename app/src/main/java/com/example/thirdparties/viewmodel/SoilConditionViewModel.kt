package com.example.thirdparties.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.thirdparties.repository.SoilConditionRepository
import com.example.thirdparties.database.SoilDatabase
import com.example.thirdparties.model.SoilCondition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SoilConditionViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<SoilCondition>>
    private val repository: SoilConditionRepository

    init{
        val soilConditionDao = SoilDatabase.getInstance(application).soilDatabaseDao
        repository = SoilConditionRepository(soilConditionDao)
        readAllData = repository.readAllData
    }

    fun addInfo(soilCondition: SoilCondition){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addInfo(soilCondition)
        }
    }

    fun updateInfo(soilCondition: SoilCondition){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateInfo(soilCondition)
        }
    }

    fun removeInfo(soilCondition: SoilCondition){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteInfo(soilCondition)
        }
    }

    fun removeAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}