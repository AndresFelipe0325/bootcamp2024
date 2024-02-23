package com.andrew.roomexample1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.andrew.roomexample1.databinding.ListItemBinding
import com.andrew.roomexample1.db.Subscriber

/** For more efficient RecyclerViewAdapter we removed the subscribersList variable as a
 * parameter constructor and keep as an reference variable*/
class MyRecyclerViewAdapter(private val clickListener:(Subscriber)->Unit) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>(){

    private val subscribersList = ArrayList<Subscriber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val subscriber = subscribersList[position]
        holder.bind(subscriber, clickListener)
    }

    fun setList(subscribers: List<Subscriber>){
        subscribersList.clear()
        subscribersList.addAll(subscribers)

    }

    inner class MyViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: Subscriber, clickListener:(Subscriber)->Unit){
            binding.apply {
                nameTextView.text = subscriber.name
                emailTextView.text = subscriber.email
                listItemLayout.setOnClickListener {
                    clickListener(subscriber)
                }
            }
        }
    }
}