import java.util.Scanner;

import aqua.exception.IllegalSyntaxException;
import aqua.manager.AppManager;
import command.Command;
import command.CommandInput;

public class Duke {
    private static String SEPARATOR = "____________________________________________________________";

    private boolean isExit = false;


    public static void main(String[] args) {
        new Duke().start();
    }


    private void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        AppManager manager = new AppManager();

        try {
            System.out.println(formatMessage(Command.GREET.execute(CommandInput.parse("", manager))));
        } catch (IllegalSyntaxException dukeIllArgEx) {
            System.out.println(formatMessage(String.format(
                "Failed to greet...\n%s",
                dukeIllArgEx.toString()
            )));
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (!isExit) {
                String message;
                try {
                    message = execute(scanner.nextLine(), manager);
                } catch (IllegalSyntaxException dukeIllArgEx) {
                    message = String.format(
                        "Hanya?? I was unable to process that...\n" +
                        "Please try again after fixing this:\n" +
                        "  %s",
                        dukeIllArgEx.getMessage()
                    );
                }
                System.out.println(formatMessage(message));
            }
        }
    }


    private String execute(String rawInput, AppManager manager) throws IllegalSyntaxException {
        String msg = rawInput;
        String inputString = "";
        Command command;

        try (Scanner scanner = new Scanner(rawInput)) {
            if (scanner.hasNext()) {
                command = Command.valueOf(scanner.next().toUpperCase());

                if (scanner.hasNext()) {
                    inputString = scanner.nextLine().strip();
                }
            } else {
                return "Hanya?? Did you say something?";
            }
        } catch (IllegalArgumentException illArgEx) {
            throw new IllegalSyntaxException("Unknown command");
        }

        if (command.equals(Command.BYE)) {
            this.isExit = true;
        }

        CommandInput input = CommandInput.parse(inputString, manager);
        msg = command.execute(input);

        return msg;
    }


    private String formatMessage(String msg) {
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(msg)) {
            while (scanner.hasNextLine()) {
                builder.append(String.format("\t  %s\n", scanner.nextLine()));
            }
        }
        return String.format("\t%s\n%s\t%s\n",
            SEPARATOR, builder.toString(), SEPARATOR
        );
    }
}
