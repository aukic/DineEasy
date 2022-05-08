package ukic.ante.dineeasy.ui.authorization

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ukic.ante.dineeasy.databinding.FragmentSignInBinding

class SignInFragment:Fragment() {
    private lateinit var binding:FragmentSignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseAuth = Firebase.auth
        binding = FragmentSignInBinding.inflate(layoutInflater)
        binding.tvNewAcc.setOnClickListener { showSignUpFragment() }
        binding.btnSignIn.setOnClickListener { showMainMenu() }

        return binding.root
    }

    private fun showMainMenu(){

        var email: String = binding.etEmail.text.toString().trim()
        var password: String = binding.etPassword.text.toString().trim()
        if(TextUtils.isEmpty(email)){
            binding.etEmail.error = "Email is required."
            return
        }
        if(TextUtils.isEmpty(password)){
            binding.etEmail.error = "Password is required."
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task->
            if (task.isSuccessful){
                Log.d("da","da")
                val action = SignInFragmentDirections.actionSignInFragmentToMainActivity()
                findNavController().navigate(action)
            }else{
                Log.d("Ne","ne")
            }
        }

    }

    private fun showSignUpFragment(){
        val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
        findNavController().navigate(action)
    }
}