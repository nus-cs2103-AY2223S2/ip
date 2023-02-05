package duke.helper;

import duke.exceptions.InvalidTaskDescriptionException;
import duke.tasks.TaskList;



//this is the class that parses commands

/**
 * Helper Class to parse and execute commands from user.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class Parser {

    /**
     * Function that parses through user input and executes associated command.
     *
     * @param input Raw string containing user input.
     * @param command Partially broken down input to make sense of commands.
     * @param tasks The list of tasks from the user.
     * @return String that denotes message to be displayed by ChadGPT
     */
    public static String run(String input, String[] command, TaskList tasks) {
        try {
            if (command[0].equals("list")) {
                return tasks.listTasks();
            } else if (command[0].equals("mark")) {
                return tasks.mark(command);
            } else if (command[0].equals("unmark")) {
                return tasks.unmark(command);
            } else if (command[0].equals("todo")) {
                return tasks.addTask(input);
            } else if (command[0].equals("deadline")) {
                return tasks.addDeadline(input);
            } else if (command[0].equals("event")) {
                return tasks.addEvent(input);
            } else if (command[0].equals("delete")) {
                return tasks.deleteTask(command);
            } else if (command[0].equals("find")) {
                return tasks.findTask(command[1]);
            } else {
                return "Invalid command wake up brother";
            }
        } catch (InvalidTaskDescriptionException e) {
            return e.getMessage();
        }




    }
}

