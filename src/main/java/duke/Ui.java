package duke;

import java.util.Scanner;

/**
 * This class deals with interactions with the user
 */
public class Ui {
    private Scanner sc;

    /**
     * Gets the next input
     * @return the next input from user
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out the list of tasks
     */
    public void showList() {
        System.out.println("Here are the tasks in your list:\n");
        int listcount = 1;
        for (Task element: Task.tasks) {
            if (element != null) {
                System.out.println("" + listcount + "." + element);
                listcount += 1;
            }
        }
    }

    /**
     * Prints error
     * @param e
     */
    public void printError(String e) {
        System.out.println(e + "\n");
    }
}
