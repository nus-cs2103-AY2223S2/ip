package duke.helper;

import duke.tasks.*;


import duke.exceptions.InvalidTaskDescriptionException;

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
     */
    public static void run(String input, String[] command, TaskList tasks) {
        try {
            if (command[0].equals("list")) {
                tasks.listTasks();
            } else if (command[0].equals("mark")) {
                tasks.mark(command);
            } else if (command[0].equals("unmark")) {
                tasks.unmark(command);
            } else if (command[0].equals("todo")) {
                tasks.addTask(input);
            } else if (command[0].equals("deadline")) {
                tasks.addDeadline(input);
            } else if (command[0].equals("event")) {
                tasks.addEvent(input);
            } else if (command[0].equals("delete")) {
                tasks.deleteTask(command);
            } else {
                System.out.println("Invalid command wake up brother");
            }
        } catch (InvalidTaskDescriptionException e) {
            System.out.println(e.getMessage());
        }


    }
}

