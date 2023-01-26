import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ui {
    protected static ArrayList<String> commandList = new ArrayList<>(Arrays.asList
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
                boolean validCommand = commandList.contains(inputArr[0]);
                if (!validCommand) {
                    throw new InvalidCommandException();
                }
                p.handleInput(input, records);
            }catch (EmptyInputException | InvalidCommandException exception) {
                System.out.println("Input Error, please input a command within the list:" +
                        "todo, deadline, event, mark, unmark, list, delete, bye.");
            }
        }
    }

    public static String format(String input) {
        return "____________________________________________________________\n" +
                input +
                "\n____________________________________________________________";
    }

    public void greet() {
        String greeting = format("if it isn't your favourite astronaut lawyer doctor plumber cleaner, Johnny Sins."
                + "\n Ready to go on a self-exploration adventure?");
        System.out.println(greeting);
    }
}
