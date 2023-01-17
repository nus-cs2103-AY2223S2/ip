import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    cleanup(sc);
                    String message = "It was nice serving you. Hope to see you again!";
                    DukeFormatter.section(message);
                    System.exit(1);
                default:
                    DukeFormatter.section(command);
            }
        }
    }
}
