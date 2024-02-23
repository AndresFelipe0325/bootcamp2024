package com.example.recyclerviewexample1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener

class MainActivity : AppCompatActivity() {
    /** data for example 1 **/
    private val fruitsList1 = listOf("Mango", "Apple", "Banana", "Guava",
        "Lemon", "Pear", "Orange")
    /**  data from a data class example 2 **/
    private val fruitsList2 = listOf(
        Fruit("Mango", "Joe"),
        Fruit("Apple", "Frank"),
        Fruit("Banana", "Tom"),
        Fruit("Guava", "Joe"),
        Fruit("Lemon", "Alex"),
        Fruit("Pear", "Joe"),
        Fruit("Orange", "Alex"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.myRecycerView)
        recyclerView.setBackgroundColor(Color.WHITE)
        //Set layoutManager for recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        //Set the adapter of the recyclerView with the custom recyclerView we created

        /** Simpler adapter**/
        //recyclerView.adapter = MyRecylcerViewAdapter1(fruitsList1)

        /** Adapter with data class objects**/
        //recyclerView.adapter = MyRecyclerViewAdapter2(fruitsList2)

        /** Setting an adapter with data class object and OnClickListener implementation
         * connecting the function itemListClicked() with the adapter using lambda expression**/
        recyclerView.adapter = MyReciclerViewAdapter3(fruitsList2) { selectedItem: Fruit ->
            itemListClicked(selectedItem)
        }

        recyclerView.addOnScrollListener(object : OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    /** Method 2: Implementing a clickListener for RecyclerView**/
    private fun itemListClicked(fruit: Fruit){
        Toast.makeText(
            this@MainActivity,
            "Selected fruit is ${fruit.name} , supplier is ${fruit.supplier}",
            Toast.LENGTH_LONG
        ).show()
    }
}