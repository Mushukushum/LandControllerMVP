package com.example.thirdparties.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thirdparties.R
import com.example.thirdparties.viewmodel.SoilConditionViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var soilConditionViewModel: SoilConditionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        soilConditionViewModel = ViewModelProvider(this).get(SoilConditionViewModel::class.java)
        soilConditionViewModel.readAllData.observe(viewLifecycleOwner, {
            soilCondition -> adapter.setData(soilCondition)
        })

        val action = ListFragmentDirections.actionListFragmentToSoilConditionFragment3()
        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            soilConditionViewModel.removeAll()
            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_,_ -> }

        builder.setTitle("Delete everything from database?")
        builder.setMessage("Are you sure you want to delete everything from database?")

        builder.create().show()
    }
}