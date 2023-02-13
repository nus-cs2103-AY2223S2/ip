package app.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Represents the List of Tasks, and the primary class that the
 * app interacts with. A TaskList allows for modification of the Tasks contained
 * within, including deletion and insertion, getting a Task at a specified index,
 * and iterating through each contained Task.
 *
 * Modification of Tasks contained in the TaskList can only be done through the
 * TaskList.
 */
public class TaskList implements Iterable<Task> {
    private static final String INVALID_TASKTYPE_ERROR = "Task not recognised.";
    private static final String MISSING_TASK_FIELD_ERROR = "This task doesn't contain the fields you've chosen";
    private static final String LIST_EMPTY_ON_DELETE_ERROR = "Hello hello there is no task to delete!!";
    private static final String TASK_NOT_IN_LIST_ERROR = "This task doesn't exist in your list.";
    private final List<Task> tasks;

    /**
     * Initialises an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Getter for a task at a given listIndex, according to the index when
     * doing the List or Find Command
     * @param listIndex
     * @return
     * @throws NumberFormatException
     * @throws IndexOutOfBoundsException
     */
    public Task getTask(String listIndex)
            throws NumberFormatException, IndexOutOfBoundsException {
        int i = Integer.parseInt(listIndex) - 1;
        return this.tasks.get(i);
    }

    /**
     * Getter for task through list index (first item is at 0).
     * @param i
     * @return
     * @throws IndexOutOfBoundsException
     */
    public Task getTask(int i) throws IndexOutOfBoundsException {
        return this.tasks.get(i);
    }

    private int parseStringIndex(String listIndex) {
        return Integer.parseInt(listIndex) - 1;
    }

    /**
     * Returns a view-only List of the Tasks in the TaskList.
     * @return
     */
    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Makes a new Task. Requires a task type as specified by TaskTypes,
     * and the correct mappings of the task.
     * @param type the type of task.
     * @param args mapping of task fields to their values.
     * @return
     * @throws InvalidInputException
     * @throws InvalidDateTimeException
     */
    private Task makeNewTask(TaskTypes.Type type, Map<String, String> args)
            throws InvalidInputException, InvalidDateTimeException {
        Task newTask;
        String desc = args.get("description");
        switch (type) {
        case TODO:
            newTask = new ToDo(desc);
            break;
        case DEADLINE:
            String by = args.get("by");
            newTask = new Deadline(desc, by);
            break;
        case EVENT:
            String from = args.get("from");
            String to = args.get("to");
            newTask = new Event(desc, from, to);
            break;
        default:
            throw new InvalidInputException(INVALID_TASKTYPE_ERROR);
        }
        return newTask;
    }

    /**
     * Adds a task to the TaskList. Tasks are always added to the end of the TaskList.
     * @param type one of the available task types provided in TaskTypes.
     * @param args map of argument names to their values.
     * @return the same Task object that was added into the TaskList
     * @throws InvalidDateTimeException
     * @throws InvalidInputException
     */
    public Task addTask(TaskTypes.Type type, Map<String, String> args)
            throws InvalidInputException, InvalidDateTimeException {
        Task newTask = makeNewTask(type, args);
        this.tasks.add(this.tasks.size(), newTask); // always adds to the end
        return newTask;
    }


    /**
     * Adds a task following addTask. Additionally, marks a task done after adding.
     * @param type one of the available task types provided in TaskTypes.
     * @param args map of argument names to their values.
     * @return the same Task object that was added into the TaskList
     * @throws InvalidDateTimeException
     * @throws InvalidInputException
     */
    public Task addDoneTask(TaskTypes.Type type, Map<String, String> args)
            throws InvalidDateTimeException, InvalidInputException {
        Task newTask = addTask(type, args); // add to end of list
        newTask.markAsDone();
        assert newTask.equals(this.tasks.get(this.tasks.size() - 1));
        return newTask;
    }


