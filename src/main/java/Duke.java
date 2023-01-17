import java.util.Scanner;

import command.Command;
import command.CommandInput;
import manager.MainManager;

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

        MainManager manager = new MainManager();

        System.out.println(formatMessage(Command.GREET.execute(CommandInput.parse("", manager))));

        try (Scanner scanner = new Scanner(System.in)) {
            while (!isExit) {
                String message;
                try {
                    message = execute(scanner.nextLine(), manager);
                } catch (IllegalArgumentException illArgEx) {
                    message = String.format(
                        "Hanya?? I was unable to process that...\n" +
                        "Please try again after fixing this:\n" +
                        "  %s",
                        illArgEx.getMessage()
                    );
                }
                System.out.println(formatMessage(message));
            }
        }
    }


    private String execute(String rawInput, MainManager manager) throws IllegalArgumentException {
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
            throw new IllegalArgumentException("Unknown command");
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
