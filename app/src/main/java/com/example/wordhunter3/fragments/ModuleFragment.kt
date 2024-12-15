package com.example.wordhunter3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.wordhunter3.R
import com.example.wordhunter3.databinding.FragmentModuleBinding

class ModuleFragment : Fragment() {

    private var _binding: FragmentModuleBinding? = null
    private val binding get() = _binding!!

    // Список слов (можно заменить реальными данными)
    private val words = listOf(
        Pair("Hello", "Привет"),
        Pair("World", "Мир"),
        Pair("Book", "Книга")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModuleBinding.inflate(inflater, container, false)

        // Добавление карточек в контейнер
        for (word in words) {
            val cardView = LayoutInflater.from(context).inflate(R.layout.card_item, binding.cardsContainer, false)
            val cardText: TextView = cardView.findViewById(R.id.cardText)

            cardText.text = word.first // Устанавливаем английское слово

            cardText.setOnClickListener {
                // Переключаем текст между английским и русским
                cardText.text = if (cardText.text == word.first) word.second else word.first
            }

            binding.cardsContainer.addView(cardView)
        }

        // Обработка кнопки назад
        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
