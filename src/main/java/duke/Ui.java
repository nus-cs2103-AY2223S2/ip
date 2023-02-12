package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.enums.Commands;
import duke.enums.Languages;
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
        try {
            return sc.nextLine();
        } catch (java.util.NoSuchElementException e) {
            Ui.showEnd();
            System.exit(0);
            return "";
        }
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
        printer(Views.WELCOME_STRING.str());
    }

    /**
     * Return Welcome String as String
     *
     * @return Welcome String
     */
    public String stringWelcome() {
        return Views.WELCOME_STRING.str();
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
    public void showList(ArrayList<Task> tasks, boolean isQuery) throws DukeException {
        printer(stringList(tasks, isQuery));
    }

    /**
     * Takes in a list of tasks and turn it into String
     *
     * @param tasks TaskList object to print the list
     * @return String representation of list of tasks
     * @throws DukeException when the task is not found in the list
     */
    public String stringList(TaskList tasks) throws DukeException {
        return stringList(tasks.getList(), false);
    }

    /**
     * Takes in a list of tasks and turn it into String
     *
     * @param tasks TaskList object to print the list
     * @return String representation of list of tasks
     * @throws DukeException when the task is not found in the list
     */
    public String stringList(TaskList tasks, boolean isGui, boolean isQuery) throws DukeException {
        return stringList(tasks.getList(), isGui, isQuery);
    }

    /**
     * Takes in a ArrayList of tasks and Strings it
     *
     * @param tasks TaskList object to print the list
     * @return String representation of list of tasks
     * @throws DukeException
     */
    public String stringList(ArrayList<Task> tasks, boolean isQuery) throws DukeException {
        return stringList(tasks, false, isQuery);
    }

    /**
     * Takes in a ArrayList of tasks and Strings it
     *
     * @param tasks TaskList object to print the list
     * @return String representation of list of tasks
     * @throws DukeException
     */
    public String stringList(ArrayList<Task> tasks, boolean isGui, boolean isQuery) throws DukeException {
        // If Size == 0 && isQuery = throw error of cannot find
        // Apply de morgan Size != 0 || !isQuery = !throw error of cannot find
        assert tasks.size() != 0 || !isQuery : Views.CANNOT_FIND_STRING.str();
        assert tasks.size() != 0 : Views.EMPTY_LIST_STRING.str();
        String returnString = Views.LIST_STRING.str();
        if (isQuery) {
            returnString = Views.FOUND_LIST_STRING.str();
        }
        if (isGui) {
            returnString = returnString.substring(0, returnString.length() - 6);
        }
        return returnString + stringListRaw(tasks, isGui);
    }

    private String stringListRaw(ArrayList<Task> tasks, boolean isGui) throws DukeException {
        assert tasks.size() != 0 : Views.EMPTY_LIST_STRING.str();
        String returnString = "";
        for (int i = 0; i < tasks.size(); i++) {
            returnString += ((i + 1) + "." + tasks.get(i)) + "\n";
            if (!isGui) {
                returnString += "      ";
            }
        }
        try {
            if (isGui) {
                return returnString.substring(0, returnString.length() - 1);
            } else {
                return returnString.substring(0, returnString.length() - 7);
            }
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            throw new DukeException(Views.EMPTY_LIST_STRING);
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
     * @param tasks TaskList object to get the task
     * @throws DukeException when the task is not found in the list
     */
    public void showMarkDone(ArrayList<Task> tasks) throws DukeException {
        printer(stringMarkDone(tasks, false));
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
        return Views.MARK_DONE_STRING.str() + tasks.get(taskNum);
    }

    /**
     * Prints out the task that is marked done
     *
     * @param tasks TaskList object to get the task
     * @return String representation of what the task looks like when its done
     * @throws DukeException when the task is not found in the list
     */
    public String stringMarkDone(ArrayList<Task> tasks, boolean isGui) throws DukeException {
        if (tasks.size() == 1) {
            return Views.MARK_DONE_STRING.str() + stringListRaw(tasks, isGui);
        } else {
            return Views.MARK_MANY_DONE_STRING.str() + stringListRaw(tasks, isGui);
        }
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
     * @param tasks TaskList object to get the task
     * @throws DukeException when the task is not found in the list
     */
    public void showUnmarkDone(ArrayList<Task> tasks) throws DukeException {
        printer(stringUnmarkDone(tasks, false));
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
        return (Views.UNMARK_DONE_STRING.str() + tasks.get(taskNum));
    }

    /**
     * Prints out the task that is marked undone
     *
     * @param tasks ArrayList object to get the task
     * @return String representation of when task is undone
     * @throws DukeException when the task is not found in the list
     */
    public String stringUnmarkDone(ArrayList<Task> tasks, boolean isGui) throws DukeException {
        if (tasks.size() == 1) {
            return Views.UNMARK_DONE_STRING.str() + stringListRaw(tasks, isGui);
        } else {
            return Views.UNMARK_MANY_DONE_STRING.str() + stringListRaw(tasks, isGui);
        }
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
     * @param delTasks tasks that is deleted
     * @param task     TaskList object to get the count
     * @throws DukeException
     */
    public void showDel(ArrayList<Task> delTasks, TaskList task) throws DukeException {
        printer(stringDel(delTasks, task, false));
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
        String returnString = Views.DELETE_DONE_STRING.str();
        returnString += delTask.toString();
        returnString += "\n      ";
        returnString += Views.TASK_COUNT_1_STRING.str();
        returnString += task.size();
        returnString += Views.TASK_COUNT_2_STRING.str();
        return returnString;
    }

    /**
     * Prints out the task that is deleted and show the number of tasks left
     * Prints out the task that is deleted String string the number of tasks left
     *
     * @param delTask task that is deleted
     * @param task    TaskList object to get the count
     * @return String representation of deleted task
     * @throws DukeException
     */
    public String stringDel(ArrayList<Task> delTask, TaskList task, Boolean isGui) throws DukeException {
        String returnString = Views.DELETE_DONE_STRING.str();
        if (delTask.size() > 1) {
            returnString = Views.DELETE_MANY_DONE_STRING.str();
        }
        returnString += stringListRaw(delTask, isGui);
        returnString += "\n      ";
        returnString += Views.TASK_COUNT_1_STRING.str();
        returnString += task.size();
        returnString += Views.TASK_COUNT_2_STRING.str();
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
     *
     * @return String representation of the task cleared
     */
    public String stringClear() {
        return Views.CLEAR_LIST_STRING.str();
    }

    /**
     * Prints out bye message
     */
    public static void showEnd() {
        printer(stringEnd());
    }

    /**
     * Returns out bye message
     *
     * @return String representation of exit message
     */
    public static String stringEnd() {
        return Views.END_STRING.str();
    }

    /**
     * Prints the language the user has selected
     *
     * @param lang
     */
    public void showSetLang(Languages lang) {
        printer(stringSetLang(lang));
    }

    /**
     * Returns the language the user has selected
     *
     * @param lang
     * @return String representation of set language
     */
    public String stringSetLang(Languages lang) {
        return Views.SELECTED_LANG_STRING.str() + lang.toString();
    }

    /**
     * Prints the help message
     */
    public void showHelp() {
        printer(stringHelp(false));
    }

    /**
     * Prints the help message that the user has selected
     *
     * @param command that the user want to know more about
     */
    public void showHelp(Commands command) {
        printer(stringHelp(command));
    }

    /**
     * Returns the help message
     *
     * @param isGui format properly for GUI display vs CLI
     * @return String representation of help messages
     */
    public String stringHelp(boolean isGui) {
        return Commands.helpMessages(isGui);
    }

    /**
     * Returns the help message the user has selected
     *
     * @param command that the user want to know more about
     * @return String representation of command help message
     */
    public String stringHelp(Commands command) {
        return command.getExplanation();
    }

    /**
     * Prints the help message and inform user they didn't select
     */
    public void showHelpError() {
        printer(stringHelpError(false));
    }

    /**
     * Returns the help message and inform user they didn't select
     *
     * @param isGui format properly for GUI display vs CLI
     * @return String representation of set language
     */
    public String stringHelpError(boolean isGui) {
        return Views.CANNOT_FIND_STRING.str() + " " + Commands.helpMessages(isGui);
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
     * Prints out the error given in as a String
     *
     * @param err
     * @return String representation of error
     */
    public static String stringError(Exception err) {
        return err.getMessage();
    }

    /**
     * Private method to print with lines and indents
     *
     * @param to of the event ending datePrint
     */
    private static void printer(String toPrint) {
        System.out.println("    " + Views.LINE_STRING.str());
        System.out.println("      " + toPrint);
        System.out.println("    " + Views.LINE_STRING.str());
        System.out.println();
    }
}
