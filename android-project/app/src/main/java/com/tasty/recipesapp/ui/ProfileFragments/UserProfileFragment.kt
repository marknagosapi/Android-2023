package com.tasty.recipesapp.ui.ProfileFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.R
class UserProfileFragment : Fragment() {

    private lateinit var userProfileViewModel: UserProfileViewModel
    private lateinit var userEmailTextView: TextView
    private lateinit var userNameTextView: TextView
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        userProfileViewModel = ViewModelProvider(requireActivity())[UserProfileViewModel::class.java]
        userNameTextView = view.findViewById(R.id.nameEditText)
        userEmailTextView = view.findViewById(R.id.emailEditText)
        saveButton = view.findViewById(R.id.saveButton)

        // Observe changes in user profile data
        userProfileViewModel.userName.observe(viewLifecycleOwner) { name ->
            // Update UI with the user's name
            userNameTextView.setText(name)
        }

        userProfileViewModel.userEmail.observe(viewLifecycleOwner) { email ->
            // Update UI with the user's email
            userEmailTextView.setText(email)
        }

        saveButton.setOnClickListener {
            saveUserCredentials()
        }


        // TODO: Load and display profile picture (you can use an ImageView for this)
    }



    private fun saveUserCredentials() {
        val newName = userNameTextView.text.toString()
        val newEmail = userEmailTextView.text.toString()

        if (newName.isNotEmpty() && newEmail.isNotEmpty()) {
            // Update ViewModel and local storage
            userProfileViewModel.updateUserProfile(newName, newEmail)

            // TODO: Perfo  rm any additional actions, such as updating the UI or showing a success message
        } else {
            // TODO: Handle validation errors, e.g., show an error message
        }
    }
}
