package com.example.wordhunter3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wordhunter3.R
import com.example.wordhunter3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Данные для модулей
        val modules = listOf("Модуль 1", "Модуль 2", "Модуль 3")

        // Установка адаптера для ListView
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, modules)
        binding.modulesListView.adapter = adapter

        // Обработка нажатий
        binding.modulesListView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val action = HomeFragmentDirections.actionHomeFragmentToModuleFragment()
                findNavController().navigate(R.id.moduleFragment)
            }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
