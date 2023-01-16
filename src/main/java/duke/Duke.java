package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandResponse;
import duke.command.EchoCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the chatbot
 */
public class Duke {
    private final static ByeCommand byeCommand = new ByeCommand();
    private final static EchoCommand echoCommand = new EchoCommand();

    private static List<String> tasks = new ArrayList<String>();

    /**
     * Main method for the chatbot
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        printGreeting();
        runInputLoop();
    }

    private static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printMessage(String.format("Hello from\n%s\nWhat can I do for you?", logo));
    }

    private static void printMessage(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("     %s\n", message.replace("\n", "\n     "));
        System.out.print("    ____________________________________________________________\n\n");
    }

    private static void runInputLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            CommandResponse res = getCommand(input).run(input, tasks);

            tasks = res.getTasks();
            printMessage(res.getMessage());

            if (input.equals("bye")) {
                break;
            }
        }
    }

    private static Command getCommand(String input) {
        if (input.equals("bye")) {
            return byeCommand;
        } else {
            return echoCommand;
        }
    }
}
