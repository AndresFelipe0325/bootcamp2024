package com.example.recyclerviewexample1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


/** RecyclerViewAdapter with function parameter as second argument**/
class MyReciclerViewAdapter3(
    private val fruitsList: List<Fruit>,
    private val clickListener: (Fruit)->Unit
    ) : RecyclerView.Adapter<MyViewHolder3>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder3 {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemListView = layoutInflater.inflate(R.layout.list_item,parent, false)
        return MyViewHolder3(itemListView)
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder3, position: Int) {
        val fruit = fruitsList[position]
        /** We need to pass the second parameter of recyclerViewAdapter**/
        holder.bind(fruit, clickListener)

    }
}

class MyViewHolder3(private val view: View) : RecyclerView.ViewHolder(view){
    /** Using the second parameter as a function parameter**/
    fun bind(fruit: Fruit, clickListener: (Fruit)->Unit) {
        val myTextView3: TextView = view.findViewById(R.id.tvName)
        myTextView3.text = fruit.name

        view.setOnClickListener {
            clickListener(fruit)
        }
    }
}