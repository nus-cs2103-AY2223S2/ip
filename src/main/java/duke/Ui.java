package duke;

import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected final static String SEPARATOR = "\t____________________________________________________________";
    private Scanner sc;

    /**
     * Contructor for the Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Reads next command inputted by user.
     *
     * @return
     */

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints welcome message to user of Duke.
     */
    public void welcomeMessage() {
        System.out.println(logo
                + "\n"
                + SEPARATOR
                + "\n\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + SEPARATOR);
    }

    /**
     * Prints bye message to user of Duke.
     */
    public void byeMessage() {
        System.out.println(SEPARATOR
                + "\n\t Bye. Hope to see you again soon!\n"
                + SEPARATOR);
    }

    /**
     * Outputs mark message to user.
     *
     * @param task Task to be marked Done.
     */
    public void showMark(Task task) {
        System.out.println(SEPARATOR + "\n\tNice! I've marked this task as done:\n\t  " + task.toString()
                + "\n" + SEPARATOR);
    }

    /**
     * Outputs unmark message to user.
     *
     * @param task Task to be marked Undone.
     */
    public void showUnmark(Task task) {
        System.out.println(SEPARATOR + "\n\tOK, I've marked this task as not done yet:\n\t  " + task.toString()
                + "\n" + SEPARATOR);
    }

    /**
     * Oututs add message to user.
     *
     * @param task Task to be added.
     * @param size Size of the tasklist
     */
    public void showAdd(Task task, int size) {
        System.out.println(SEPARATOR + "\n\tGot it. I've added this task:\n\t  "
                + task.toString() + "\n\tNow you have "
                + size + " task(s) in the list.\n"
                + SEPARATOR);
    }

    /**
     * Outputs deleted message to user.
     *
     * @param task Task to be deleted.
     * @param size Size of the tasklist.
     */
    public void showDelete(Task task, int size) {
        System.out.println(SEPARATOR + "\n\tNoted. I've removed this task:\n\t  "
                + task.toString() + "\n\tNow you have "
                + size + " task(s) in the list.\n"
                + SEPARATOR);
    }

    /**
     * Outputs the taskList to user.
     *
     * @param taskList TaskList which contains the list of tasks.
     */
    public void showList(TaskList taskList) {
        System.out.print(SEPARATOR);
        taskList.printList();
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the list of tasks found using the FindCommand.
     *
     * @param listOfTasksFound ArrayList consisting of strings of tasks found using FindCommand.
     */
    public void printFindList(ArrayList<String> listOfTasksFound) {
        int i = 1;
        System.out.println(SEPARATOR + "\n\tHere are the matching tasks in your list:");
        for (String string : listOfTasksFound) {
            System.out.println("\t"
                    + i
                    + ". "
                    + string);
            i++;
        }
        System.out.println("\tThere are "
                + listOfTasksFound.size()
                + " matching task(s) in your list."
                + "\n"
                + SEPARATOR);
    }

    /**
     * Outputs the data stored in the local filepath.
     *
     * @param storage Storage contains the filepath where data is saved locally.
     */
    public void showListFromStorage(Storage storage) {
        String filepath = storage.getFilepath();
        if (!Files.exists(Path.of(filepath))) {
            new File(filepath).getParentFile().mkdirs();
        } else {
            File file = new File(filepath);
            try {
                Scanner sc = new Scanner(file);
                System.out.println("\tThese is your current TaskList");
                while (sc.hasNextLine()) {
                    String text = sc.nextLine();
                    System.out.println("\t" + text.trim());
                }
                System.out.println(SEPARATOR);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Outputs error message to user.
     *
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        System.out.println(SEPARATOR
                + "\n"
                + errorMessage
                + "\n"
                + SEPARATOR);
    }
}
