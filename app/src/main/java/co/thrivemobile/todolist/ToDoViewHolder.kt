package co.thrivemobile.todolist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(title: String) {
        itemView.findViewById<TextView>(R.id.title).text = title
    }
}
