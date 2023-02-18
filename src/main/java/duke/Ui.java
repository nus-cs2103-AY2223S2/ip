package duke;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import task.Task;

import java.util.Scanner;

/**
 * This class handles all the printing of the User Interface.
 * @author Bryan Ong
 */
public class Ui {

    private static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    @FXML
    private static TextField userInput;
    private static Scanner sc = new Scanner(System.in);

    /**
     * This method reads the command entered.
     * @return String This returns the command line
     */
    public static String readCommand() {
        return userInput.getText();
    }

    /**
     * This method prints the task information.
     * @param info to be printed.
     */
    public static String print(int num, String info) {
        return (num + ". " + info);
    }
    /**
     * This method prints the farewell message
     */
    public static String printBye() {
        return(DIVIDER + "Ah..... okkkk nehmind. GO. BYE. :)\n" + DIVIDER);
    }

    /**
     * This method prints the message when a Task has
     * been added.
     * @param task Task object added.
     */
    public static String printDefault(Task task) {
        return (DIVIDER + "Aite letsgetit you added:\n" + task.toString()  +
                "currently you have " + task.numberTask() + " tasks\n" + DIVIDER);
    }

    /**
     * This method prints the message when a Task has
     * been deleted.
     * @param task Task object to be deleted.
     */
    public static String printDelete(Task task) {
        return(DIVIDER + "Alright, deleted task:\n" + task
                + "\n" + task.numberTask() + " tasks left!\n" + DIVIDER);
    }

    /**
     * This method prints the divider for user interface design.
     */
    public static String printDivider() {
        return(DIVIDER);
    }

    /**
     * This method prints when user enters more than one word
     * when using find function.
     */
    public static String printEnterKeyword() {
        return("Please enter a keyword");
    }

    /**
     * This method prints the message for found keywords.
     */
    public static String printFind() {
        return(DIVIDER + "Aye this is what i found according to your keyword:\n");
    }

    public static String printFindNot() {
        return(DIVIDER + "Hey cant find any task with that keyword man.\n");
    }

    /**
     * This method prints the message for first time
     * initialization of Duke program.
     */
    public static String printInit() {
        return(DIVIDER + "Welcome hooman!\nCome add some tasks today!\n" + DIVIDER);
    }

    /**
     * This method prints the message for an
     * empty list upon list command.
     */
    public static String printListEmpty() {
        return(DIVIDER + "List is empty.......\n" + DIVIDER);
    }

    /**
     * This method prints the message for a
     * non-empty list upon list command
     */
    public static String printListPrompt() {
        return(DIVIDER + "Hurry up and finish these tasks:\n");
    }

    /**
     * This method prints the message upon mark command.
     * @param task Task that has been marked.
     */
    public static String printMark(Task task) {
        return(DIVIDER + "Congrats this has been done:\n"
            + task + "\n Leskooo!\n" + DIVIDER);
    }

    /**
     * This method prints the error message when
     * Todo command is incomplete.
     */
    public static String printTodoError() {
        return("What do u need to do ah? u never write.");
    }

    /**
     * This method prints the message upon unmark command.
     * @param task Task that has been unmarked.
     */
    public static String printUnmark(Task task) {
        return(DIVIDER + "Alright, new task:\n" + task
                + "\nWe can do dis!\n" + DIVIDER);
    }

    /**
     * This method prints the message to welcome
     * user back to Duke.
     */
    public static String printWelcomeBack() {
        String welcome = "Welcome back hooman!\n" +
                "Wat u want to do today?\n";
        return(DIVIDER + welcome + DIVIDER);
    }

    /**
     * This method prints the error message when
     * user inputs a commmand not recognised
     */
    public static String printWrongCommand() {
        return(DIVIDER + "I have no idea what is going on, try again?\n" + DIVIDER);
    }

    /**
     * This method prints the error message when
     * user inputs a number less than 0 or more than
     * what exists for marking, unmarking and deleting commands.
     */
    public static String printWrongNumber() {
        return("Number entered out of range, try again");
    }

    public static String printMissingName() {
        return("No name identified, try again\n");
    }

    public static String printUndoSuccess() {
        return(DIVIDER + "Undone!\n" + DIVIDER);
    }

    public static String printUndoFail() {
        return("There is nothing to undo :)\n");
    }
}
