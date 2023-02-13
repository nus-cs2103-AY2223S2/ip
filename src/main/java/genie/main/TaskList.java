package genie.main;

import genie.task.Deadline;
import genie.task.Event;
import genie.task.Task;
import genie.task.ToDo;

import java.util.ArrayList;

/**
 * Contains the task list and has operations to perform actions on it.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * A constructor that initialises <code>ArrayList&lt;Task&gt; of size 100.</code>
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Stores task into the task list.
     * @param t task
     */
    public void storeTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Adds <code>ToDo</code> into task list from user's input.
     * @param i user's input
     * @return task
     */
    public Task addToDoFromUser(String i) {
        ToDo t = new ToDo(i.replace("todo ", ""));
        storeTask(t);
        return t;
    }

    /**
     * Adds <code>ToDo</code> into task list from .txt file input.
     * @param sf .txt input
     * @return task
     */
    public Task addToDoFromFile(String sf) {
        char status = sf.charAt(4);
        ToDo t = new ToDo(sf.substring(7));
        storeTask(t);
        if (status == 'X') {
            t.markDone();
        }
        return t;
    }

    /**
     * Adds <code>Deadline</code> into task list from user's input.
     * @param i user's input
     * @return task
     */
    public Task addDeadlineFromUser(String i) {
        String[] contents = i.split(" /by ");
        Deadline d = new Deadline(contents[0].replace("deadline ", ""), contents[1]);
        storeTask(d);
        return d;
    }
    /**
     * Adds <code>Deadline</code> into task list from .txt file input.
     * @param sf .txt input
     * @return task
     */
    public Task addDeadlineFromFile(String sf) {
        char status = sf.charAt(4);
        String[] contents = sf.substring(7).split(" \\| ");
        String deadlineDesc = contents[0];
        String deadlineBy = contents[1];
        Deadline d = new Deadline(deadlineDesc, deadlineBy);
        storeTask(d);
        if (status == 'X') {
            d.markDone();
        }
        return d;
    }
    /**
     * Adds <code>Event</code> into task list from user's input.
     * @param i user's input
     * @return task
     */
    public Task addEventFromUser(String i) {
        String[] contents = i.split(" /from ");
        String[] fromTo = contents[1].split(" /to ");
        Event e = new Event(contents[0].replace("event ", ""), fromTo[0], fromTo[1]);
        storeTask(e);
        return e;
    }
    /**
     * Adds <code>Event</code> into task list from .txt file input.
     * @param sf .txt input
     * @return task
     */
    public Task addEventFromFile(String sf) {
        char status = sf.charAt(4);
        String[] contents = sf.substring(7).split(" \\| ");
        String eventDesc = contents[0];
        String[] eventFromTo = contents[1].split(" - ");
        String eventFrom = eventFromTo[0];
        String eventTo = eventFromTo[1];
        Event e = new Event(eventDesc, eventFrom, eventTo);
        storeTask(e);
        if (status == 'X') {
            e.markDone();
        }
        return e;
    }

    /**
     * Delete task at index of task list.
     * @param index of task list to be deleted
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Get task list.
     * @return task list
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    //@@author mandykqh-reused
    //Reused from https://stackoverflow.com/questions/17134773/to-check-if-string-contains-particular-word
    //with minor modifications
    public ArrayList<Task> searchMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.containsWord(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }
    //@@author
}
