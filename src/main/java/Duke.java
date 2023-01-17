import java.util.Scanner;

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
                    try {
                        store.add(command);
                        DukeFormatter.section("added: " + command);
                    } catch (DukeStoreFullException e) {
                        DukeFormatter.section("\u001B[31m" + e.getMessage() + "\u001B[0m");
                    }
            }
        }
    }
}
