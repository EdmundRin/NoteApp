package com.example.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.note.databinding.FragmentEditNoteBinding

/**
 * A Fragment responsible for editing note details.
 *
 * This fragment allows users to edit the details of a note, such as its title and description.
 * It retrieves the note's information based on the provided `noteId` and uses a ViewModel to manage
 * the editing process. When the user completes editing, the changes can be saved, and the user
 * can navigate back to the list of notes.
 */
class EditNoteFragment : Fragment() {
    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        val view = binding.root
        val noteId = EditNoteFragmentArgs.fromBundle(requireArguments()).noteId

        val application = requireNotNull(this.activity).application
        val dao = NoteDatabase.getInstance(application).noteDao

        val viewModelFactory = EditNoteViewModelFactory(noteId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EditNoteViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController()
                    .navigate(R.id.action_editNoteFragment_to_noteFragment)
                viewModel.onNavigatedToList()
            }
        })
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}