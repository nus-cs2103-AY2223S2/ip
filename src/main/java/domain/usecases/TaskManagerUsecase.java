package domain.usecases;

import core.exceptions.InvalidArgumentException;
import core.exceptions.WriteException;
import core.utils.fp.ThrowingFunction;
import domain.entities.DataSaver;
import domain.entities.core.*;
import domain.entities.taskmanager.*;

import java.util.ArrayList;

/**
 * A class for managing tasks.
 */
public class TaskManagerUsecase implements ExecutableRegisterable {
    /**
     * Creates a new TaskManagerUsecase with the todos set to items.
     *
     * @param items     the todos that this TaskManagerUsecase starts with.
     * @param writable  the writable that this manager writes to.
     * @param dataSaver the data saver to save the data.
     */
    public TaskManagerUsecase(Writable writable, ArrayList<Task> items,
                              DataSaver dataSaver, Writable errorWritable) {
        this.tasks = items;
        this.writable = writable;
        this.dataSaver = dataSaver;
        this.errorWritable = errorWritable;
    }

    /**
     * Creates a new TaskManagerUsecase with the todos set to empty.
     *
     * @param writable  the writable that this manager writes to.
     * @param dataSaver the data saver to save the data.
     */
    public TaskManagerUsecase(Writable writable, Writable errorWritable,
                              DataSaver dataSaver) {
        this(writable, new ArrayList<>(), dataSaver, errorWritable);
    }

    /**
     * The list of tasks that this manager contains.
     */
    private ArrayList<Task> tasks;

    /**
     * The writable that this manager writes to.
     */
    private Writable writable;

    /**
     * The writable for writing errors.
     */
    private Writable errorWritable;

    /**
     * The data saver
     */
    private final DataSaver dataSaver;

    /**
     * Adds a Task to the todoItems.
     *
     * @param task the Task.
     */
    private void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * The executable for adding a Task to this class.
     *
     * @param taskSupplier the function for creating a task instance.
     * @param id           the id of the task instance.
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
                    writable.writeln(exception.getMessage());
                    return ExitStatus.finishCurrentIteration;
                }
                addTask(task);
                writable.writeln("added: " + task);
                return ExitStatus.finishCurrentIteration;
            }
        };
    }

    /**
     * Gets the executable for listing TodoItems.
     *
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
                    writable.writeln((i + 1) + ". " + tasks.get(i));
                }
                return null;
            }
        };
    }

    /**
     * Gets a index number from the index string.
     *
     * @param indexStr the string from which the index number is get.
     * @return the index as an int.
     * @throws InvalidArgumentException the exception whose value should be
     *                                  displayed.
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
     *
     * @param id         the id for the marker executable.
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
                    writable.writeln(exception.getMessage());
                    return ExitStatus.finishCurrentIteration;
                }
                final Task item = tasks.get(index);
                item.setComplete(isComplete);
                writable.writeln("Nice, I've marked this item as done:");
                writable.writeln("\t" + item);
                return ExitStatus.finishCurrentIteration;
            }

            @Override
            public String getId() {
                return id;
            }
        };
    }

    /**
     * Gets the executable for deleting a TodoItem.
     *
     * @return the executable for deleting a TodoItem.
     */
    private IdentifiableExecutable getDeleteExecutable() {
        return new IdentifiableExecutable() {
            @Override
            public ExitStatus execute(String[] tokens) {
                final String indexStr = tokens[0];
                final int index;
                try {
                    index = getIndex(indexStr);
                } catch (InvalidArgumentException exception) {
                    writable.writeln(exception.getMessage());
                    return ExitStatus.finishCurrentIteration;
                }
                final Task res = tasks.remove(index);
                writable.writeln("removed: " + res);
                return ExitStatus.finishCurrentIteration;
            }

            @Override
            public String getId() {
                return "delete";
            }
        };
    }

    /**
     * Gets the disposable for disposing this class.
     *
     * @return the disposable for disposing this class.
     */
    private Disposable getDisposable() {
        return () -> {
            try {
                dataSaver.clear();
            } catch (WriteException e) {
                errorWritable.writeln("Error while clearing data" +
                        " saver: " + e.getMessage());
            }
            for (Task task : tasks) {
                try {
                    dataSaver.writeln(task.serialize());
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
            dataSaver.dispose();
        };
    }

    @Override
    public void register(NestableExecutableObject nestable) {
        registerReader(nestable);
        nestable.registerIdentifiableExecutable(getListTodosExecutable());
        nestable.registerIdentifiableExecutable(
                getMarkerExecutable(true, "mark")
        );
        nestable.registerIdentifiableExecutable(
                getMarkerExecutable(false, "unmark")
        );
        nestable.registerIdentifiableExecutable(getDeleteExecutable());
        nestable.registerDisposable(getDisposable());
    }

    /**
     * Registers the readers for this class to the given nestable.
     *
     * @param nestable the nestable for executing the readers.
     */
    public void registerReader(NestableExecutableObject nestable) {
        nestable.registerIdentifiableExecutable(getAddTaskExecutable(
                ToDo::new,
                "todo"
        ));
        nestable.registerIdentifiableExecutable(getAddTaskExecutable(
                Deadline::new,
                "deadline"
        ));
        nestable.registerIdentifiableExecutable(getAddTaskExecutable(
                Event::new,
                "event"
        ));
    }

    /**
     * Redirects the output of this class to the given writable.
     *
     * @param writable the writable for this class.
     */
    public void redirectOutput(Writable writable) {
        this.writable = writable;
    }
}