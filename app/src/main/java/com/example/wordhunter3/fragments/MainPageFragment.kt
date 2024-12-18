package com.example.wordhunter3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.wordhunter3.R
import com.example.wordhunter3.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)

        // Устанавливаем обработчик для BottomNavigationView
        binding.bNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Favorite -> {
                    replaceFragment(FavoriteFragment())
                    true
                }
                R.id.Home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.Profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Устанавливаем стартовый фрагмент (HomeFragment)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        return binding.root
    }

    private fun replaceFragment(fragment: Fragment) {
        childFragmentManager.commit {
            replace(R.id.frame_layout, fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
