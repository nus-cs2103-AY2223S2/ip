import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * The main class to run the Duke App.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        operate();
    }

    private static void greet() {
        String name = "SOCCat";
        String greeting = String.format("Hello, I'm %s", name);
        String cta = "How meow I help you today?";
        DukeFormatter.section(
                greeting + "\n"
                + cta +"\n"
        );
    }

    private static void cleanup(Scanner sc) {
        sc.close();
    }

    private static void parseCommand(String cmd, DukeStore ds, Scanner sc) {
        String[] tokens = cmd.split(" ", 2);
        if (tokens.length == 1) {
            operateSimpleCommand(tokens[0], ds, sc);
            return;
        }

        twoArgCommand(tokens[0], tokens[1], ds);
    }

    private static void operateSimpleCommand(String cmd, DukeStore ds, Scanner sc) {
        switch (cmd) {
            case "bye":
                cleanup(sc);
                String message = "It was nice serving you. Hope to see you again!" + "\n"
                        + "Exiting...";
                DukeFormatter.section(message);
                System.exit(1);

            case "list":
                DukeFormatter.section(ds.toString());
                return;

            default:
                DukeFormatter.error(new Exception(
                        "Please try a valid command."
                ));
        }
    }

    private static void twoArgCommand(String cmd, String param, DukeStore ds) {
        switch (cmd) {
            case "mark":
                try {
                    int i = Integer.parseInt(param);
                    mark(i, ds);
                } catch (Exception e) {
                    DukeFormatter.error(new Exception(
                            "Invalid task number entered. Ensure it is a number."
                    ));
                }
                return;
            case "unmark":
                try {
                    int i = Integer.parseInt(param);
                    unMark(i, ds);
                } catch (Exception e) {
                    DukeFormatter.error(new Exception(
                            "Invalid task number entered. Ensure it is a number."
                    ));
                }
                return;
            case "todo":
                todo(param, ds);
                return;
            case "deadline":
                deadline(param, ds);
                return;
            case "event":
                event(param, ds);
                return;

            default:
                DukeFormatter.error(new Exception(
                        "Please try a valid command."
                ));
        }
    }

    private static void mark(int idx, DukeStore ds) {
        try {
            ds.mark(idx - 1);
        } catch (DukeStoreInvalidAccessException e) {
            DukeFormatter.error(e);
        }
    }

    private static void unMark(int idx, DukeStore ds) {
        try {
            ds.unMark(idx - 1);
        } catch (DukeStoreInvalidAccessException e) {
            DukeFormatter.error(e);
        }
    }

    private static void todo(String task, DukeStore ds) {
        TaskTodo todo = new TaskTodo(task);
        try {
            ds.add(todo);
        } catch (DukeStoreFullException e) {
            DukeFormatter.error(e);
        }
    }

    private static void deadline(String command, DukeStore ds) {
        String[] tokens = command.split(" /by ", 2);
        if (tokens.length != 2) {
            System.out.println(Arrays.toString(tokens));
            DukeFormatter.error(new Exception(
                    "Usage: deadline {task} /by {deadline}"
            ));
            return;
        }

        TaskDeadline deadline = new TaskDeadline(tokens[0], tokens[1]);
        try {
            ds.add(deadline);
        } catch (DukeStoreFullException e) {
            DukeFormatter.error(e);
        }
    }

    private static void event(String command, DukeStore ds) {
        String[] tokens = command.split(" /from ", 2);
        if (tokens.length != 2) {
            DukeFormatter.error(new Exception(
                    "Usage: event {task} /from {start} /to {end}"
            ));
            return;
        }
        String[] dates = tokens[1].split(" /to ", 2);
        if (dates.length != 2) {
            DukeFormatter.error(new Exception(
                    "Usage: event {task} /from {start} /to {end}"
            ));
            return;
        }

        TaskEvent event = new TaskEvent(tokens[0], dates[0], dates[1]);
        try {
            ds.add(event);
        } catch (DukeStoreFullException e) {
            DukeFormatter.error(e);
        }
    }

    private static void operate() {
        DukeStore store = DukeStore.create();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            parseCommand(command, store, sc);
        }
    }
}
