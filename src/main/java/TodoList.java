import java.lang.reflect.Array;
import java.util.ArrayList;

public class TodoList {
    private ArrayList<String> todos;

    /**
     * Constructor for the todo list.
     */
    public TodoList() {
        this.todos = new ArrayList<String>();
    }

    /**
     * Adds a new todo task.
     * @param todo  task to be added
     */
    public void createTodo(String todo) {
        this.todos.add(todo);
    }

    /**
     * Lists all todos
     * @return  A list of todo tasks.
     */
    public ArrayList<String> indexTodo() {
        return this.todos;
    }
}
