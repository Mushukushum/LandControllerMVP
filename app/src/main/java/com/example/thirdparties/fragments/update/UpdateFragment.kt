package com.example.thirdparties.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thirdparties.R
import com.example.thirdparties.model.SoilCondition
import com.example.thirdparties.viewmodel.SoilConditionViewModel
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var soilConditionViewModel: SoilConditionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        soilConditionViewModel = ViewModelProvider(this).get(SoilConditionViewModel::class.java)

        view.update_area_size_field.setText(args.currentSoilCondition.area.toString())
        view.update_last_agriculture_field.setText(args.currentSoilCondition.lastAgriculture)
        view.update_crope_capacity_field.setText(args.currentSoilCondition.cropCapacity.toString())

        view.update_button.setOnClickListener{
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val area = update_area_size_field.text.toString().toDouble()
        val lastAgriculture = update_last_agriculture_field.text
        val cropCapacity = Integer.parseInt(update_crope_capacity_field.text.toString())

        if(inputCheck(area.toString(), lastAgriculture.toString(), cropCapacity.toString())){
            val updatedInfo = SoilCondition(args.currentSoilCondition.id, area, lastAgriculture.toString(), cropCapacity)

            soilConditionViewModel.updateInfo(updatedInfo)

            Toast.makeText(requireContext(), "Updated successfully!", Toast.LENGTH_LONG).show()

            val action = UpdateFragmentDirections.actionUpdateFragmentToListFragment()

            findNavController().navigate(action)
        }
        else{
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(area: String, lastAgriculture:String, cropCapacity:String):Boolean{
        return !(TextUtils.isEmpty(area)&& TextUtils.isEmpty(lastAgriculture)&& TextUtils.isEmpty(cropCapacity))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
             deleteInfo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteInfo() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            soilConditionViewModel.removeInfo(args.currentSoilCondition)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentSoilCondition.lastAgriculture}", Toast.LENGTH_LONG).show()
            val action = UpdateFragmentDirections.actionUpdateFragmentToListFragment()
            findNavController().navigate(action)
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete ${args.currentSoilCondition.lastAgriculture}?")
        builder.setMessage("Are you sure you want to delete ${args.currentSoilCondition.lastAgriculture}")
        builder.create().show()
    }
}