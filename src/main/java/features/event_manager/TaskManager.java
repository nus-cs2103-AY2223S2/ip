package features.event_manager;


import event_loop.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A class for managing tasks.
 */
public class TaskManager implements ExecutableRegisterable {
    /**
     * Creates a new TaskManager with the todos set to items.
     * @param items the todos that this TaskManager starts with.
     */
    public TaskManager(ArrayList<Task> items) {
        this.tasks = items;
    }

    /**
     * Creates a new TaskManager with the todos set to empty.
     */
    public TaskManager() {
        this(new ArrayList<>());
    }

    /**
     * The list of tasks that this manager contains.
     */
    private ArrayList<Task> tasks;

    /**
     * Adds a Task to the todoItems.
     * @param task the Task.
     */
    void addTodo(Task task) {
        tasks.add(task);
    }

    /**
     * The executable for adding a Task to this class.
     * @param taskSupplier the function for creating a task instance.
     * @param id the id of the task instance.
     * @return the executable for adding a Task to this class.
     */
    IdentifiableExecutable getAddTaskExecutable(Function<String[], Task> taskSupplier, String id) {
        return new IdentifiableExecutable() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public ExitStatus execute(String[] tokens) {
                String[] newTokens = new String[tokens.length - 1];
                for (int i = 0; i < tokens.length - 1; i++) {
                    newTokens[i] = tokens[i+1];
                }
                final Task task = taskSupplier.apply(newTokens);
                addTodo(task);
                System.out.println("added: " + task);
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
               for (int i = 0; i < tasks.size(); i++) {
                   System.out.println((i+1) + ". " + tasks.get(i));
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
                final Task item = tasks.get(index);
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
        nestable.registerIdentifiableExecutable(getAddTaskExecutable(
                ToDo::fromTokens,
                "todo"
        ));
        nestable.registerIdentifiableExecutable(getAddTaskExecutable(
                Deadline::fromTokens,
                "deadline"
        ));
        nestable.registerIdentifiableExecutable(getAddTaskExecutable(
                Event::fromTokens,
                "event"
        ));
        nestable.registerIdentifiableExecutable(getListTodosExecutable());
        nestable.registerIdentifiableExecutable(
                getMarkerExecutable(true, "mark")
        );
        nestable.registerIdentifiableExecutable(
                getMarkerExecutable(false, "unmark")
        );
    }
}