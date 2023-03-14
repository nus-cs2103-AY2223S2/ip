package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;


/**
 * Keeps the list of tasks using an arraylist.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> a) {
        this.list = a;
    }

    /**
     * Identify the task from its number and mark it as complete.
     *
     * @param taskno - Number for the task on the list.
     */
    public void markTask(int taskno) throws DukeException {
        try {
            list.get(taskno).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number not in list, please fill again");
        }
    }




    /**
     * Identify the task from its number and mark it as incomplete.
     *
     * @param taskno Number for the task on the list.
     */
    public void unmarkTask(int taskno) throws DukeException {
        try {
            list.get(taskno).markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number not in list, please fill again");
        }
    }

    /**
     * Prints the current list of tasks.
     */
    public String printList() {
        String output = "";
        for (int x = 0; x < list.size(); x++) {
            output += String.valueOf(x + 1) + "." + list.get(x) + "\n";
        }
        return "Here are the tasks in your list:\n" + output + getLengthMessage();
    }

    /**
     * Add the input task object to the current object list.
     *
     * @param t Task object to be added.
     */
    public void addToList(Task t) {
        list.add(t);
    }


    /**
     * Identify the task from its number and remove it from the list.
     *
     * @param taskno Number for the task on the list.
     */
    public void deleteTask(int taskno) throws DukeException {
        try {
            list.remove(taskno);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number not in list, please fill again");
        }
    }

    /**
     * Shows how many tasks is in the list.
     *
     * @return Integer - Number of elements in list.
     */
    private int getLengthOfList() {
        return list.size();
    }

    /**
     * Output the statement for the number of tasks in the list.
     */
    public String getLengthMessage() {
        return "\nNow you have " + this.getLengthOfList() + " tasks in your list." + "\n\n";
    }

    /**
     * Method which finds the tasks which matches the inputted string.
     *
     * @param message String that the user wants to find.
     * @return List List of tasks which matches the input string.
     */
    public ArrayList<Task> findStringInTask(String message) {
        ArrayList<Task> arrStr = new ArrayList<>();
        for (Task t : list) {
            if (t.toString().contains(message)) {
                arrStr.add(t);
            }

        }
        return arrStr;
    }

    /**
     * Tag the indicated task with the input tag name.
     *
     * @param taskNum Task number to add tag to.
     * @param tagName Tag task with name.
     * @throws DukeException
     */
    public void tagTask(int taskNum, String tagName) throws DukeException {
        try {
            list.get(taskNum - 1).addTag(tagName);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number not in list, please fill again");
        }
    }
}
