package co.thrivemobile.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
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
        val parentView = findViewById<ViewGroup>(android.R.id.content)
        val contentView = LayoutInflater.from(this).inflate(R.layout.add_item, parentView, false)
        AlertDialog.Builder(this)
            .setTitle("Add a To Do item:")
            .setView(contentView)
            .setPositiveButton("Add") { _, _ ->
                val userItem = contentView.findViewById<EditText>(R.id.user_input).text.toString()
                itemAdded(userItem)
            }.create()
            .show()
    }

    private fun itemAdded(item: String) {
        toDoItems.add(item)
        val itemInsertedAt = toDoItems.size - 1
        toDoListAdapter.notifyItemInserted(itemInsertedAt)
    }
}