    /**
     * Edits a Task in place, given a mapping of the new fields and their values.
     * The index of the task doesn't change.
     * The fields specified must exist in the Task.
     * <br>
     * Under the hood, a new Task instance is created, and the old one deleted.
     * Any field that is not specified to be edited will take on the original value.
     * @param listIndex
     * @param args mapping of the name of the edited field(s) and the value
     * @return the new edited Task
     * @throws InvalidInputException
     */
    public Task editTask(String listIndex, Map<String, String> args)
            throws InvalidInputException, InvalidDateTimeException {
        Task toBeEdited = getTask(listIndex);
        try {
            TaskTypes.Type type = toBeEdited.getType();
            Map<String, String> previousMappings = toBeEdited.getMapping();
            Map<String, String> editedMappings = new HashMap<>();
            /*
            Check if fields in args are fields in the Task
            If yes, add them into the new mappings
            */
            for (String key : args.keySet()) {
                if (!toBeEdited.containsField(key)) {
                    throw new InvalidInputException(MISSING_TASK_FIELD_ERROR);
                } else {
                    editedMappings.put(key, args.get(key));
                }
            }
            System.out.println(editedMappings);
            /*
            Add all previous mappings that have not been edited
            to the new editedMappings map.
             */
            for (String key : previousMappings.keySet()) {
                if (!editedMappings.containsKey(key)) {
                    editedMappings.put(key, previousMappings.get(key));
                }
            }

            Task afterEdited = makeNewTask(type, editedMappings);
            replaceTaskAt(listIndex, afterEdited);
            return afterEdited;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("This task doesn't exist in your list");
        }
    }

    private void replaceTaskAt(String index, Task t) {
        int i = parseStringIndex(index);
        this.tasks.set(i, t);
    }

    /**
     * Pops (deletes and returns) a task from the TaskList, given the index of the task.
     * Note that the index is starts from zero - Commands accept user input which starts from 1.
     * @param index of the Task - starts from zero.
     * @return the deleted Task.
     * @throws InvalidInputException
     */
    public Task deleteTask(int index) throws InvalidInputException {
        if (this.tasks.isEmpty()) {
            throw new InvalidInputException(LIST_EMPTY_ON_DELETE_ERROR);
        }

        try {
            Task deletedTask = this.tasks.get(index);
            this.tasks.remove(index);
            return deletedTask;

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(TASK_NOT_IN_LIST_ERROR);
        }
    }

    /**
     * Marks a task as done at the given index.
     * Note that the index is starts from zero - Commands accept user input which starts from 1.
     * @param index of the Task - starts from zero.
     * @return returns true if task is already marked done, false if not.
     * @throws IndexOutOfBoundsException
     */
    public boolean markAsDone(String index) throws IndexOutOfBoundsException {
        boolean isAlreadyMarked = false;
        Task task = this.getTask(index);
        if (task.isDone()) {
            isAlreadyMarked = true;
        }
        task.markAsDone();
        return isAlreadyMarked;
    }

    /**
     * Marks a task as UNdone at the given index.
     * Note that the index is starts from zero - Commands accept user input which starts from 1.
     * @param index of the Task - starts from zero.
     * @return returns true if task is already marked UNdone, false if not.
     * @throws IndexOutOfBoundsException
     */
    public boolean unmarkDone(String index) throws IndexOutOfBoundsException {
        boolean isAlreadyMarked = false;
        Task task = this.getTask(index);
        if (!task.isDone()) {
            isAlreadyMarked = true;
        }
        task.unmarkDone();
        return isAlreadyMarked;
    }

    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }

    public List<Task> getSortedTasksByType(TaskTypes.Type type) {
        List<Task> result = new ArrayList<>();
        for (Task t : this.getAllTasks()) {
            if (t.getType().equals(type)) {
                result.add(t);
            }
        }
        result.sort(null);
        return Collections.unmodifiableList(result);
    }
}
