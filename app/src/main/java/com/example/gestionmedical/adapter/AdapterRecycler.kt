package com.example.gestionmedical.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gestionmedical.R
import com.example.gestionmedical.databinding.CardviewBinding
import com.example.gestionmedical.db.DataUser

class AdapterRecycler(private val clickListener: (DataUser) -> Unit) : RecyclerView.Adapter<AdapterRecycler.MyViewHolder>(){

    private val datauserlist = ArrayList<DataUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardviewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.cardview, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        holder.bind(datauserlist[position], clickListener)
    }

    fun setList(daousers: List<DataUser>){
        datauserlist.clear()
        datauserlist. addAll(daousers)
    }

    class MyViewHolder(val binding: CardviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(dataUser: DataUser, clickListener: (DataUser) -> Unit){
            binding.text1.text = dataUser.text1
            binding.itemLayout.setOnClickListener{
                clickListener(dataUser)
            }
        }
    }

    override fun getItemCount(): Int {
        return datauserlist.size
    }
}