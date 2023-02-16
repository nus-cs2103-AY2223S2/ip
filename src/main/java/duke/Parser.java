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
        }
        //Displays the list of items
        if (combined.equals("list")) {
            return TaskList.displayList(array);
        }
        //Mark the task as done
        else if (splitInput[0].equals("mark")) {
            return TaskList.markTask(array, splitInput);
        }
        //Unmark the task as done
        else if (splitInput[0].equals("unmark")) {
            return TaskList.unmarkTask(array, splitInput);
        }
        //Creates a Deadline type Task
        else if (splitInput[0].equals("deadline")) {
            return Deadline.createDeadlineTask(array, splitInput);
        }
        //Creates an Event type Task
        else if (splitInput[0].equals("event")) {
            return Event.createEventTask(array, splitInput);
        }
        //Creates a Todo type Task
        else if (splitInput[0].equals("todo")) {
            return Todo.createTodoTask(array, splitInput);
        }
        //Deletes a Task
        else if (splitInput[0].equals("delete")) {
            return TaskList.deleteTask(array, splitInput);
        }
        //Finds a Task
        else if (splitInput[0].equals("find")) {
            return TaskList.searchTask(array, splitInput);
        } else {
            try {
                throw new IllegalArgumentException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return "error";
    }
}
