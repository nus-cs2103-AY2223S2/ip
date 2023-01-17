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

    private static void operate() {
        DukeStore store = DukeStore.create();

        Consumer<String> mark = v -> {
            String[] tokens = v.split(" ");
            if (tokens.length != 2) {
                DukeFormatter.error(new Exception(
                        "Usage: mark {task_number}"
                ));
                return;
            }

            int idx;
            try {
                idx = Integer.parseInt(tokens[1]);
            } catch (Exception e) {
                DukeFormatter.error(new Exception(
                        "Invalid task number entered. Ensure it is a number."
                ));
                return;
            }

            try {
                store.mark(idx - 1);
            } catch (DukeStoreInvalidAccessException e) {
                DukeFormatter.error(e);
            }
        };

        Consumer<String> unMark = v -> {
            String[] tokens = v.split(" ");
            if (tokens.length != 2) {
                DukeFormatter.error(new Exception(
                        "Usage: unmark {task_number}"
                ));
            }
            int idx;

            try {
                idx = Integer.parseInt(tokens[1]);
            } catch (Exception e) {
                DukeFormatter.error(new Exception(
                        "Invalid task number entered. Ensure it is a number."
                ));
                return;
            }

            try {
                store.unMark(idx - 1);
            } catch (DukeStoreInvalidAccessException e) {
                DukeFormatter.error(e);
            }
        };

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    cleanup(sc);
                    String message = "It was nice serving you. Hope to see you again!" + "\n"
                            + "Exiting...";
                    DukeFormatter.section(message);
                    System.exit(1);

                case "list":
                    DukeFormatter.section(store.toString());
                    continue;


                default:
                   if (command.startsWith("mark")) {
                       mark.accept(command);
                       continue;
                   } else if (command.startsWith("unmark")) {
                       unMark.accept(command);
                       continue;
                   }

                    try {
                        store.add(command);
                        DukeFormatter.section("added: " + command);
                    } catch (DukeStoreFullException e) {
                        DukeFormatter.error(e);
                    }
            }
        }
    }
}
