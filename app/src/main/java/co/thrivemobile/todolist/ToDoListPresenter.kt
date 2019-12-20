package co.thrivemobile.todolist

class ToDoListPresenter(
    private val toDoListView : ToDoListContract.View
) : ToDoListContract.Presenter {

    // the model
    private val items = mutableListOf<String>()

    override fun onResume() {
        toDoListView.setList(items)
    }

    override fun onItemRemoved(position: Int) {
        items.removeAt(position)
        toDoListView.notifyItemRemoved(position)
    }

    override fun onItemAdded(item: String) {
        items.add(item)
        toDoListView.notifyItemAdded(items.size - 1)
    }
}
