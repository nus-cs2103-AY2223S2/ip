package duke.functions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyInputException;
import duke.exceptions.InvalidCommandException;
import duke.tasks.TaskList;

/**
 * Main UI class that controls the formatting and display of output to CLI.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T
 */
public class Ui {
    public static ArrayList<String> commandList = new ArrayList<>(Arrays.asList(
            "todo", "deadline", "event", "mark", "unmark", "list", "bye", "delete", "find", "help"));

    /**
     * Main method to start interaction between user and interface.
     *
     * @param records TaskList passed down from duke.functions.Duke main class.
     */
    public void run(TaskList records) {
        greet();
        Scanner userInput = new Scanner(System.in);
        Parser p = new Parser(records);
        while (true) {
            try {
                String input = userInput.nextLine();
                //Check for empty input (pressing enter)
                if (input == "") {
                    throw new EmptyInputException();
                }
                String[] inputArr = input.split(" ", 2);
                //Check for invalid commands (first word)
                boolean isValidCommand = commandList.contains(inputArr[0]);
                if (!isValidCommand) {
                    throw new InvalidCommandException();
                }
                p.handleInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Auxiliary static method to format text outputs to the command line.
     *
     * @param input String to be formatted.
     * @return String object to be written to the command line.
     */
    public static String format(String input) {
        return input;
    }

    /**
     * Method to display greeting string to the command line.
     *
     */
    public void greet() {
        String greeting = format("if it isn't your favourite astronaut lawyer doctor plumber cleaner, Johnny Sins."
                + "\n Ready to go on a self-exploration adventure?"
                + "\n For more information on the commands available, type help.");
        System.out.println(greeting);
    }
    /**
     * Method to display help string to the command line.
     *
     * @return Help string.
     */
    public static String help() {
        String help = "The available commands are todo, deadline, event, mark, unmark, list, bye, delete, find, help.\n"
                + "For more information on the usage of the commands, please type usage.";
        return help;
    }
    /**
     * Method to display a detailed help string to the command line.
     *
     * @return Detailed help string.
     */
    public static String helpDetailed() {
        String help = format("Hi."
                    + "The commands available are: \n"
                    + "todo task - add a task to your todo list\n"
                    + "deadline task /by time - add a task with deadline\n"
                    + "event task /from time /to time - add an event\n"
                    + "mark 1 - marks first item in the list as done\n"
                    + "unmark 5 - marks 5th item in the list as undone\n"
                    + "list - shows all tasks in the list\n"
                    + "delete 1 - deletes first task from the list\n"
                    + "find task - finds all tasks containing the name\n"
                    + "bye - exit this application."
        );
        return help;
    }
}
