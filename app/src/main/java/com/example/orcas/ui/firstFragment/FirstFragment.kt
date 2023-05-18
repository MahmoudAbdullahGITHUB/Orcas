package com.example.orcas.ui.firstFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.orcas.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {

//    private lateinit var mySpinner: Spinner
    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

//    private var _binding :FirstFragmentBinding? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(layoutInflater,container,false)
        val view = binding.root


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val items = arrayOf("Item 1", "Item 2", "Item 3")

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            items,
        );


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        binding.mySpinner.adapter = adapter


        binding.test13.text = "dsfdsv"

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}