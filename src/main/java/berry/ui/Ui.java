package berry.ui;

import java.util.ArrayList;
import java.util.Scanner;

import berry.exception.NoTasksException;
import berry.task.Task;
import berry.task.TaskList;

/**
 * Deals with interactions with the user.
 */

public class Ui {

    private static Scanner sc = new Scanner(System.in);

    /**
     * General command to show a string to the ui.
     *
     * @param toPrint string to be printed to ui
     * @return string to be printed to ui
     */
    public static String show(String toPrint) {
        return toPrint;
    }

    public static String showWelcome() {
        String logo = "";
        return logo + "Hey there! \nI'm Berry the Bunny~ <3 "
                + "\nWhat are you looking to plan today?"
                + "\nType 'help' for the help menu!";
    }

    public static String showGoodbye() {
        return "Bye! I hope Berry was helpful to you <: \n";
    }

    public static String showListOfTasks(TaskList tasks) throws NoTasksException {
        ArrayList<Task> listOfTasks = tasks.getList();
        int length = listOfTasks.size();

        if (length == 0) {
            throw new NoTasksException();
        }

        String output = "Here's what I have for you:\n";
        for (int i = 0; i < length; i++) {
            output += "\t" + (i + 1) + ". " + listOfTasks.get(i).toString() + "\n";
        }
        return output;
    }

    public static String showUnmark() {
        return "Okay! I'll set the task as not done\n";
    }

    public static String showMark() {
        return "Alright~ I'll set the task as done!\n";
    }

    public static String showAdd() {
        return "You can do it! I've added this task for ya\n";
    }

    public static String showDelete() {
        return "Here you go! I've deleted this task for ya\n";
    }

    public static String showFind() {
        return "Here's what I found for ya\n";
    }

    public static String showLoadingError() {
        return "I can't seem to load the file\n";
    }

    public static String showHelpMenu() {
        String output = "Here's what berry can do:"
            + "\n-----------------Adding Tasks------------------"
                + "\n1. todo <task_name> [#hash_tag] \n\t- Adds a task you have to do"
            + "\n2. deadline <task_name> \n\t/by<YYYY-MM-DD> [#hash_tag] \n\t- Adds a task with a deadline"
            + "\n3. event <task_name> /from<YYYY-MM-DD> \n\t/to<YYYY-MM-DD> [#hash_tag]"
                + "\n\t- Adds an event you have to attend to"
            + "\n----------------Showing Tasks-----------------"
            + "\nlist \n\t- Shows all the tasks you have currently"
            + "\n-----------------Marking tasks-----------------"
                + "\n1. mark <index> \n\t- Marks a task with index as done"
            + "\n2. unmark <index> \n\t- Marks a task with the index as not done"
            + "\n-----------------Deleting tasks-----------------"
                + "\ndelete <index> \n\t- Deletes a task with index"
            + "\n-----------------Finding tasks:-----------------"
                + "\nfind <keyword> "
            + "\n\t- Shows all the tasks with corresponding task \n\tindex which contains the keyword";

        output += showNotice();
        return output;
    }

    public static String showNotice() {

        String output = "\n------------------------------------------------"
                + "\nNote that '[#hash_tag]' is optional";
        output += "\nE.g. Berry accepts 'todo sweep room' and\n\t\t'todo sweep room #chores'";
        return output;
    }
}
