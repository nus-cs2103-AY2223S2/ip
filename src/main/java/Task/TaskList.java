package duke;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.DukeException;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Represents a List of Tasks.
 */
public class TaskList {

    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu kk:mm");
    private ArrayList<Task> tasks;

    /**
     * Initialises new instance of TaskList. TaskList contains no Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises new instance of TaskList.
     *
     * @param tasks ArrayList of Task in TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns size of List.
     *
     * @return Integer The size of the List.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Changes the isDone value of a Task.
     *
     * @param taskNum The number of the selected Task in the List.
     * @param isDone A boolean representing whether task has been completed.
     * @return Task The edited Task.
     * @throws DukeException
     */
    public Task markTask(int taskNum, boolean isDone) throws DukeException {
        Task taskToMark = null;
        try {
            taskToMark = tasks.get(taskNum - 1);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException(Ui.outOfBoundSelectionResponse(this.getSize()));
        }

        if (isDone) {
            taskToMark.mark();
        } else {
            taskToMark.unmark();
        }
        return taskToMark;
    }

    /**
     * Deletes a selected Task.
     *
     * @param taskNum The number of the selected Task in the List.
     * @return Task The deleted Task.
     * @throws DukeException
     */
    public Task deleteTask(int taskNum) throws DukeException {
        Task deleteTask = null;

        try {
            deleteTask = tasks.remove(taskNum);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException(Ui.outOfBoundSelectionResponse(this.getSize()));
        }

        return deleteTask;
    }

    public Task editTask(int taskNum, String item, String newInfo) throws DukeException {
        Task editTask = null;

        try {
            editTask = tasks.get(taskNum - 1);
            editTask.edit(item, newInfo);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException(Ui.outOfBoundSelectionResponse(this.getSize()));
        }
        return editTask;
    }

    /**
     * Adds a Todo Task.
     *
     * @param name The name of the new Todo Task.
     * @return Task The newly added Todo Task.
     */
    public Task addTodo(String name) {
        Todo newTask = new Todo(name);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds a Deadline Task.
     *
     * @param name The name of the new Deadline Task.
     * @param deadline The deadline for the task in a String.
     * @return Task The newly added Deadline Task.
     */
    public Task addDeadline(String name, String deadline) {
        Deadline newTask = new Deadline(name, deadline, FORMAT);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds an Event Task.
     *
     * @param name The name of the new Deadline Task.
     * @param from The starting date of the task in a String.
     * @param to The ending date of the task in a String.
     * @return Task The newly added Event Task.
     */
    public Task addEvent(String name, String from, String to) {
        Event newTask = new Event(name, from, to, FORMAT);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Finds Tasks in List related to a keyword.
     *
     * @param word The given keyword.
     * @return TaskList The List containing all the related Tasks.
     */
    public TaskList findRelated(String word) {
        Stream<Task> filterTasks = this.tasks.stream().filter(s -> s.isRelated(word));
        ArrayList<Task> lst = filterTasks.collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(lst);
    }

    /**
     * Converts the TaskList into a nicely formatted String with numbered bullet points.
     *
     * @return String The numbered List of Tasks.
     */
    public String toStringList() {
        if (this.tasks.isEmpty()) {
            return "";
        }
        String str = "1. " + this.tasks.get(0).toString();
        for (int i = 1; i < this.tasks.size(); i++) {
            str = str + "\n" + (i + 1) + ". " + this.tasks.get(i);
        }
        return str;
    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "";
        }
        String str = this.tasks.get(0).toString();
        for (int i = 1; i < this.tasks.size(); i++) {
            str = str + "\n" + this.tasks.get(i);
        }
        return str;
    }

}
