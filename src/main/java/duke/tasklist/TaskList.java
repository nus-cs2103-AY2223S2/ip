package duke.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.Parser;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeTaskNotFoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * TaskList class.
 * Handle tasks in a list.
 */
public class TaskList {

    protected ArrayList<Task> arrayList;
    protected Parser parser = new Parser();

    /**
     * Constructor for TaskList.
     * @param arrayList ArrayList to be wrapped around TaskList.
     */
    public TaskList(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
    }

    /**
     * Marks the task and returns it.
     * @param index Index of task in the list.
     * @return Marked task.
     */
    public Task mark(Integer index) {
        arrayList.get(index - 1).markAsDone();
        return arrayList.get(index - 1);
    }

    /**
     * Unmarks the task and returns it.
     * @param index Index of task in the list.
     * @return Unmarked task.
     */
    public Task unmark(Integer index) {
        arrayList.get(index - 1).markAsUndone();
        return arrayList.get(index - 1);
    }

    /**
     * Deletes task from the list and returns it.
     * @param index Index of task in the list.
     * @return Deleted Task.
     */
    public Task delete(Integer index) {
        Task t = arrayList.get(index - 1);
        arrayList.remove(index - 1);
        return t;
    }


    /**
     * Lists tasks that has the given keyword.
     * @param keyword Keyword user inputs.
     * @throws DukeTaskNotFoundException If keyword cannot be found in task list.
     */
    public TaskList find(String keyword) throws DukeTaskNotFoundException {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).toString().indexOf(keyword) != -1) {
                temp.add(arrayList.get(i));
            }
        }
        if (temp.isEmpty()) {
            throw new DukeTaskNotFoundException("No task with that keyword!");
        }
        TaskList tempTaskList = new TaskList(temp);
        tempTaskList.list();
        return tempTaskList;
    }

    /**
     * Adds a Todo task in the list and returns it.
     * @param description Description of todo task.
     * @return Added task.
     */
    public Task addTodo(String description) {

        Task t = new Todo(description);
        arrayList.add(t);
        return t;
    }

    /**
     * Adds a Deadline task in the list and returns it.
     * @param description Description of deadline task.
     * @param by Date when deadline is due.
     * @return Added task.
     */
    public Task addDeadline(String description, String by) throws DukeInvalidArgumentException {
        LocalDateTime byAsLDT = parser.getAsLocalDate(by);
        Task t = new Deadline(description, byAsLDT);
        arrayList.add(t);
        return t;
    }

    /**
     * Adds an Event task in the list and returns it.
     * @param description Description of event task.
     * @param from Date when event starts.
     * @param to Date when event ends.
     * @return Added task.
     */
    public Task addEvent(String description, String from, String to) throws DukeInvalidArgumentException {
        LocalDateTime fromAsLDT = parser.getAsLocalDate(from);
        LocalDateTime toAsLDT = parser.getAsLocalDate(to);
        Task t = new Event(description, fromAsLDT, toAsLDT);
        arrayList.add(t);
        return t;
    }


    /**
     * Lists out the task list in order when they are added in.
     */
    public void list() {
        for (int i = 1; i < arrayList.size() + 1; i++) {
            System.out.println(i + ". " + arrayList.get(i - 1));
        }
    }

    /**
     * Returns the length of the list.
     * @return Length of list.
     */
    public Integer getLength() {
        return arrayList.size();
    }

    /**
     * Returns a task.
     * @param i Index of task.
     * @return Task.
     */
    public Task get(Integer i) {
        return arrayList.get(i);
    }

    /**
     * Moves task in the list to an archive list.
     * @param archives Archive list.
     */
    public void archiveAll(ArchivedTaskList archives) {

        while (arrayList.size() > 0) {
            Task deleted = this.delete(arrayList.size());
            archives.addDeletedTask(deleted);
        }
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }
    /**
     * Returns a string of the tasks in order of when they are added.
     * @return String of the task list.
     */
    public String toString() {
        String str = "";
        for (int i = 1; i < arrayList.size() + 1; i++) {
            str = str + i + ". " + arrayList.get(i - 1) + "\n";
        }
        return str;
    }



}
