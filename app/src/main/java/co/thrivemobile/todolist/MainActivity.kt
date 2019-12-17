package co.thrivemobile.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val toDoItems: MutableList<String> = mutableListOf()
    private val toDoListAdapter = ToDoListAdapter(toDoItems) { position ->
        onItemRemoved(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 1..100) {
            toDoItems.add("To Do Item $i")
        }

        findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = toDoListAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
        }
        findViewById<FloatingActionButton>(R.id.add_item).setOnClickListener { onAddItem() }
    }

    private fun onItemRemoved(position: Int) {
        toDoItems.removeAt(position)
        toDoListAdapter.notifyItemRemoved(position)
    }

    private fun onAddItem() {
        AlertDialog.Builder(this)
            .setTitle("Add a To Do item:")
            .setView(R.layout.add_item)
            .setPositiveButton("Add") { _, _ ->

            }.create()
            .show()
    }
}
