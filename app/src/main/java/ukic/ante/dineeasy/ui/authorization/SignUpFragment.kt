package ukic.ante.dineeasy.ui.authorization

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import ukic.ante.dineeasy.databinding.FramgentSignUpBinding

class SignUpFragment:Fragment() {
    private lateinit var binding: FramgentSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseAuth =  Firebase.auth
        binding = FramgentSignUpBinding.inflate(layoutInflater)
        binding.btnSignUpFragment.setOnClickListener { registerUser() }
        return binding.root

        }

    private fun registerUser(){
        var email: String = binding.etEmail.text.toString().trim()
        var password: String = binding.etPassword.text.toString().trim()

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            task->
            if(task.isSuccessful){
                val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
                findNavController().navigate(action)
            }else{
                Log.d("ne","ne")
            }
        }
    }
}