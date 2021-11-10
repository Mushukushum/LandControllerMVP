package com.example.thirdparties.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.thirdparties.MainContract
import com.example.thirdparties.presenter.Presenter
import com.example.thirdparties.R
import com.example.thirdparties.model.SoilCondition
import kotlinx.android.synthetic.main.fragment_soil_condition.*
import kotlinx.android.synthetic.main.fragment_soil_condition.view.*

class SoilConditionFragment: Fragment(), MainContract.View {

    private lateinit var presenter: Presenter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?):View? {

        val view =  inflater.inflate(R.layout.fragment_soil_condition, container, false)
        presenter = Presenter(this, requireActivity().application)

        view.add_button.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val area = area_size_field.text.toString()
        val lastAgriculture = last_agriculture_field.text.toString()
        val cropCapacity = crope_capacity_field.text.toString()

        if(inputCheck(area, lastAgriculture, cropCapacity)){
            val info = SoilCondition(0, area.toDouble(), lastAgriculture, cropCapacity.toInt())
            presenter.addInfo(info)
            Toast.makeText(requireContext(), "Successfully added to database", Toast.LENGTH_LONG).show()
            val action = SoilConditionFragmentDirections.actionSoilConditionFragment3ToListFragment()
            findNavController().navigate(action)
        }
        else{
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(area: String, lastAgriculture:String, cropCapacity:String):Boolean{
        return !(TextUtils.isEmpty(area)&&TextUtils.isEmpty(lastAgriculture)&&TextUtils.isEmpty(cropCapacity))
    }

    override fun showInfo(info: List<SoilCondition>) {
        presenter.getAllInfo()
    }
}