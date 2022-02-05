package com.example.simpletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    var listOfTasks = mutableListOf<String>()
    lateinit var adapter : TaskItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicker(position: Int) {
                //1. Remove the ittem from list
                listOfTasks.removeAt(position)
                //2. notify the adapter that out data has changed
                adapter.notifyDataSetChanged()
                saveItems()
            }

        }

        loadItems()
        //loko up recycler view in the layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        //create adapter passing in the sample user data
         adapter = TaskItemAdapter(listOfTasks, onLongClickListener)

        //attach the ddpater to the recyclerview to populat items
        recyclerView.adapter = adapter

        //set layout amnager to position the items

        recyclerView.layoutManager = LinearLayoutManager(this)

        //set up the button and input field so that the use can enter a task and add it to the list
        val inputTextField = findViewById<EditText>(R.id.addTaskField)
        //get reference to the button
        //and then set an onclick listerner
        findViewById<Button>(R.id.addTaskButton).setOnClickListener {
            //1.grab test user hs inputted
            val userInputtedTask = inputTextField.text.toString()
            //2. add string to list of tasks
            listOfTasks.add(userInputtedTask)

            //notify the adapter that our dta has been updated
            adapter.notifyItemInserted(listOfTasks.size -1)
            //3. Reset text field
            inputTextField.setText("")
            saveItems()
        }
    }

    //sabe teh data the use has inputted
    fun getDataFile() : File {
        //every line will represetn specific task
        return File(filesDir, "data.txt")
    }
    //load the items by reading every line in the file
    fun loadItems(){
        try{
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        }catch (ioException : IOException){
            ioException.printStackTrace()
        }

    }

    //save itenms
    fun saveItems(){
        try{
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException : IOException){
            ioException.printStackTrace()
        }


    }

}