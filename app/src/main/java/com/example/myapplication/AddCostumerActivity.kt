package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_costumer.*

class AddCostumerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_costumer)


        btnSave.setOnClickListener{
            if (editCostumerName.text.isEmpty()){
                Toast.makeText(this, "Masukkan Nama Anada ", Toast.LENGTH_SHORT).show()
            }else{
                val customer = Costumer()
                customer.costumerName = editCostumerName.text.toString()
                if(editMaxCredit.text.isEmpty())
                    customer.maxCredit= 0.0
                else
                    customer.maxCredit = editMaxCredit.text.toString().toDouble()
                MainActivity.dbHandler.AddCustomer(this , customer)
                editCostumerName.requestFocus()
            }

        }
        btnClose.setOnClickListener{
            ClearEdits()
            finish()

        }




    }
    fun ClearEdits(){
        editCostumerName.text.clear()
        editMaxCredit.text.clear()
    }
}
