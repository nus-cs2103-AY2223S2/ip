package genie;

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
    private static final String TODO = "todo ";
    private static final String DEADLINE = "deadline ";
    private static final String EVENT = "event ";

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
        String descOnly = removeCommandFromInput(i, TODO);
        ToDo t = new ToDo(descOnly);
        storeTask(t);
        return t;
    }

    /**
     * Adds <code>ToDo</code> into task list from .txt file input.
     * @param sf .txt input
     * @return task
     */
    public Task addToDoFromFile(String sf) {
        char status = statusFromTaskInfo(sf);
        String desc = removeTypeAndStatus(sf);
        ToDo t = new ToDo(desc);
        storeTask(t);
        if (isMarked(status)) {
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
        String deadlineTime = contents[1];
        String commandAndDesc = contents[0];
        String descOnly = removeCommandFromInput(commandAndDesc, DEADLINE);
        Deadline d = new Deadline(descOnly, deadlineTime);
        storeTask(d);
        return d;
    }
    /**
     * Adds <code>Deadline</code> into task list from .txt file input.
     * @param sf .txt input
     * @return task
     */
    public Task addDeadlineFromFile(String sf) {
        char status = statusFromTaskInfo(sf);
        String desc = removeTypeAndStatus(sf);
        String[] contents = splitContents(desc);
        String descOnly = contents[0];
        String deadlineBy = contents[1];
        Deadline d = new Deadline(descOnly, deadlineBy);
        storeTask(d);
        if (isMarked(status)) {
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
        String[] timings_fromTo = contents[1].split(" /to ");
        String from = timings_fromTo[0];
        String to = timings_fromTo[1];
        String commandAndDesc = contents[0];
        String descOnly = removeCommandFromInput(commandAndDesc, EVENT);
        Event e = new Event(descOnly, from, to);
        storeTask(e);
        return e;
    }
    /**
     * Adds <code>Event</code> into task list from .txt file input.
     * @param sf .txt input
     * @return task
     */
    public Task addEventFromFile(String sf) { // todo load up saved tasks when app starts
        char status = statusFromTaskInfo(sf);
        String desc = removeTypeAndStatus(sf);
        String[] contents = splitContents(desc);
        String descOnly = contents[0];
        String timing = contents[1];
        String[] splitTimings_from_to = timing.split(" - ");
        String eventFrom = splitTimings_from_to[0];
        String eventTo = splitTimings_from_to[1];

        Event e = new Event(descOnly, eventFrom, eventTo);
        storeTask(e);
        if (isMarked(status)) {
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
            boolean taskContainsKeyword = t.containsWord(keyword);
            if (taskContainsKeyword) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }
    //@@author
    public String removeCommandFromInput(String input, String command) {
        String inputWithoutCommand = input.replace(command, "");
        return inputWithoutCommand;
    }
    public char statusFromTaskInfo(String taskInfo) {
        char status = taskInfo.charAt(4);
        return status;
    }
    public String removeTypeAndStatus(String taskInfo) {
        String content = taskInfo.substring(7);
        return content;
    }
    public boolean isMarked(char status) {
        return status == 'X';
    }
    public String[] splitContents(String content) {
        return content.split(" \\| ");
    }
}
