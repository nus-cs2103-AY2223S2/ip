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
        String inputString = rawInput;
        Command command = Command.TODO;

        try (Scanner scanner = new Scanner(rawInput)) {
            if (scanner.hasNext()) {
                command = Command.valueOf(scanner.next().toUpperCase());

                if (scanner.hasNext()) {
                    inputString = scanner.nextLine().strip();
                }
            }
        } catch (IllegalArgumentException noElmEx) {
            command = Command.TODO;
        }

        if (command.equals(Command.BYE)) {
            this.isExit = true;
        }

        try {
            CommandInput input = CommandInput.parse(inputString, manager);
            msg = command.execute(input);
        } catch (IllegalArgumentException illArgEx) {
            return illArgEx.toString();
        }

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
