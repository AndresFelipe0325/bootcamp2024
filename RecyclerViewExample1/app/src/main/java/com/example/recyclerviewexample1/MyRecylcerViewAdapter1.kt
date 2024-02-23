package com.example.recyclerviewexample1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

/** The adapter class should extend from RecyclerView.Adapter<myViewHolder>()
 * myViewHolder class should be created on this same file, which helps us to define
 * how to display the items of the data (the arrangement of element on the items list layouy)
 *
 * NOTE: as a parameter of our custom RecyclerView adapter we pass the list created**/
class MyRecylcerViewAdapter1(private val fruitsList1: List<String>) : RecyclerView.Adapter<MyViewHolder>() {

    /** This is where we create the items list, we need to create a template for it
     * two things to do:
     * - Create the layout for the items list
     * - Return the custom viewHolder (MyViewHolder)**/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return fruitsList1.size
    }

    /** This is where we can display data on the item list**/
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit = fruitsList1[position]
        //without best practices
        //holder.mytextView.text = fruit

        //with best practices
        holder.bind(fruit)
    }

}

/** According to the best practices on the ViewHolder class we need to implement a function to
 * display the data **/

//Without best practices
/*class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    val mytextView = view.findViewById<TextView>(R.id.tvName)
}*/

//Applying best practices
class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(fruit: String){
        val myTextView: TextView = view.findViewById(R.id.tvName)
        myTextView.text = fruit

        /** Method #1: implementing clickListener
         * LIMITATION -> the fruit object of selected item
         * list is usable only inside the adapter**/

        view.setOnClickListener {
            Toast.makeText(
                view.context,
                "Selected fruit is $fruit",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

