package duke;

import java.util.ArrayList;

/**
 * The Ui class handles the user interface and printing of messages to the console.
 */
public class Ui {
    /**
     * Displays the initial greeting when duke is launched.
     * @return The greeting
     */
    public static String getGreeting() {
        //Initial greeting
        String greet = "Hello! I'm Broccoli the dinosaur \n";
        greet += "                      <|°▿▿▿▿°|/ \n";
        greet += "      What can I do for you?";
        greet += " ‧˚₊•-----------୨୧-----------•‧₊˚⊹";
        greet += " input 'help' for a list of commands";
        return greet;
    }
    /**
     * Displays the list of tasks in a formatted string.
     *
     * @param array The ArrayList of tasks to be displayed.
     * @return The formatted string of the list of tasks.
     */
    public static String displayList(ArrayList<Task> array) {
        String list = "";
        for (int j = 0; j < array.size(); j++) {
            list += "      " + (j + 1) + "." + array.get(j).toString() + "\n";
        }
        return list;
    }
    /**
     * A formatted string confirming the addition of a Task.
     *
     * @param array The ArrayList of tasks to which the task will be added.
     * @param t The task to be added to the list.
     * @return A formatted string confirming the addition of the task to the list.
     */
    static String addTask(ArrayList<Task> array, Task t) {
        String output = "";
        output += "     Got it. I've added this task:" + "\n";
        output += "     " + t.toString() + "\n";
        output += "     Now you have " + array.size() + " tasks in the list.";
        return output;
    }
    /**
     * A formatted string confirming the removal of a task from the list.
     *
     * @param array The ArrayList of tasks from which the task will be removed.
     * @param splitInput The user's input for removing a task.
     * @return A formatted string confirming the removal of the task from the list.
     */
    static String removeTask(ArrayList<Task> array, String[] splitInput) {
        String output = "";
        output += "      Noted. I've removed this task:" + "\n";
        output += "      " + array.get((Integer.parseInt(splitInput[1]) - 1)).toString() + "\n";
        output += "      Now you have " + array.size() + " tasks in the list." + "\n";
        return output;
    }
    /**
     * A formatted string confirming the task is marked as done.
     *
     * @param array The ArrayList of tasks to which the task will be marked as done.
     * @param splitInput The user's input for marking a task as done.
     * @return A formatted string confirming the task has been marked as done.
     */
    static String markTask(ArrayList<Task> array, String[] splitInput) {
        String output = "";
        output += "      Nice! I've marked this task as done:" + "\n";
        output += "      " + array.get((Integer.parseInt(splitInput[1]) - 1)).toString();
        return output;
    }
    /**
     * A formatted string confirming the task is marked as not done.
     *
     * @param array The ArrayList of tasks to which the task will be marked as not done.
     * @param splitInput The user's input for marking a task as not done.
     * @return A formatted string confirming the task has been marked as not done.
     */
    static String unmarkTask(ArrayList<Task> array, String[] splitInput) {
        String output = "";
        output += "      OK, I've marked this task as not done yet:";
        output += "      " + array.get((Integer.parseInt(splitInput[1]) - 1)).toString();
        return output;
    }

    /**
     * @return help message
     */
    public static String helpMessage() {
        String output = "";
        output += "•────────•°•❀•°•────────•" + "\n";
        output += "Hello! Looks like you need help." + "\n";
        output += "Here's a list of my commands" + "\n";
        output += "✤ todo <task> - Adds a todo task to the list" + "\n";
        output += "✤ deadline <task> /by <date> - Adds a deadline task to the list" + "\n";
        output += "✤ event <task> /at <date> - Adds an event task to the list" + "\n";
        output += "✤ list - Displays the list of tasks" + "\n";
        output += "✤ mark <task number> - Marks a task as done" + "\n";
        output += "✤ unmark <task number> - Marks a task as not done" + "\n";
        output += "✤ delete <task number> - Deletes a task from the list" + "\n";
        output += "✤ find <keyword> - Finds tasks with the keyword" + "\n";
        output += "✤ bye - Exits the program" + "\n";
        output += "✤ help - Displays this message" + "\n";
        output += "•────────•°•❀•°•────────•" + "\n";
        output += "Cool fact!" + "\n";
        output += "if your <date> is in the format YYYY-MM-DD it will be saved in MMM d yyyy format" + "\n";
        output += "Your tasks list will be saved even if you exit the program" + "\n";
        output += "Hope this helps! Try it now!!!!!" + "\n";
        return output;
    }
}

