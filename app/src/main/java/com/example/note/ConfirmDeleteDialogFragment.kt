package com.example.note

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
/**
 * A dialog fragment for confirming the deletion of a note.
 *
 * This dialog fragment is used to confirm the deletion of a note. It displays a confirmation
 * message with "Yes" and "No" buttons, allowing the user to confirm or cancel the deletion.
 * The result of the confirmation is handled via the provided `clickListener`.
 */
class ConfirmDeleteDialogFragment(val noteId : Long,val clickListener: (noteId: Long) -> Unit) : DialogFragment() {
    val TAG = "ConfirmDeleteDialogFragment"
    interface myClickListener {
        fun yesPressed()
    }

    var listener: myClickListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.delete_confirmation))
            .setPositiveButton(getString(R.string.yes)) { _,_ -> clickListener(noteId)}
            .setNegativeButton(getString(R.string.no)) { _,_ -> }

            .create()

    companion object {
        const val TAG = "ConfirmDeleteDialogFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as myClickListener
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
        }
    }
}