package features.taskmanager;
import exceptions.InvalidArgumentException;
import eventloop.*;
import utils.fp.ThrowingFunction;

import java.util.ArrayList;

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
    void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * The executable for adding a Task to this class.
     * @param taskSupplier the function for creating a task instance.
     * @param id the id of the task instance.
     * @return the executable for adding a Task to this class.
     */
    private IdentifiableExecutable getAddTaskExecutable(ThrowingFunction<String[], Task,
            InvalidArgumentException> taskSupplier, String id) {
        return new IdentifiableExecutable() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public ExitStatus execute(String[] tokens) {
                final Task task;
                try {
                    task = taskSupplier.apply(tokens);
                } catch (InvalidArgumentException exception) {
                    System.out.println(exception.getMessage());
                    return ExitStatus.finishCurrentIteration;
                }
                addTask(task);
                System.out.println("added: " + task);
                return ExitStatus.finishCurrentIteration;
            }
        };
    }

    /**
     * Gets the executable for listing TodoItems.
     * @return the executable for listing all the TodoItems.
     */
    private IdentifiableExecutable getListTodosExecutable() {
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
     * Gets a index number from the index string.
     * @param indexStr the string from which the index number is get.
     * @return the index as an int.
     * @throws InvalidArgumentException the exception whose value should be
     * displayed.
     */
    private int getIndex(String indexStr) throws InvalidArgumentException {
        final int index;
        try {
            index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException("☹ OOPS, please input a " +
                    "number!");
        }
        if (index >= tasks.size() || index < 0) {
            throw new InvalidArgumentException("☹ OOPS, please input a number" +
                    " that is within range!");
        }
        return index;
    }

    /**
     * Gets the executable that will mark an item's isComplete as isComplete.
     * @param id the id for the marker executable.
     * @param isComplete whether if the marker executable will mark item as
     *                   complete or not.
     * @return the executable that will mark an item's isComplete.
     */
    private IdentifiableExecutable getMarkerExecutable(boolean isComplete,
                                                 String id) {
        return new IdentifiableExecutable() {
            @Override
            public ExitStatus execute(String[] tokens) {
                final String indexStr = tokens[0];
                final int index;
                try {
                    index = getIndex(indexStr);
                } catch (InvalidArgumentException exception) {
                    System.out.println(exception.getMessage());
                    return ExitStatus.finishCurrentIteration;
                }
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

    private IdentifiableExecutable getDeleteExecutable() {
        return new IdentifiableExecutable() {
            @Override
            public ExitStatus execute(String[] tokens) {
                final String indexStr = tokens[0];
                final int index;
                try {
                    index = getIndex(indexStr);
                } catch (InvalidArgumentException exception) {
                    System.out.println(exception.getMessage());
                    return ExitStatus.finishCurrentIteration;
                }
                final Task res = tasks.remove(index);
                System.out.println("removed: " + res);
                return ExitStatus.finishCurrentIteration;
            }

            @Override
            public String getId() {
                return "delete";
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
        nestable.registerIdentifiableExecutable(getDeleteExecutable());
    }
}