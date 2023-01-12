import java.lang.reflect.Array;
import java.util.ArrayList;

public class TodoList {
    private ArrayList<Todo> todos;

    /**
     * Constructor for the todo list.
     */
    public TodoList() {
        this.todos = new ArrayList<Todo>();
    }

    /**
     * Adds a new todo task.
     * @param task  task to be added
     */
    public void createTodo(String task) {
        this.todos.add(new Todo(task));
    }

    /**
     * Lists all todos.
     * @return  A list of todo tasks.
     */
    public ArrayList<Todo> indexTodo() {
        return this.todos;
    }

    /**
     * Shows one todo.
     * @param index     index of the todo item
     * @return  The todo item.
     */
    public Todo showTodo(int index) {
        return this.todos.get(index);
    }

    /**
     * Marks todo as completed.
     * @param index     index of todo
     */
    public void markTodo(int index) {
        this.todos.get(index).markCompleted();
    }

    /**
     * Marks todo as uncompleted.
     * @param index     index of todo
     */
    public void unmarkTodo(int index) {
        this.todos.get(index).unmarkCompleted();
    }
}
