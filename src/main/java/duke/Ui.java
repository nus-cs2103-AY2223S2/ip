package duke;

import task.Task;

import java.util.Dictionary;
import java.util.Scanner;

/**
 * This class handles all the printing of the User Interface.
 * @author Bryan Ong
 */
public class Ui {

    private static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    private static Scanner sc = new Scanner(System.in);

    /**
     * This method reads the command entered.
     * @return String This returns the command line
     */
    public static String readCommand() {
        return sc.nextLine();
    }

    /**
     * This method prints the task information.
     * @param String to be printed.
     */
    public static void print(int num, String info) {
        System.out.print(num + ". " + info);
    }
    /**
     * This method prints the farewell message
     */
    public void printBye() {
        System.out.println(DIVIDER + "Ah..... okkkk nehmind. GO. BYE. :)\n" + DIVIDER);
    }

    /**
     * This method prints the message when a Task has
     * been added.
     * @param task Task object added.
     */
    public static void printDefault(Task task) {
        System.out.println(DIVIDER + "Aite letsgetit you added:\n" + task.toString()  +
                "currently you have " + task.numberTask() + " tasks\n" + DIVIDER);
    }

    /**
     * This method prints the message when a Task has
     * been deleted.
     * @param task Task object to be deleted.
     */
    public static void printDelete(Task task) {
        System.out.println(DIVIDER + "Alright, deleted task:\n" + task
                + "\n" + task.numberTask() + " tasks left!\n" + DIVIDER);
    }

    /**
     * This method prints the divider for user interface design.
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * This method prints when user enters more than one word
     * when using find function.
     */
    public static void printEnterKeyword() {
        System.out.println("Please enter only one keyword:");
    }

    /**
     * This method prints the message for found keywords.
     */
    public static void printFind() {
        System.out.println(DIVIDER + "Aye this is what i found according to your keyword:\n");
    }

    public static void printFindNot() {
        System.out.println(DIVIDER + "Hey cant find any task with that keyword man.");
    }

    /**
     * This method prints the message for first time
     * initialization of Duke program.
     */
    public static void printInit() {
        System.out.println(DIVIDER + "Welcome hooman!\nCome add some tasks today!\n" + DIVIDER);
    }

    /**
     * This method prints the message for an
     * empty list upon list command.
     */
    public static void printListEmpty() {
        System.out.println(DIVIDER + "List is empty.......\n" + DIVIDER);
    }

    /**
     * This method prints the message for a
     * non-empty list upon list command
     */
    public static void printListPrompt() {
        System.out.println(DIVIDER + "Hurry up and finish these tasks:");
    }

    /**
     * This method prints the message upon mark command.
     * @param task Task that has been marked.
     */
    public static void printMark(Task task) {
        System.out.println(DIVIDER + "Congrats this has been d:\n"
            + task + "\n down, Leskooo!\n" + DIVIDER);
    }

    /**
     * This method prints the error message when
     * Todo command is incomplete.
     */
    public static void printTodoError() {
        System.out.println("What do u need to do ah? u never write.");
    }

    /**
     * This method prints the message upon unmark command.
     * @param task Task that has been unmarked.
     */
    public static void printUnmark(Task task) {
        System.out.println(DIVIDER + "Alright, new task:\n" + task
                + "\nWe can do dis!\n" + DIVIDER);
    }

    /**
     * This method prints the message to welcome
     * user back to Duke.
     */
    public static void printWelcomeBack() {
        String welcome = "Welcome back hooman!\n" +
                "Wat u want to do today?\n";
        System.out.println(DIVIDER + welcome + DIVIDER);
    }

    /**
     * This method prints the error message when
     * user inputs a commmand not recognised
     */
    public void printWrongCommand() {
        System.out.println(DIVIDER + "I have no idea what is going on, try again?\n" + DIVIDER);
    }

    /**
     * This method prints the error message when
     * user inputs a number less than 0 or more than
     * what exists for marking, unmarking and deleting commands.
     */
    public static void printWrongNumber() {
        System.out.println("Number entered out of range, type the number again");
    }

}
