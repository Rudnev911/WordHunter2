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

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sp = requireContext().getSharedPreferences("PC", Context.MODE_PRIVATE).edit()
        val userLogin: EditText = view.findViewById(R.id.user_login)
        val userEmail: EditText = view.findViewById(R.id.user_email) // Если поле email не нужно, можно удалить
        val userPass: EditText = view.findViewById(R.id.user_pass)
        val buttonReg: Button = view.findViewById(R.id.button_reg)
        val linkToAuth: TextView = view.findViewById(R.id.link_to_auth)
        val controller = findNavController()


        linkToAuth.setOnClickListener {
            controller.navigate(R.id.loginFragment)
        }


        buttonReg.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val password = userPass.text.toString().trim()

            if (login.isEmpty()) {
                Toast.makeText(requireContext(), "Проверьте поле login", Toast.LENGTH_LONG).show()
            } else if (password.isEmpty() || password.length < 5) {
                Toast.makeText(requireContext(), "Пароль должен быть больше 4 символов", Toast.LENGTH_LONG).show()
            } else {
                val db = Firebase.firestore
                val user = hashMapOf(
                    "login" to login,
                    "password" to password
                )

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                        sp.putString("Login", login).apply()
                        Toast.makeText(requireContext(), "Регистрация успешна", Toast.LENGTH_LONG).show()


                        controller.navigate(R.id.loginFragment)
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Ошибка. Попробуйте позже", Toast.LENGTH_LONG).show()
                    }
            }
        }
    }
}
