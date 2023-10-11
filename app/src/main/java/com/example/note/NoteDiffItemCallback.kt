package com.example.note

import androidx.recyclerview.widget.DiffUtil

/**
 * A utility class for calculating the difference between two lists of notes in a RecyclerView.
 *
 * This class is used by RecyclerView adapters to determine which items in the old and new lists have
 * the same content and which items have changed, been added, or removed. It helps optimize
 * RecyclerView updates.
 */
class NoteDiffItemCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note)
            = (oldItem.noteId == newItem.noteId)
    override fun areContentsTheSame(oldItem: Note, newItem: Note) = (oldItem == newItem)
}