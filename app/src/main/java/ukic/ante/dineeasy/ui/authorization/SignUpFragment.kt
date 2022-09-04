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
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

import ukic.ante.dineeasy.databinding.FramgentSignUpBinding

class SignUpFragment:Fragment() {
    private lateinit var binding: FramgentSignUpBinding
    private var firebaseAuth: FirebaseAuth = Firebase.auth
    private var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var collectionReference: CollectionReference = firebaseFirestore.collection("Users")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                val hashMap: HashMap<String,String> = HashMap()
                hashMap["Email"] = email
                hashMap["Full name"] = binding.etFullName.text.toString().trim()
                hashMap["Phone number"] = ""
                var collectionReference2 = collectionReference.document(firebaseAuth.uid.toString())
                collectionReference2.set(hashMap)
                val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
                findNavController().navigate(action)
            }
        }
    }
}