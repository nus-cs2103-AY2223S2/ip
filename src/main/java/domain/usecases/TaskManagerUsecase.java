package domain.usecases;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

import core.exceptions.InvalidArgumentException;
import core.exceptions.WriteException;
import core.utils.fp.ThrowingFunction;
import domain.entities.DataSaver;
import domain.entities.core.CommandRegisterable;
import domain.entities.core.Disposable;
import domain.entities.core.ExitStatus;
import domain.entities.core.IdentifiedCommandable;
import domain.entities.core.NestedCommandableObject;
import domain.entities.core.Writable;
import domain.entities.taskmanager.Deadline;
import domain.entities.taskmanager.Event;
import domain.entities.taskmanager.Task;
import domain.entities.taskmanager.ToDo;

/**
 * A {@link TaskManagerUsecase} is an usecase that manages a list of
 * {@link Task}s. It provides many anonymous classes that can be registered
 * to a {@link NestedCommandableObject}. These anonymous classes will be
 * used to execute the commands.
 * <p>
 * To register the anonymous classes, use the register method.
 */
public class TaskManagerUsecase implements CommandRegisterable {
    /**
     * The data saver
     */
    private final DataSaver dataSaver;
    /**
     * The list of tasks that this manager contains.
     */
    private final ArrayList<Task> tasks;
    /**
     * The writable for writing errors.
     */
    private Writable errorWritable;
    /**
     * The writable that this manager writes to.
     */
    private Writable writable;

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
     * Adds a Task to the todoItems.
     *
     * @param task the Task.
     */
    private void addTask(Task task) {
        tasks.add(task);
        Collections.sort(tasks);
    }

    /**
     * The executable for adding a Task to this class.
     *
     * @param taskSupplier the function for creating a task instance.
     * @param id           the id of the task instance.
     * @return the executable for adding a Task to this class.
     */
    public IdentifiedCommandable getAddTaskCommand(ThrowingFunction<String[],
            Task,
            InvalidArgumentException> taskSupplier, String id) {
        return new IdentifiedCommandable() {
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
                    errorWritable.writeln(exception.getMessage());
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
    public IdentifiedCommandable getListTodoCommand() {
        return new IdentifiedCommandable() {
            @Override
            public String getId() {
                return "list";
            }

            @Override
            public ExitStatus execute(String[] tokens) {
                Collections.sort(tasks);
                for (int i = 0; i < tasks.size(); i++) {
                    writable.writeln((i + 1) + ". " + tasks.get(i));
                }
                return ExitStatus.finishCurrentIteration;
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
            throw new InvalidArgumentException("☹ OOPS, please input a "
                    + "number!");
        }
        if (index >= tasks.size() || index < 0) {
            throw new InvalidArgumentException("☹ OOPS, please input a number"
                    + " that is within range!");
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
    public IdentifiedCommandable getMarkerExecutable(boolean isComplete,
                                                     String id) {
        return new IdentifiedCommandable() {
            @Override
            public ExitStatus execute(String[] tokens) {
                final String indexStr = tokens[0];
                final int index;
                try {
                    index = getIndex(indexStr);
                } catch (InvalidArgumentException exception) {
                    errorWritable.writeln(exception.getMessage());
                    return ExitStatus.finishCurrentIteration;
                }
                final Task item = tasks.get(index);
                item.setComplete(isComplete);
                writable.writeln("Nice, I've marked this item as "
                        + (isComplete ? "done" : "not done") + ":");
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
    public IdentifiedCommandable getDeleteExecutable() {
        return new IdentifiedCommandable() {
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
     * Gets the executable for listing all the tasks that contains a date.
     *
     * @return the executable for listing all the tasks that contains a date.
     */
    IdentifiedCommandable getListWhenCommand() {
        return new IdentifiedCommandable() {
            @Override
            public ExitStatus execute(String[] tokens) {
                final String dateStr = tokens[0];
                final LocalDate date;
                try {
                    date = LocalDate.parse(dateStr);
                } catch (DateTimeParseException exception) {
                    writable.writeln("☹ OOPS, please input a date "
                            + "in the format yyyy-mm-dd!");
                    return ExitStatus.finishCurrentIteration;
                }
                boolean hasDate = false;
                for (int i = 0; i < tasks.size(); i++) {
                    final Task task = tasks.get(i);
                    if (task.containsDate(date)) {
                        writable.writeln((i + 1) + ". " + task);
                        hasDate = true;
                    }
                }
                if (!hasDate) {
                    writable.writeln("No tasks found on " + dateStr);
                }
                return ExitStatus.finishCurrentIteration;
            }

            @Override
            public String getId() {
                return "listwhen";
            }
        };
    }

    IdentifiedCommandable getFindCommand() {
        return new IdentifiedCommandable() {
            @Override
            public ExitStatus execute(String[] tokens) {
                final String keyword = String.join(" ", tokens);
                boolean hasKeyword = false;
                for (int i = 0; i < tasks.size(); i++) {
                    final Task task = tasks.get(i);
                    if (task.nameContains(keyword)) {
                        writable.writeln((i + 1) + ". " + task);
                        hasKeyword = true;
                    }
                }
                if (!hasKeyword) {
                    writable.writeln("No tasks found with keyword " + keyword);
                }
                return ExitStatus.finishCurrentIteration;
            }

            @Override
            public String getId() {
                return "find";
            }
        };
    }

    /**
     * Gets the disposable for disposing this class.
     *
     * @return the disposable for disposing this class.
     */
    public Disposable getDisposable() {
        return () -> {
            try {
                dataSaver.clear();
            } catch (WriteException e) {
                errorWritable.writeln("Error while clearing data"
                        + " saver: " + e.getMessage());
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
    public void register(NestedCommandableObject nestable) {
        registerReader(nestable);
        nestable.registerIdentifiableExecutable(getListTodoCommand());
        nestable.registerIdentifiableExecutable(
                getMarkerExecutable(true, "mark")
        );
        nestable.registerIdentifiableExecutable(
                getMarkerExecutable(false, "unmark")
        );
        nestable.registerIdentifiableExecutable(getDeleteExecutable());
        nestable.registerDisposable(getDisposable());
        nestable.registerIdentifiableExecutable(getFindCommand());
        nestable.registerIdentifiableExecutable(getListWhenCommand());
    }

    /**
     * Registers the readers for this class to the given nestable.
     *
     * @param nestable the nestable for executing the readers.
     */
    public void registerReader(NestedCommandableObject nestable) {
        nestable.registerIdentifiableExecutable(getAddTaskCommand(
                ToDo::new,
                "todo"
        ));
        nestable.registerIdentifiableExecutable(getAddTaskCommand(
                Deadline::new,
                "deadline"
        ));
        nestable.registerIdentifiableExecutable(getAddTaskCommand(
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

    /**
     * Redirects the error output of this class to the given writable.
     *
     * @param writable the writable for this class.
     */
    public void redirectErrorOutput(Writable writable) {
        this.errorWritable = writable;
    }
}
