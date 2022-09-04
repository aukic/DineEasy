package ukic.ante.dineeasy.ui.mainmenu

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import ukic.ante.dineeasy.databinding.FragmentProfileBinding
import ukic.ante.dineeasy.ui.authorization.AuthorizationActivity

class ProfileFragment:Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private var firebaseAuth: FirebaseAuth = Firebase.auth
    private var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var collectionReference: DocumentReference = firebaseFirestore.collection("Users").document(firebaseAuth.uid.toString())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        getData()
        binding.editEmail.setOnClickListener {
            changeEmail(it)
        }
        binding.editName.setOnClickListener {
            changeName(it)
        }
        binding.btnPhoneNumber.setOnClickListener {
            changePhoneNumber(it)
        }
        binding.btnResetPassword.setOnClickListener {
            changePassword(it)
        }
        return binding.root
    }

    private fun changePassword(it: View){
        var email = EditText(it.context)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Change password")
        builder.setMessage("Enter your email")
        builder.setView(email)
        builder.setPositiveButton("Send"){dialog,which ->
            firebaseAuth.sendPasswordResetEmail(email.text.toString().trim()).addOnCompleteListener {
                if (it.isSuccessful){
                    Log.i("LOG","LOG")
                    Toast.makeText(requireContext(), "Reset link sent to your email.", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().signOut()
                }
            }
        }
        builder.setNegativeButton("Cancel"){dialog,which ->

        }
        builder.show()
    }

    private fun changePhoneNumber(it: View) {
        var newPhoneNumber = EditText(it.context)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Phone number")
        builder.setMessage("Enter your phone number")
        builder.setView(newPhoneNumber)
        builder.setPositiveButton("Send"){dialog,which ->
            collectionReference.update("Phone number", newPhoneNumber.text.toString())
            Toast.makeText(requireContext(), "Phone number changed successfully", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel"){dialog,which ->

        }
        builder.show()
    }

    private fun changeName(it: View) {
        var newName = EditText(it.context)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Full name")
        builder.setMessage("Enter your full name")
        builder.setView(newName)
        builder.setPositiveButton("Send"){dialog,which ->
            collectionReference.update("Full name",newName.text.toString())
            Toast.makeText(requireContext(), "Full name changed successfully", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel"){dialog,which ->

        }
        builder.show()
    }

    private fun changeEmail(it: View) {
        var newEmail = EditText(it.context)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Email")
        builder.setMessage("Enter your new email")
        builder.setView(newEmail)
        builder.setPositiveButton("Send"){dialog,which ->
            collectionReference.update("Email",newEmail.text.toString())
            Toast.makeText(requireContext(), "Email changed successfully", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel"){dialog,which ->

        }
        builder.show()
    }

    private fun getData() {
        collectionReference.get().addOnCompleteListener {
            if(it.isSuccessful){
                binding.tvEmail.text = it.result.data?.getValue("Email").toString()
                binding.tvFullName.text = it.result.data?.getValue("Full name").toString()
                binding.tvPhoneNumber.text = it.result.data?.getValue("Phone number").toString()
            }
        }
    }
}