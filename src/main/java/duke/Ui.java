package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.enums.Views;
import duke.task.Task;

/**
 * Class to interact with the user via commands and messages
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructs the UI
     */
    Ui() {

    }

    /**
     * Reads the input given by the user
     *
     * @return String input from the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out the welcome message
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ / _   _| | _____ \n"
                + "| | | | | | | |/ / _ /\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ /__,_|_|/_/___|\n";
        System.out.println("Hello from\n" + logo);
        printer(Views.WELCOME_STRING.eng());
    }

    /**
     * Return Welcome String as String
     * @return Welcome String
     */
    public String stringWelcome() {
        return Views.WELCOME_STRING.eng();
    }

    /**
     * Takes in a list of tasks and prints it all out
     *
     * @param tasks TaskList object to print the list
     * @throws DukeException when the task is not found in the list
     */
    public void showList(TaskList tasks) throws DukeException {
        printer(stringList(tasks));
    }

    /**
     * Takes in a ArrayList of tasks and prints it all out
     *
     * @param tasks TaskList object to print the list
     * @throws DukeException
     */
    public void showList(ArrayList<Task> tasks) throws DukeException {
        printer(stringList(tasks));
    }

    /**
     * Takes in a list of tasks and turn it into String
     *
     * @param tasks TaskList object to print the list
     * @return String representation of list of tasks
     * @throws DukeException when the task is not found in the list
     */
    public String stringList(TaskList tasks) throws DukeException {
        return stringList(tasks.getList());
    }

    /**
     * Takes in a list of tasks and turn it into String
     *
     * @param tasks TaskList object to print the list
     * @return String representation of list of tasks
     * @throws DukeException when the task is not found in the list
     */
    public String stringList(TaskList tasks, boolean isGui) throws DukeException {
        return stringList(tasks.getList(), isGui);
    }

    /**
     * Takes in a ArrayList of tasks and Strings it
     *
     * @param tasks TaskList object to print the list
     * @return String representation of list of tasks
     * @throws DukeException
     */
    public String stringList(ArrayList<Task> tasks) throws DukeException {
        return stringList(tasks, false);
    }

    /**
     * Takes in a ArrayList of tasks and Strings it
     *
     * @param tasks TaskList object to print the list
     * @return String representation of list of tasks
     * @throws DukeException
     */
    public String stringList(ArrayList<Task> tasks, boolean isGui) throws DukeException {
        assert tasks.size() != 0 : Views.CANNOT_FIND_STRING.eng();
        String toPrint = Views.FOUND_LIST_STRING.eng();
        if (isGui) {
            toPrint = toPrint.substring(0, toPrint.length() - 6);
        }
        for (int i = 0; i < tasks.size(); i++) {
            toPrint += ((i + 1) + "." + tasks.get(i)) + "\n";
            if (!isGui) {
                toPrint += "      ";
            }
        }
        if (isGui) {
            return toPrint.substring(0, toPrint.length() - 1);
        } else {
            return toPrint.substring(0, toPrint.length() - 7);
        }
    }

    /**
     * Prints out the task that is marked done
     *
     * @param tasks   TaskList object to get the task
     * @param taskNum int index of task in the ArrayList
     * @throws DukeException when the task is not found in the list
     */
    public void showMarkDone(TaskList tasks, int taskNum) throws DukeException {
        printer(stringMarkDone(tasks, taskNum));
    }

    /**
     * Prints out the task that is marked done
     *
     * @param tasks   TaskList object to get the task
     * @param taskNum int index of task in the ArrayList
     * @return String representation of what the task looks like when its done
     * @throws DukeException when the task is not found in the list
     */
    public String stringMarkDone(TaskList tasks, int taskNum) throws DukeException {
        return Views.MARK_DONE_STRING.eng() + tasks.get(taskNum);
    }

    /**
     * Prints out the task that is marked undone
     *
     * @param tasks   TaskList object to get the task
     * @param taskNum int index of task in the ArrayList
     * @throws DukeException when the task is not found in the list
     */
    public void showUnmarkDone(TaskList tasks, int taskNum) throws DukeException {
        printer(stringUnmarkDone(tasks, taskNum));
    }

    /**
     * Prints out the task that is marked undone
     *
     * @param tasks   TaskList object to get the task
     * @param taskNum int index of task in the ArrayList
     * @return String representation of when task is undone
     * @throws DukeException when the task is not found in the list
     */
    public String stringUnmarkDone(TaskList tasks, int taskNum) throws DukeException {
        return (Views.UNMARK_DONE_STRING.eng() + tasks.get(taskNum));
    }

    /**
     * Prints out the task that is newly added
     *
     * @param newTask new Task object to be printed
     */
    public void showAdd(Task newTask) {
        printer(stringAdd(newTask));
    }

    /**
     * Prints out the task that is newly added
     *
     * @param newTask new Task object to be printed
     * @return String representation of new task added
     */
    public String stringAdd(Task newTask) {
        return "added: " + newTask;
    }

    /**
     * Prints out the task that is deleted and show the number of tasks left
     * Prints out the task that is deleted String string the number of tasks left
     *
     * @param delTask task that is deleted
     * @param task    TaskList object to get the count
     */
    public void showDel(Task delTask, TaskList task) {
        printer(stringDel(delTask, task));
    }

    /**
     * Prints out the task that is deleted and show the number of tasks left
     * Prints out the task that is deleted String string the number of tasks left
     *
     * @param delTask task that is deleted
     * @param task    TaskList object to get the count
     * @return String representation of deleted task
     */
    public String stringDel(Task delTask, TaskList task) {
        String returnString = Views.DELETE_DONE_STRING.eng();
        returnString += delTask.toString();
        returnString += "\n      ";
        returnString += Views.TASK_COUNT_1_STRING.eng();
        returnString += task.size();
        returnString += Views.TASK_COUNT_2_STRING.eng();
        return returnString;
    }

    /**
     * Prints out to user to show that the task has been cleared
     */
    public void showClear() {
        printer(stringClear());
    }

    /**
     * Prints out to user to show that the task has been cleared
     * @return String representation of the task cleared
     */
    public String stringClear() {
        return Views.CLEAR_LIST_STRING.eng();
    }

    /**
     * Prints out bye message
     */
    public void showEnd() {
        printer(stringEnd());
    }

    /**
     * Prints out bye message
     * @return String representation of exit message
     */
    public String stringEnd() {
        return Views.END_STRING.eng();
    }

    /**
     * Prints out the error given in as a String
     *
     * @param err
     */
    public void showError(String err) {
        printer(err);
    }

    /**
     * Prints out the error given in as a String
     *
     * @param err
     */
    public void showError(Exception err) {
        printer(err.getMessage());
    }

    /**
     * Prints out the error given in as a String
     *
     * @param err
     */
    public void showError(Error err) {
        printer(err.getMessage());
    }

    /**
     * Prints out the error given in as a String
     *
     * @param err
     * @return String representation of error
     */
    public String stringError(String err) {
        return err;
    }

    /**
     * Private method to print with lines and indents
     *
     * @param to of the event ending datePrint
     */
    private static void printer(String toPrint) {
        System.out.println("    " + Views.LINE_STRING.eng());
        System.out.println("      " + toPrint);
        System.out.println("    " + Views.LINE_STRING.eng());
        System.out.println();
    }
}
