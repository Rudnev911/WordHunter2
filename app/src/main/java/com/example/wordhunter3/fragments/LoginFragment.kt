package com.example.wordhunter3.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wordhunter3.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sp = requireContext().getSharedPreferences("PC", Context.MODE_PRIVATE).edit()
        val userLogin: EditText = view.findViewById(R.id.user_login_auth)
        val userPass: EditText = view.findViewById(R.id.user_pass_auth)
        val buttonAuth: Button = view.findViewById(R.id.button_auth)
        val linkToReg: TextView = view.findViewById(R.id.link_to_reg)
        val controller = findNavController()


        linkToReg.setOnClickListener {
            controller.navigate(R.id.registerFragment)
        }


        buttonAuth.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val password = userPass.text.toString().trim()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val db = Firebase.firestore
            db.collection("users")
                .whereEqualTo("login", login)
                .whereEqualTo("password", password)
                .get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty) {

                        sp.putString("Login", login).apply()
                        Toast.makeText(requireContext(), "Добро пожаловать, $login!", Toast.LENGTH_LONG).show()


                        controller.navigate(R.id.mainPageFragment)
                    } else {

                        Toast.makeText(requireContext(), "Неверный логин или пароль", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Ошибка подключения. Попробуйте позже", Toast.LENGTH_LONG).show()
                }
        }
    }
}
