package com.example.recyclerviewexample1

/** By default on Kotlin data classes are used to hold data or state
 * we don't need to declare getter or setter (they're automatically delared)**/
data class Fruit(
    val name: String,
    val supplier: String
)