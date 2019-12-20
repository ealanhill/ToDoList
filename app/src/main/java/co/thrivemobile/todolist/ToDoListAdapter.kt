package co.thrivemobile.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ToDoViewHolder>() {

    var items: List<String> = emptyList()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ToDoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ToDoViewHolder,
        position: Int
    ) {
        holder.apply {
            bind(items[position])
            itemView.setOnClickListener { onClick(adapterPosition) }
        }
    }

}
