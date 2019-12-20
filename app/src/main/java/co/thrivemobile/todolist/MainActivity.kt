package co.thrivemobile.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity
    : AppCompatActivity(), ToDoListContract.View {

    // create the presenter by passing in the View
    private val presenter: ToDoListPresenter = ToDoListPresenter(this)
    // note that the adapter was changed
    private val toDoListAdapter = ToDoListAdapter { position ->
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

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun setList(items: List<String>) {
        toDoListAdapter.apply {
            this.items = items
            notifyDataSetChanged()
        }
    }

    override fun notifyItemRemoved(position: Int) {
        toDoListAdapter.notifyItemRemoved(position)
    }

    override fun notifyItemAdded(position: Int) {
        toDoListAdapter.notifyItemInserted(position)
    }

    private fun onItemRemoved(position: Int) {
        presenter.onItemRemoved(position)
    }

    private fun onAddItem() {
        val parentView = findViewById<ViewGroup>(android.R.id.content)
        val contentView = LayoutInflater.from(this).inflate(R.layout.add_item, parentView, false)
        AlertDialog.Builder(this)
            .setTitle("Add a To Do item:")
            .setView(contentView)
            .setPositiveButton("Add") { _, _ ->
                val userItem = contentView.findViewById<EditText>(R.id.user_input).text.toString()
                presenter.onItemAdded(userItem)
            }.create()
            .show()
    }
}
