package duke;

import java.util.ArrayList;

/**
 * Class responsible for executing user's commands
 */
public class Parser {
    /**
     * Takes in user's input and executes respective command.
     * @param splitInput User's input in the form of a String array
     */
    public static String parseInput(String[] splitInput) {
        ArrayList<Task> array = TaskList.getList();
        String combined = String.join(" ", splitInput);
        if (combined.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return "bye";
        } else if (combined.equals("list")) {
            //Displays the list of items
            return TaskList.displayList(array);
        } else if (splitInput[0].equalsIgnoreCase("mark")) {
            //Mark the task as done
            return TaskList.markTask(array, splitInput);
        } else if (splitInput[0].equalsIgnoreCase("unmark")) {
            //Unmark the task as done
            return TaskList.unmarkTask(array, splitInput);
        } else if (splitInput[0].equalsIgnoreCase("deadline")) {
            //Creates a Deadline type Task
            return Deadline.formatForDeadline(array, splitInput);
        } else if (splitInput[0].equalsIgnoreCase("event")) {
            //Creates an Event type Task
            return Event.formatForEvent(array, splitInput);
        } else if (splitInput[0].equalsIgnoreCase("todo")) {
            //Creates a Todo type Task
            return Todo.createTodoTask(array, splitInput);
        } else if (splitInput[0].equalsIgnoreCase("delete")) {
            //Deletes a Task
            return TaskList.deleteTask(array, splitInput);
        } else if (splitInput[0].equalsIgnoreCase("find")) {
            //Finds a Task
            return TaskList.searchTask(array, splitInput);
        } else if (splitInput[0].equalsIgnoreCase("help")) {
            //Displays help message
            return Ui.helpMessage();
        } else {
            try {
                throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        String error = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        error += "Type 'help' to see a list of commands";
        return error;

    }
}
