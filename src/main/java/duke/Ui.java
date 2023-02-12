package duke;

import duke.task.Task;

/**
 * This class deals with any response printing for the UI
 *
 * @author He Shuimei
 * @version 0
 */
public class Ui {
    public final String ERROR_DELETE_TASK = "Error: Item does not exist";
    public final String ERROR_EMPTY_TODO = "Please input something TO DO????!!";
    public final String ERROR_UNKNOWN_COMMAND = "Error: Unknown command, please try again";

    /*
     * Deletes task
     *
     * @param task, task to be deleted
     * @param size, size of list
     */
    void printDelete(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("SENDING TASK TO THE VOID (DELETING)");
        System.out.println("\t" + task);
        System.out.println("You currently have " + size + " tracked tasks");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints successful add task
     *
     * @param curr single Task object
     * @param size int size of list
     */
    void printNotif(Task curr, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Me add your task to list: ");
        System.out.println("\t" + curr);
        System.out.println("You currently have " + size + " tracked tasks");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the initial start-up message
     */
    void printASCII(){
        String line = " ---------------------------------------------------------";
        String logo = "\t\t\t\t            | |       \n" +
                "\t\t\t\t _ __  _   _| | _____ \n" +
                "\t\t\t\t| '_ \\| | | | |/ / _ \\\n" +
                "\t\t\t\t| |_) | |_| |   <  __/\n" +
                "\t\t\t\t| .__/ \\__,_|_|\\_\\___|\n" +
                "\t\t\t\t| |                   \n" +
                "\t\t\t\t|_|                  ";
        System.out.println(line + "\n" + logo + "\n" + line);
        System.out.println("Welcome to PUKE, the worst program in existence");
        System.out.println("Input a command");
    }

    /**
     * Prints exception message
     *
     * @param e exception encountered
     */
    void printException(Exception e) {
        System.out.println("Encountered exception: " + e + "\nExiting program");
    }
}
