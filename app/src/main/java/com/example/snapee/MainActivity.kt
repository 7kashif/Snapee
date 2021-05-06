package com.example.snapee

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants
import com.example.snapee.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            val intent= Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent,45)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==45){
            if (data != null) {
                val uri: Uri? =data.data
                val dsPhotoEditorIntent = Intent(this, DsPhotoEditorActivity::class.java)
                dsPhotoEditorIntent.data = uri
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Snapee")
                val toolsToHide = intArrayOf(
                    DsPhotoEditorActivity.TOOL_ORIENTATION,
                    DsPhotoEditorActivity.TOOL_CROP
                )
                dsPhotoEditorIntent.putExtra(
                    DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE,
                    toolsToHide
                )
                startActivityForResult(dsPhotoEditorIntent, 200)
            }
        }
        if(requestCode==200){
            val intent=Intent(this,EditedPhotoActivity::class.java)
            if (data != null) {
                intent.data = data.data
            }
            startActivity(intent)
        }
    }
}