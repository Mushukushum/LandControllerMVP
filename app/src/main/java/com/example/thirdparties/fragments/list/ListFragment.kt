package com.example.thirdparties.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thirdparties.MainContract
import com.example.thirdparties.presenter.Presenter
import com.example.thirdparties.R
import com.example.thirdparties.model.SoilCondition
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment(), MainContract.View {

    private lateinit var presenter: Presenter
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        presenter = Presenter(this, requireActivity().application)

        val action = ListFragmentDirections.actionListFragmentToSoilConditionFragment3()
        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(action)
        }

        setHasOptionsMenu(true)
        presenter.getAllInfo()

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
            presenter.deleteAll()
            fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
            this.requireActivity().recreate()
            Toast.makeText(requireContext(), "Successfully removed everything", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_,_ -> }

        builder.setTitle("Delete everything from database?")
        builder.setMessage("Are you sure you want to delete everything from database?")

        builder.create().show()
    }

    override fun showInfo(info: List<SoilCondition>) {
        adapter.setData(info)
    }
}