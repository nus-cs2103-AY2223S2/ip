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
    public static ArrayList<String> commandList = new ArrayList<>(Arrays.asList
            ("todo", "deadline", "event", "mark", "unmark", "list", "bye", "delete", "find"));

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
        String s = "____________________________________";
        return String.format("%s\n%s\n%s", s, input, s);
    }

    /**
     * Method to display greeting string to the command line.
     *
     */
    public void greet() {
        String greeting = format("if it isn't your favourite astronaut lawyer doctor plumber cleaner, Johnny Sins."
                + "\n Ready to go on a self-exploration adventure?");
        System.out.println(greeting);
    }
}
