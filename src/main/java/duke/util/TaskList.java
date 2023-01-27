package duke.util;
import duke.Command;
import duke.Duke;
import duke.DukeException;
import duke.taskers.Deadline;
import duke.taskers.Event;
import duke.taskers.Task;
import duke.taskers.Todo;

import java.time.LocalDateTime;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfThings;

    public TaskList(ArrayList<Task> loadedTasks) {
        this.listOfThings = loadedTasks;
    }

//    public TaskList() {
//        this.listOfThings = new ArrayList<Task>();
//    }

    public ArrayList<Task> getList() {
        return this.listOfThings;
    }


    /**
     * Marks the task inside the list as done.
     *
     * @param index The index in the array of the task we want marked.
     * @return The task that is marked.
     */
    public Task markTaskInListDone(int index) {
        this.listOfThings.get(index).setDone();
        return this.listOfThings.get(index);
    }


    /**
     * Marks the task inside the list as undone.
     *
     * @param index The index in the array of the task we want unmarked.
     * @return The task we just unmarked.
     */
    public Task markTaskInListUndone(int index) {
        this.listOfThings.get(index).setUndone();
        return this.listOfThings.get(index);
    }


    /**
     * Adds the item to the taskList.
     *
     * @param text The command string.
     * @param add The Command type.
     * @return The task that is being added.
     * @throws DukeException When the wrong format is parsed.
     */
    public Task addItem(String text, Command add) throws DukeException {
        Task addedItem = null;
        if (add.equals(Command.TODO)) {
            String contents = Parser.parseTodo(text);
            if (contents.length() == 0) {
                throw new DukeException("The description of a todo cannot be empty");
            }
            addedItem = new Todo(contents, false);

        } else if (add.equals(Command.DEADLINE)) {
            String[] arr = Parser.parseDeadline(text);
            if (arr.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'deadline [do something] /by [date]");
            }
            LocalDateTime end = Duke.createLocalDateTime(arr[1]);
            if (end != null) {
                addedItem = new Deadline(arr[0], false, end);
            } else {
                throw new DukeException("Format date as YYYY-MM-DD HH:mm");
            }
        } else {
            String[] arr = Parser.parseEvent(text);
            if (arr.length != 3) {
                throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
            }
            LocalDateTime start = Duke.createLocalDateTime(arr[1]);
            LocalDateTime end = Duke.createLocalDateTime(arr[2]);
            if (start != null && end != null) {
                addedItem = new Event(arr[0], false, start, end);
            } else {
                throw new DukeException("Format date as YYYY-MM-DD HH:mm");
            }
        }
        this.listOfThings.add(addedItem);
        return addedItem;
    }


    public ArrayList<Task> findMatching(String keyword) {
        ArrayList<Task> matchingWords = new ArrayList<>();
        String betterKeyword = keyword.trim().toLowerCase();
        for (Task listOfThing : this.listOfThings) {
            if (listOfThing.getDescription().contains(betterKeyword)) {
                matchingWords.add(listOfThing);
            }
        }
        return matchingWords;
    }

    /**
     * Removes the item in the list.
     *
     * @param index The index of the item to be removed.
     */
    public Task removeItem(int index) {
        return this.listOfThings.remove(index);
    }

}
