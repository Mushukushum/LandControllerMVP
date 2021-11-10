package com.example.thirdparties

import com.example.thirdparties.model.SoilCondition

interface MainContract {
    interface View{
        fun showInfo(info:List<SoilCondition>)
    }
    interface Presenter{
        fun addInfo(soilCondition: SoilCondition)
        fun updateInfo(soilCondition: SoilCondition)
        fun deleteInfo(soilCondition: SoilCondition)
        fun deleteAll()
        fun getAllInfo()
    }
    interface SoilConditionRepository{
        fun addInfo(soilCondition: SoilCondition)
        fun updateInfo(soilCondition: SoilCondition)
        fun deleteInfo(soilCondition: SoilCondition)
        fun deleteAll()
        fun getAllInfo(): List<SoilCondition>
    }
}