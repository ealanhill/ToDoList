package co.thrivemobile.todolist

interface ToDoListContract {

    interface View {
        // will notify the adapter of the list
        fun setList(items: List<String>)

        // tells the adapter an item was removed
        fun notifyItemRemoved(position: Int)

        // tells the adapter an item was added
        fun notifyItemAdded(position: Int)
    }

    interface Presenter {
        // notifies the presenter to load the model
        fun onResume()

        // removes an item from the model
        fun onItemRemoved(position: Int)

        // adds an item to the model
        fun onItemAdded(item: String)
    }
}
