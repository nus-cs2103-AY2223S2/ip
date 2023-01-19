package features.todos_manager;


import event_loop.*;
import utils.Pair;

import java.util.ArrayList;
import java.util.function.Function;

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
     * @return the executable for adding a TodoItem to this class.
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
     * @return the executable for listing all the TodoItems.
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

    /**
     * Gets the executable that will mark an item's isComplete as isComplete.
     * @param id the id for the marker executable.
     * @param isComplete whether if the marker executable will mark item as
     *                   complete or not.
     * @return the executable that will mark an item's isComplete.
     */
    IdentifiableExecutable getMarkerExecutable(boolean isComplete, String id) {
        return new IdentifiableExecutable() {
            @Override
            public ExitStatus execute(String[] tokens) {
                final String indexStr = tokens[1];
                final int index = Integer.parseInt(indexStr) - 1;
                final TodoItem item = todoItems.get(index);
                item.setComplete(isComplete);
                System.out.println("Nice, I've marked this item as done:");
                System.out.println("\t" + item);
                return ExitStatus.finishCurrentIteration;
            }

            @Override
            public String getId() {
                return id;
            }
        };
    }

    @Override
    public void register(NestableExecutableObject nestable) {
        nestable.registerPostExecutable(getAddTodoExecutable());
        nestable.registerIdentifiableExecutable(getListTodosExecutable());
        nestable.registerIdentifiableExecutable(
                getMarkerExecutable(true, "mark")
        );
        nestable.registerIdentifiableExecutable(
                getMarkerExecutable(false, "unmark")
        );
    }
}