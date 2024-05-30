package com.example.ctn_project

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText

class home_screen_activity : AppCompatActivity() {

    val emailFormat : String = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    var emailStatus : Boolean = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        var createBtn = findViewById<AppCompatImageView>(R.id.homeScreenAddMoreData)

        var dataList : ArrayList<dataSummaray> = ArrayList()
        var dataForAdapter = recycleAdapter(dataList)

        val recycleview = findViewById<RecyclerView>(R.id.homeScreenRecycleView)
        recycleview.layoutManager = LinearLayoutManager(this)
        recycleview.adapter = dataForAdapter

        createBtn.setOnClickListener(){
            val Dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.botton_sheet,null)

            var addAccountBtn = view.findViewById<AppCompatButton>(R.id.BottomSheetAddNewAccount)
            var accountType = view.findViewById<TextInputEditText>(R.id.homeScreenAccTypeBtn)
            var email = view.findViewById<TextInputEditText>(R.id.homeScreenAccUserNameBtn)
            var password = view.findViewById<TextInputEditText>(R.id.homeScreenAccPasswordBtn)

            addAccountBtn.setOnClickListener(){

                emailStatus = checkEmailValidation(email.text.toString())

                if (emailStatus == true){

                    val db = sqliteClass(this,null)

                    var accType = accountType.text.toString()
                    var userEmail = email.text.toString()
                    var accPassword = password.text.toString()

                    db.addData(accType, userEmail, accPassword)

                    Dialog.dismiss()

                    val cursor = db.getData()

//                    if (cursor!!.moveToFirst()){
//                        do {
//                            dataList.add(dataSummaray(cursor.getString(1),cursor.getString(2),cursor.getString(3)))
//                        }while (cursor.moveToNext())
//                    }
//                    cursor.close()


                    while (cursor!!.moveToNext()){
                        dataList.add(dataSummaray(cursor.getString(1),cursor.getString(2),cursor.getString(3)))
                    }


                }else{
                    Toast.makeText(this,"Enter Proper Email",Toast.LENGTH_SHORT).show()
                }
            }
            Dialog.setCancelable(false)
            Dialog.setContentView(view)
            Dialog.show()
        }
    }

    fun checkEmailValidation(checkEmail : String): Boolean {
        emailStatus = checkEmail.matches(emailFormat.toRegex())
        return emailStatus
    }


}