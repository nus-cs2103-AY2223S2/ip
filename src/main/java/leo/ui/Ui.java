package leo.ui;

import leo.parser.Parser;
import leo.task.LeoTaskException;
import leo.task.Task;

import java.util.ArrayList;

/**
 * Class that handles the user interface.
 */
public class Ui {
    /**
     * Prints the greeting message.
     */
    public void greetUser() {
        printDivider();
        System.out.println("Hello! I'm Leo");
        System.out.println("What can I do for you?");
        printDivider();
    }

    /**
     * Returns a user greeting message.
     * @return User greeting String
     */
    public static String greetUserGUI() {
        return String.format("Hello! I'm Leo\nWhat can I do for you?\n\n");
    }

    public static String exitGUI() {
        return "It was nice talking, see you soon!\n";
    }

    /**
     * Prints a divider.
     */
    public static void printDivider() {
        for (int i = 0; i < 25; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    /**
     * Prints Leo's response with dividers.
     * @param response
     */

    public static void printResponse(String response) {
        printDivider();
        System.out.println(response);
        printDivider();
    }

    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are your tasks, you legend!:\n");
        printDivider();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d) %s\n", i + 1, tasks.get(i));
        }
        printDivider();
    }

    public static void printListWithIndices(ArrayList<Task> foundTasks, ArrayList<Integer> foundTaskIndices) {
        if (foundTasks.isEmpty()) {
            Ui.printResponse("You've been caught offside my friend, no tasks found!");
            return;
        }
        printDivider();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.printf("%d) %s\n", foundTaskIndices.get(i), foundTasks.get(i));
        }
        System.out.println("To perform any action on these tasks, use the stated indices.");
        printDivider();
    }

    public static String getList(ArrayList<Task> tasks) {
        String response = "Here are your tasks, you legend!:\n\n";
        for (int i = 0; i < tasks.size(); i++) {
            response += String.format("%d) %s\n", i + 1, tasks.get(i));
        }
        return response;
    }

    public static String getListWithIndices(ArrayList<Task> foundTasks, ArrayList<Integer> foundTaskIndices) {
        if (foundTasks.isEmpty()) {
            return "You've been caught offside my friend, no tasks found!";
        }
        String response = "Here are the matching tasks in your list:\n\n";
        for (int i = 0; i < foundTasks.size(); i++) {
            response += String.format("%d) %s\n", foundTaskIndices.get(i), foundTasks.get(i));
        }
        response += "\nTo perform any action on these tasks, use the stated indices.\n";
        return response;
    }

    public static String getMarkMessage(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).setDone();
        return String.format("Well done on completing the task! Let me mark that as done! Campeon del mundo!\n%s\n", tasks.get(taskNumber));
    }

    public static String getUnmarkMessage(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).setNotDone();
        return String.format("Ok, I've marked that as not done! Please get to it :(\n%s\n", tasks.get(taskNumber));
    }





    /**
     * Prints custom exceptions (LeoTaskException) thrown by Leo.
     * @param e
     */

    public static void printError(LeoTaskException e) {
        printDivider();
        System.out.println(e.getMessage());
        printDivider();
    }

    /**
     * Prints exit message.
     */
    public void printExit() {
        printDivider();
        System.out.println("It was nice talking, see you soon!");
        printDivider();
    }

    public static String getHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the commands you can use:\n\n");
        sb.append("list: lists all tasks\n");
        sb.append("todo <description>: adds a todo task\n");
        sb.append("deadline <description> /by <date>: adds a deadline task\n");
        sb.append("event <description> /at <date>: adds an event task\n");
        sb.append("mark <task number>: marks a task as done\n");
        sb.append("unmark <task number>: marks a task as not done\n");
        sb.append("delete <task number>: deletes a task\n");
        sb.append("find <keyword>: finds tasks with the keyword\n");
        sb.append("help: gives you a list of supported commands\n");
        sb.append("bye: exits the program\n");
        return sb.toString();
    }

    public static void printHelp() {
        Ui.printResponse(Ui.getHelp());
    }

}
