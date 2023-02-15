package berry.ui;

import java.util.ArrayList;
import java.util.Scanner;

import berry.task.Task;
import berry.task.TaskList;

/**
 * Deals with interactions with the user.
 */

public class Ui {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Reads the user input.
     *
     * @return the user input as a string
     */
    public static String readCommand() {
        return sc.nextLine();
    }

    public static String show(String toPrint) {
        return toPrint;
    }

    public static String showWelcome() {
        String logo = "";
        return logo + "Hey there! \nI'm Berry the Bunny~ ૮ ˶ᵔ ᵕ ᵔ˶ ა\n"
                + "What are you looking to plan today?";
    }

    public static String showGoodbye() {
        return "Bye! I hope Berry was helpful to you <: \n";
    }

    public static String showError(String message) {
        return message;
    }

    public static String showListOfTasks(TaskList tasks) {
        ArrayList<Task> listOfTasks = tasks.getList();
        String output = "Here's what I have for you:\n";
        for (int i = 0; i < listOfTasks.size(); i++) {
            output += (i + 1) + ". " + listOfTasks.get(i).toString() + "\n";
        }
        return output;
    }

    public static String showUnmark() {
        return "Okay! I'll set the task as not done. \n₍ ˃ ⤙ ˂ ₎\n";
    }

    public static String showMark() {
        return "Alright~ I'll set the task as done! \n₍ ˶ᵔ ᵕ ᵔ˶ ₎\n";
    }

    public static String showAdd() {
        return "Done and ready to go~ I've added this task for ya!\n";
    }

    public static String showDelete() {
        return "Here you go! I've deleted this task for ya.\n";
    }

    public static String showFind() {
        return "Here's what I found for ya\n";
    }

    public static String showLoadingError() {
        return "I can't seem to load the file \n˶> _ <˶ \n";
    }
}
