package com.andrew.roomexample1.db


/** This class helps to be an intermediary between the viewModel and the model (describe with
 * the Room library)**/
class SubscriberRepository(private val dao: SubscriberDAO) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber) : Long{
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber) : Int{
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber) : Int{
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAll()
    }
}