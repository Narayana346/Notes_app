package com.example.noteapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Home
import com.example.noteapp.R
import com.example.noteapp.models.Note
import kotlin.random.Random

class NoteAdapter(private val context: Home, val listener: NotesItemClickListener): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val noteList:ArrayList<Note> = ArrayList()
    private val fullList:ArrayList<Note> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = noteList[position]
        holder.title.text = currentNote.title
        holder.note_tv.text = currentNote.note
        holder.date.text = currentNote.date
        holder.title.isSelected = true
        holder.date.isSelected = true
        holder.note_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColour(),null))

        holder.note_layout.setOnClickListener {
            listener.onItemClicked(noteList[holder.absoluteAdapterPosition])

        }
        holder.note_layout.setOnLongClickListener {
            listener.onLongItemClicked(noteList[holder.absoluteAdapterPosition], holder.note_layout)
            true
        }

    }
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val note_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note_tv = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }
    // click listener
    interface NotesItemClickListener{
        //on click listener
        fun onItemClicked(note:Note)
        // on long click listener
        fun onLongItemClicked(note:Note,cardView: CardView)
    }

    // create random background color of note
    fun randomColour():Int{
        val list = ArrayList<Int>()
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)
        list.add(R.color.NoteColor6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    // update all notes
    fun updateList(newList:List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        noteList.clear()
        noteList.addAll(fullList)
        notifyDataSetChanged()
    }

    //search feature
    fun filterList(search:String){
        noteList.clear()
        for (item in fullList){
            if(item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.note?.lowercase()?.contains(search.lowercase()) == true){

                noteList.add(item)
            }
        }
        notifyDataSetChanged()
    }
}