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
     * @return String
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
     * Takes in a list of tasks and prints it all out
     *
     * @param tasks
     * @throws DukeException
     */
    public void showList(TaskList tasks) throws DukeException {
        if (tasks.size() == 0) {
            printer(Views.EMPTY_LIST_STRING.eng());
        } else {
            String toPrint = "";
            for (int i = 0; i < tasks.size(); i++) {
                toPrint += ((i + 1) + "." + tasks.get(i)) + "\n      ";
            }
            printer(toPrint.substring(0, toPrint.length() - 7));
        }
    }

    /**
     * Takes in a ArrayList of tasks and prints it all out
     *
     * @param tasks
     * @throws DukeException
     */

    public void showList(ArrayList<Task> tasks) throws DukeException {
        if (tasks.size() == 0) {
            printer(Views.CANNOT_FIND_STRING.eng());
        } else {
            String toPrint = Views.FOUND_LIST_STRING.eng();
            for (int i = 0; i < tasks.size(); i++) {
                toPrint += ((i + 1) + "." + tasks.get(i)) + "\n      ";
            }
            printer(toPrint.substring(0, toPrint.length() - 7));
        }
    }

    /**
     * Prints out the task that is marked done
     *
     * @param tasks
     * @param taskNo
     * @throws DukeException
     */
    public void showMarkDone(TaskList tasks, int taskNo) throws DukeException {
        printer(Views.MARK_DONE_STRING.eng() + tasks.get(taskNo));
    }

    /**
     * Prints out the task that is marked undone
     *
     * @param tasks
     * @param taskNo
     * @throws DukeException
     */
    public void showUnmarkDone(TaskList tasks, int taskNo) throws DukeException {
        printer(Views.UNMARK_DONE_STRING.eng() + tasks.get(taskNo));
    }

    /**
     * Prints out the task that is newly added
     *
     * @param newTask
     */
    public void showAdd(Task newTask) {
        printer("added: " + newTask);
    }

    /**
     * Prints out the task that is deleted and show the number of tasks left
     *
     * @param delTask
     * @param task
     */
    public void showDel(Task delTask, TaskList task) {
        String returnString = Views.DELETE_DONE_STRING.eng();
        returnString += delTask.toString();
        returnString += "\n      ";
        returnString += Views.TASK_COUNT_1_STRING.eng();
        returnString += task.size();
        returnString += Views.TASK_COUNT_2_STRING.eng();
        printer(returnString);
    }

    /**
     * Prints out to user to show that the task has been cleared
     */
    public void showClear() {
        printer(Views.CLEAR_LIST_STRING.eng());
    }

    /**
     * Prints out bye message
     */
    public void showEnd() {
        printer(Views.END_STRING.eng());
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
     * Print out load error when loading from a file
     */
    public void showLoadingError() {
        printer("File load has error");
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
