package duke.functions;

import duke.exceptions.DateTimeFormatException;
import duke.exceptions.DukeException;
import duke.exceptions.EmptyInputException;
import duke.exceptions.InvalidCommandException;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Ui {
    public static ArrayList<String> commandList = new ArrayList<>(Arrays.asList
            ("todo", "deadline", "event", "mark", "unmark", "list", "bye", "delete"));
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
            }catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String format(String input) {
        String s = "____________________________________________________________";
        return String.format("%s\n%s\n%s", s, input, s);
    }

    public void greet() {
        String greeting = format("if it isn't your favourite astronaut lawyer doctor plumber cleaner, Johnny Sins."
                + "\n Ready to go on a self-exploration adventure?");
        System.out.println(greeting);
    }
}
