package com.example.snapee

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.snapee.databinding.ActivityEditedPhotoBinding

class EditedPhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditedPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditedPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editedImage.setImageURI(intent.data)
    }
}