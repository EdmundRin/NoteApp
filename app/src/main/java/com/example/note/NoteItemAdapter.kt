package com.example.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.NoteItemBinding
/**
 * A RecyclerView adapter for displaying a list of notes in a user interface.
 *
 * This adapter is responsible for binding the data of notes to individual items in a RecyclerView.
 * It supports both regular item clicks and delete button clicks for each note.
 *
 * @param clickListener A function to handle regular item clicks.
 * @param deleteClickListener A function to handle delete button clicks for notes.
 */
class NoteItemAdapter(val clickListener: (noteId: Long) -> Unit,
                      val deleteClickListener: (noteId: Long) -> Unit)
    : ListAdapter<Note, NoteItemAdapter.NoteItemViewHolder>(NoteDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : NoteItemViewHolder = NoteItemViewHolder.inflateFrom(parent)
    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener, deleteClickListener)
    }

    /**
     * View holder for an individual note item in the RecyclerView.
     *
     * This view holder binds the data of a note to its corresponding layout elements and
     * handles click events for regular item clicks and delete button clicks.
     */
    class NoteItemViewHolder(val binding: NoteItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            /**
             * Inflates the view holder from a parent view group.
             *
             * @param parent The parent view group.
             * @return An instance of NoteItemViewHolder.
             */
            fun inflateFrom(parent: ViewGroup): NoteItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteItemBinding.inflate(layoutInflater, parent, false)
                return NoteItemViewHolder(binding)
            }
        }

        /**
         * Binds a note's data to the layout elements and sets up click event handlers.
         *
         * @param item The note item to be displayed.
         * @param clickListener A function to handle regular item clicks.
         * @param deleteClickListener A function to handle delete button clicks.
         */
        fun bind(item: Note, clickListener: (noteId: Long) -> Unit,
                 deleteClickListener: (noteId: Long) -> Unit) {
            binding.note = item
            binding.root.setOnClickListener { clickListener(item.noteId) }
            binding.deleteButton.setOnClickListener { deleteClickListener(item.noteId) }
        }
    }
}