package com.example.thirdparties.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.thirdparties.R

import com.example.thirdparties.model.SoilCondition
import kotlinx.android.synthetic.main.recycler_view.view.*

class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var soilConditionList = emptyList<SoilCondition>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false))
    }

    override fun getItemCount(): Int {
        return soilConditionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = soilConditionList[position]
        holder.itemView.number.text = currentItem.id.toString()
        holder.itemView.area_size.text = currentItem.area.toString()
        holder.itemView.last_agriculture.text = currentItem.lastAgriculture
        holder.itemView.crope_capacity.text = currentItem.cropCapacity.toString()

        holder.itemView.recyclerViewLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(soilCondition: List<SoilCondition>){
        this.soilConditionList = soilCondition
        notifyDataSetChanged()
    }
}