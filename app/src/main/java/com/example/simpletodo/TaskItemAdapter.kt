package com.example.simpletodo

import android.util.Log
import android.util.Log.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
/*
*A bridge that relsls the recycler view thhow to displat the data we give it
 */
class TaskItemAdapter(val listOfItems : List<String>, val longClickListener: OnLongClickListener) :
        RecyclerView.Adapter<TaskItemAdapter.ViewHolder>(){
    interface OnLongClickListener {
        fun onItemLongClicker(position :Int )
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }
    //involves populating data into the irtem through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         // Get the data model based on position

        // Get the data model based on position
//        val contact: Contact = mContacts.get(position)
//
//        // Set item views based on your views and data model
//
//        // Set item views based on your views and data model
//        val textView: TextView = holder.nameTextView
//        textView.setText(contact.getName())
//        val button: Button = holder.messageButton
//        button.setText(if (contact.isOnline()) "Message" else "Offline")
//        button.setEnabled(contact.isOnline())
        //get data model based on positon
        val item = listOfItems.get(position)

        //set textview to whatever text for task is
        holder.textView.text = item
    }
    //Provide direct reference to each of the views within a data item
    //used to cache the views within the item layuout for fast access
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        //Store regerences to elements in our layout view
        val textView : TextView
        init{
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener{
                longClickListener.onItemLongClicker((adapterPosition))
                true
            }
        }
    }
}
