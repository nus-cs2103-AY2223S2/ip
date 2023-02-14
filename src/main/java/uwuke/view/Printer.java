package uwuke.view;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import uwuke.model.Task;

/**
 * Helper class that prints out everything in required format to System.out
 */
public class Printer {

    private static VBox dialogContainer; 

    public static void setDialogContainer(VBox dialogContainer) {
        Printer.dialogContainer = dialogContainer;
    }

    /**
     * Prints the string with some OwO's
     * 
     * @param input message that should be displayed
     */
    public static void printWithDecorations(String input) {
        StringBuilder sb = new StringBuilder("OwO OwO OwO OwO OwO OwO\n");
        sb.append(input);
        sb.append("\nOwO OwO OwO OwO OwO OwO");
        dialogContainer.getChildren().add(DialogBox.getDukeDialogBox(new Label(sb.toString())));
    }

    /**
     * Prints a boring welcome message
     */
    public static void printWelcome() {
        String welcomeString = 
                "Hello from\n"
                +" ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        
        printWithDecorations(welcomeString);
    }

    /**
     * Prints out a giant uwu
     */
    public static void uwu() {
        String uwu =
        "Hewwo from\n"+
        "_\\/\\\\\\_______\\/\\\\\\__\\/\\\\\\_____________\\/\\\\\\__\\/\\\\\\_______\\/\\\\\\_       \n"+
        "__/\\\\\\________/\\\\\\___/\\\\\\______________/\\\\\\___/\\\\\\________/\\\\\\_       \n"+ 
        " _\\/\\\\\\_______\\/\\\\\\__\\/\\\\\\_____________\\/\\\\\\__\\/\\\\\\_______\\/\\\\\\_      \n"+
        "  _\\/\\\\\\_______\\/\\\\\\__\\//\\\\\\____/\\\\\\____/\\\\\\___\\/\\\\\\_______\\/\\\\\\_     \n"+
        "   _\\/\\\\\\_______\\/\\\\\\___\\//\\\\\\__/\\\\\\\\\\__/\\\\\\____\\/\\\\\\_______\\/\\\\\\_    \n"+
        "    _\\/\\\\\\_______\\/\\\\\\____\\//\\\\\\/\\\\\\/\\\\\\/\\\\\\_____\\/\\\\\\_______\\/\\\\\\_   \n"+
        "     _\\//\\\\\\______/\\\\\\______\\//\\\\\\\\\\\\//\\\\\\\\\\______\\//\\\\\\______/\\\\\\__  \n"+
        "      __\\///\\\\\\\\\\\\\\\\\\/________\\//\\\\\\__\\//\\\\\\________\\///\\\\\\\\\\\\\\\\\\/___ \n"+
        "       ____\\/////////___________\\///____\\///___________\\/////////_____\n";
       
        printWithDecorations(uwu);
    }

    /**
     * Prints the goodbye message
     */
    public static void printBye() {
        String bye = "Bye. Hope to see you again soon!";
        printWithDecorations(bye);
    }

    /**
     * Prints out all the tasks that the user currently has stored in the list.
     * Runtime grows linearly with total number of characters in all tasks.
     * 
     * @param tasks list of all tasks
     */
    public static void printTasks(ArrayList<Task> tasks) {
        String s;

        if (tasks.size() <= 0) {
            s = "No task found! Please add a task.";
        } else if (tasks.size() == 1) {
            s = "Here is the task in your list:\n";
        } else {
            s = "Here are the tasks in your list:\n";
        }
        
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d.%s\n", i+1, tasks.get(i).toString()));
        }
        printWithDecorations(sb.toString());
    }

    /**
     * Prints out a confirmation message when a task is added successfully.
     * Should only be called after the actual add operation.
     * 
     * @param task task that was added
     * @param numTasks number of tasks after adding this new task
     */
    public static void printAddedConfirmation(Task task, int numTasks) {
        String grammar = numTasks <= 1 ? "task" : "tasks";
        String s = String.format("Got it. I've added this task:\n   %s\nNow you have %d %s in the list.", task.toString(), numTasks, grammar);
        printWithDecorations(s);
    }

    /**
     * Prints out a warning message when list has reach it's capacity
     */
    public static void printNotEnoughSpace() {
        printWithDecorations("You have added too many tasks!");
    }

    /**
     * Prints out an error message.
     * Should only be used when an error is encountered.
     *
     * @param errorMessage description of the error
     */
    public static void printError(String errorMessage) {
        printWithDecorations(errorMessage);
    }

    /**
     * Prints out a confirmation when a task is deleted successfully.
     * Should only be used after the actual delete operation.
     * 
     * @param task task that was just deleted
     * @param numTasksAfterDelete number of tasks after deleting the task
     */
    public static void printDeleteConfirmation(Task task, int numTasksAfterDelete) {
        String grammar = numTasksAfterDelete == 1 ? "task" : "tasks";
        String s = String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.", 
                                 task.toString(), 
                                 numTasksAfterDelete, 
                                 grammar);
        printWithDecorations(s);    
    }

    public static void printFindResults(ArrayList<Task> matchingTasks) {
        String s;

        if (matchingTasks.size() <= 0) {
            s = "No task found with the given keyword! Please choose a different keyword.";
        } else if (matchingTasks.size() == 1) {
            s = "Here is a matching task in your list:\n";
        } else {
            s = "Here are the matching tasks in your list:\n";
        }
        
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(String.format("%d.%s\n", i+1, matchingTasks.get(i).toString()));
        }
        printWithDecorations(sb.toString());
    }
    
}
