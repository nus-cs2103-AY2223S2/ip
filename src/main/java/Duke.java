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
                System.out.println(formatMessage(execute(scanner.nextLine(), manager)));
            }
        }
    }


    private String execute(String rawInput, MainManager manager) {
        String msg = rawInput;
        try (Scanner scanner = new Scanner(rawInput)) {
            if (scanner.hasNext()) {
                Command command;
                try {
                    command = Command.valueOf(scanner.next().toUpperCase());
                } catch (IllegalArgumentException illArgEx) {
                    return msg;
                }

                if (command.equals(Command.BYE)) {
                    isExit = true;
                }

                CommandInput input = CommandInput.parse("", manager);
                if (scanner.hasNext()) {
                    try {
                        input = CommandInput.parse(msg, manager);
                    } catch (IllegalArgumentException illArgEx) {
                        return msg;
                    }
                }

                try {
                    msg = command.execute(input);
                } catch (IllegalArgumentException illArgEx) {
                    return msg;
                }
            }
        }
        return msg;
    }


    private String formatMessage(String msg) {
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(msg)) {
            while (scanner.hasNextLine()) {
                builder.append("\t" + scanner.nextLine());
            }
        }
        return String.format("\t%s\n%s\n\t%s\n",
            SEPARATOR, builder.toString(), SEPARATOR
        );
    }
}
