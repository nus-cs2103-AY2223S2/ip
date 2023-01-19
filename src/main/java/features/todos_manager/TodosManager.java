package features.todos_manager;


import event_loop.*;

import java.util.ArrayList;

/**
 * A class for managing todos.
 */
public class TodosManager implements ExecutableRegisterable {
    /**
     * Creates a new TodosManager with the todos set to items.
     * @param items the todos that this TodosManager starts with.
     */
    public TodosManager(ArrayList<TodoItem> items) {
        this.todoItems = items;
    }

    /**
     * Creates a new TodosManager with the todos set to empty.
     */
    public TodosManager() {
        this(new ArrayList<>());
    }

    /// The list of TodoItems that this manager contains.
    private ArrayList<TodoItem> todoItems;

    /**
     * Adds a TodoItem to the todoItems.
     * @param todo the TodoItem.
     */
    void addTodo(TodoItem todo) {
        todoItems.add(todo);
    }

    /**
     * The executable for adding a TodoItem to this class.
     * @return ExecutionStatus.finishCurrentIteration, i.e. this own event
     * should take an entire iteration in the outer event loop.
     */
    Executable getAddTodoExecutable() {
        return new Executable() {
            @Override
            public ExitStatus execute(String[] tokens) {
                final String name = String.join(" ", tokens);
                final TodoItem todoItem = new TodoItem(name);
                addTodo(todoItem);
                System.out.println("added: " + todoItem);
                return ExitStatus.finishCurrentIteration;
            }
        };
    }

    /**
     * Gets the executable for listing TodoItems.
     * @return
     */
    IdentifiableExecutable getListTodosExecutable() {
       return new IdentifiableExecutable() {
           @Override
           public String getId() {
               return "list";
           }

           @Override
           public ExitStatus execute(String[] tokens) {
               for (int i = 0; i < todoItems.size(); i++) {
                   System.out.println((i+1) + ". " + todoItems.get(i));
               }
               return null;
           }
       };
    }

    @Override
    public void register(NestableExecutableObject nestable) {
        nestable.registerPostExecutable(getAddTodoExecutable());
        nestable.registerIdentifiableExecutable(getListTodosExecutable());
    }
}
