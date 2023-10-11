package com.example.note

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.note.databinding.FragmentNoteBinding


/**
 * A Fragment for displaying a list of notes.
 *
 * This fragment presents a list of notes, allowing the user to view and manage their notes.
 * It utilizes a ViewModel to manage note data and handles user interactions, such as clicking on a note
 * for details or deleting a note. Additionally, it offers navigation to the EditNoteFragment for editing
 * and adding new notes.
 */
class NoteFragment : Fragment() {
    val TAG = "NoteFragment"
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = NoteDatabase.getInstance(application).noteDao
        val viewModelFactory = NoteViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(NoteViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        /* val adapter = TaskItemAdapter{ taskId ->
             viewModel.onTaskClicked(taskId)

         }*/

        /**
         * Handle actions related to notes and their deletion.
         *
         * This class provides methods to perform various actions related to notes, including
         * clicking on a note, confirming the deletion of a note, and deleting a note when confirmed.
         */
        fun noteClicked (noteId : Long) {
            viewModel.onNoteClicked(noteId)
        }

        /**
         * Handle the user's confirmation to delete a note.
         *
         * This method logs the note's ID and should implement the actual deletion logic.
         *
         * @param noteId The ID of the note to be deleted.
         */
        fun yesPressed(noteId : Long) {
            Log.d(TAG, "in yesPressed(): noteId = $noteId")
            //TODO: delete the note with id = noteId
        }

        /**
         * Handle the user's request to delete a note.
         *
         * This method displays a confirmation dialog for deleting a note and handles the confirmation.
         *
         * @param noteId The ID of the note to be deleted.
         */
        fun deleteClicked (noteId : Long) {
            ConfirmDeleteDialogFragment(noteId,::yesPressed).show(childFragmentManager,
                ConfirmDeleteDialogFragment.TAG)
        }
        val adapter = NoteItemAdapter(::noteClicked,::deleteClicked)


        binding.noteList.adapter = adapter

        viewModel.note.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.navigateToNote.observe(viewLifecycleOwner, Observer { noteId ->
            noteId?.let {
                val action = NoteFragmentDirections
                    .actionNoteFragmentToEditNoteFragment(noteId)
                this.findNavController().navigate(action)
                viewModel.onNoteNavigated()
            }
        })


        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}