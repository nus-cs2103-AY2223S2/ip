package duke.task;

import duke.exception.*;
import duke.ui.*;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> items;

    /**
     * Constructor for TaskList to initialize the internal arraylist of Tasks.
     */
    public TaskList() {
        items = new ArrayList<>();
    }

    /**
     * Method to add a Task directly to TaskList.
     *
     * @param task Task to be added to TaskList.
     * @param ui ui to give user the message.
     */
    public void add(Task task, Ui ui) {
        items.add(task);
        ui.addTask(task, items.size());
    }

    /**
     * Method to add an Event to the TaskList via the input String array.
     *
     * @param curr a String array from user input to be parsed.
     * @param ui ui to give user the message.
     * @throws DukeException If user input is erroneous.
     */
    public void addEvent(String[] curr, Ui ui) throws DukeException {
        try {
            String descr = curr[0].substring(6).trim();
            String[] newCurr = curr[1].split("/to");
            String from = newCurr[0].trim();
            String to = newCurr[1].trim();
            add(new Event(descr, from, to), ui);
        } catch (Exception e) {
            throw new DukeException("You need to fill in an event with format `event {title} /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm`");
        }
    }

    /**
     * Method to add a Deadline to the TaskList via the input String array.
     *
     * @param curr a String array from user input to be parsed.
     * @param ui ui to give user the message.
     * @throws DukeException If user input is erroneous.
     */
    public void addDeadline(String[] curr, Ui ui) throws DukeException {
        try {
            String descr = curr[0].substring(9).trim();
            String by = curr[1].trim();
            add(new Deadline(descr, by), ui);
        } catch (Exception e) {
            throw new DukeException("You need to fill in a deadline with format `deadline {title} /by dd/MM/yyyy HHmm`");
        }
    }

    /**
     * Method to add a ToDo to the TaskList via the input String array.
     *
     * @param curr a String array from user input to be parsed.
     * @param ui ui to give user the message.
     * @throws DukeException If user input is erroneous.
     */
    public void addToDo(String[] curr, Ui ui) throws DukeException {
        String todo = curr[0].substring(5).trim();
        if (todo.isBlank()) {
            throw new DukeException("You need to add a todo with format `todo {title}`");
        } else { add(new ToDo(todo), ui); }
    }

    /**
     * Method to delete a Task from the TaskList via input String array.
     *
     * @param curr_title a String array from user input to be parsed.
     * @param ui ui to give user the message.
     * @throws DukeException If user input is erroneous.
     */
    public void deleteTask(String[] curr_title, Ui ui) throws DukeException {
        try {
            int idx = Integer.parseInt(curr_title[1]) - 1;
            if (idx >= items.size() || idx < 0) {
                throw new DukeException();
            } else {
                ui.deleteMessage(items.get(idx), items.size());
                items.remove(idx);
            }
        } catch (Exception e) {
            throw new DukeException("Please give a valid index between 1 and " + items.size());
        }
    }

    /**
     * Method to mark Task in TaskList via input String array.
     *
     * @param curr_title a String array from user input to be parsed
     * @param ui ui to give user the message.
     * @throws DukeException If user input is erroneous.
     */
    public void mark(String[] curr_title, Ui ui) throws DukeException {
        try {
            int idx = Integer.parseInt(curr_title[1]) - 1;
            if (items.get(idx).isMarked()) {
                throw new AlreadyMarkedException();
            } else {
                items.get(idx).mark();
                ui.markMessage(items.get(idx));
            }
        } catch (AlreadyMarkedException e) {
            throw new AlreadyMarkedException("Already Marked!");
        } catch (Exception e) {
            throw new DukeException("Please give a valid input with index between 1 and " + items.size());
        }
    }

    /**
     * Method to unmark Task in TaskList via input String array.
     *
     * @param curr_title a String array from user input to be parsed.
     * @param ui ui to give user the message.
     * @throws DukeException If user input is erroneous.
     */
    public void unmark(String[] curr_title, Ui ui) throws DukeException {
        try {
            int idx = Integer.parseInt(curr_title[1]) - 1;
            if (!items.get(idx).isMarked()) {
                throw new AlreadyUnmarkedException();
            } else {
                items.get(idx).unmark();
                ui.unmarkMessage(items.get(idx));
            }
        } catch (AlreadyUnmarkedException e) {
            throw new AlreadyUnmarkedException("Already unmarked!");
        } catch (Exception e) {
            throw new DukeException("Please give a valid input with index between 1 and " + items.size());
        }
    }

    /**
     * Method to add tasks without giving the UI messages.
     * Primarily for when loading from hard drive data.
     *
     * @param task Task to be added.
     */
    public void initAdd(Task task) {
        items.add(task);
    }

    /**
     * Method to get the String representation of entire list for writing to hard drive.
     *
     * @return alternative String representation of list.
     */
    public String itemsToData() {
        String data = "";
        for (int i = 0; i < items.size(); i++) {
            data += items.get(i).toData();
            data += "\n";
        }
        return data;
    }

    /**
     * Method to end the program.
     *
     * @param ui ui to give user the message.
     */
    public void end(Ui ui) {
        ui.end();
        System.exit(0);
    }

    /**
     * Overloaded String method to get representation of entire list for user.
     *
     * @return String representation of list for user.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < items.size(); i++) {
            Task item = items.get(i);
            res += "\t " + (i + 1) + ". " + item + "\n";
        }
        return res;
    }
}
