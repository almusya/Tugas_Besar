package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lo_costumer.view.*
import kotlin.math.cos

class CostumerAdapter(mCtx : Context , val costumers : ArrayList<Costumer>): RecyclerView.Adapter<CostumerAdapter.ViewHolder>(){




    val mCtx = mCtx

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val  txtCustomerName = itemView.txtCustomerName
        val txtMaxCredit = itemView.txtMaxCredit
        val btnUpdate = itemView.btnUpdate
        val btnDelete = itemView.btnDelete
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_costumer , parent , false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return costumers.size
    }

    override fun onBindViewHolder(holder: CostumerAdapter.ViewHolder, position: Int) {
        val costumer : Costumer = costumers[position]
        holder.txtCustomerName.text = costumer.costumerName
        holder.txtMaxCredit.text = costumer.maxCredit.toString()

    }


}