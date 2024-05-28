package com.example.ctn_project

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.bottomsheet.BottomSheetDialog

class home_screen_activity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        var createBtn = findViewById<AppCompatImageView>(R.id.homeScreenAddMoreData)

        createBtn.setOnClickListener(){
            val Dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.botton_sheet,null)

            var addAccountBtn = view.findViewById<AppCompatButton>(R.id.BottomSheetAddNewAccount)

            addAccountBtn.setOnClickListener(){

                Dialog.dismiss()
            }
            Dialog.setCancelable(false)
            Dialog.setContentView(view)
            Dialog.show()
        }
    }
}