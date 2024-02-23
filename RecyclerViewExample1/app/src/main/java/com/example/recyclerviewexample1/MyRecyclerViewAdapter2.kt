package com.example.recyclerviewexample1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter2(private val fruitsList: List<Fruit>) : RecyclerView.Adapter<MyViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemsListView = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder2(itemsListView)
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        val fruit = fruitsList[position]
        //holder.myTextView2.text = fruit.name
        holder.bind(fruit)

    }
}

class MyViewHolder2(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(fruit: Fruit) {
        val myTextView2: TextView = view.findViewById(R.id.tvName)
        myTextView2.text = fruit.name
    }
}