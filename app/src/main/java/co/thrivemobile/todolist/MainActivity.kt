package co.thrivemobile.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList.OnListChangedCallback
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val onToDoListChangedCallback = object : OnListChangedCallback<ObservableArrayList<String>>() {
        override fun onChanged(sender: ObservableArrayList<String>?) {
            // nothing to do
        }

        override fun onItemRangeRemoved(
            sender: ObservableArrayList<String>?,
            positionStart: Int,
            itemCount: Int
        ) {
            toDoListAdapter.notifyItemRangeRemoved(positionStart, itemCount)
        }

        override fun onItemRangeMoved(
            sender: ObservableArrayList<String>?,
            fromPosition: Int,
            toPosition: Int,
            itemCount: Int
        ) {
            // do nothing, we don't allow users to move items
        }

        override fun onItemRangeInserted(
            sender: ObservableArrayList<String>?,
            positionStart: Int,
            itemCount: Int
        ) {
            toDoListAdapter.notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeChanged(
            sender: ObservableArrayList<String>?,
            positionStart: Int,
            itemCount: Int
        ) {
            toDoListAdapter.notifyItemRangeChanged(positionStart, itemCount)
        }
    }
    private val toDoViewModel = ToDoViewModel()
    private val toDoListAdapter = ToDoListAdapter(toDoViewModel.items) { position ->
        toDoViewModel.onItemRemoved(position)
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
        toDoViewModel.items.addOnListChangedCallback(onToDoListChangedCallback)
    }

    private fun onAddItem() {
        val parentView = findViewById<ViewGroup>(android.R.id.content)
        val contentView = LayoutInflater.from(this).inflate(R.layout.add_item, parentView, false)
        AlertDialog.Builder(this)
            .setTitle("Add a To Do item:")
            .setView(contentView)
            .setPositiveButton("Add") { _, _ ->
                val userItem = contentView.findViewById<EditText>(R.id.user_input).text.toString()
                toDoViewModel.onItemAdded(userItem)
            }.create()
            .show()
    }
}
