package com.example.noteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.models.Note
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

class AddNote : AppCompatActivity() {
    private lateinit var binding:FragmentAddNoteBinding
    private lateinit var note: Note
    private lateinit var oldNote: Note
    var isUpdate: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            oldNote = intent.getSerializableExtra("currentNote") as Note
            binding.etTitle.setText(oldNote.title)
            binding.etNote.setText(oldNote.note)
            isUpdate = true
        }catch (e :Exception){
            e.printStackTrace()
        }
        binding.imgCheck.setOnClickListener{
            val title = binding.etTitle.text.toString()
            val note_desc = binding.etNote.text.toString()
            if (title.isNotEmpty() || note_desc.isNotEmpty()){
                val formatter = SimpleDateFormat("EE,d MM yyyy HH: mm a")

                if(isUpdate){
                    note = Note(oldNote.id,title,note_desc,formatter.format(Date()))
                }else{
                    note = Note(null,title,note_desc,formatter.format(Date()))
                }
                val intent = Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this@AddNote,"Please enter some data",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.imgBackArrow.setOnClickListener {
            onBackPressedDispatcher
        }

    }

}