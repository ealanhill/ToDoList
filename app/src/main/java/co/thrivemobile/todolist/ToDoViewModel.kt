package co.thrivemobile.todolist

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList

class ToDoViewModel {

    // the model
    val items: ObservableList<String> = ObservableArrayList<String>()

    fun onItemAdded(item: String) {
        items.add(item)
    }

    fun onItemRemoved(position: Int) {
        items.removeAt(position)
    }
}
