import java.util.Scanner;

public class Duke {
    private static void printText(String text) {
        System.out.printf("     %s\n", text);
    }

    private static void printHorizontal() {
        System.out.println("    ____________________________________________________________");
    }

    private static void printStartup() {
        String logo =
                " /\\_/\\\n" +
                "( o.o )   ~meow~\n" +
                " > ^ <";
        System.out.println(logo);
        printHorizontal();
        printText("Hello! I'm Duke");
        printText("What can I do for you?");
        printHorizontal();
    }

    private static void commandEcho(String echoString) {
        printHorizontal();
        printText(echoString);
        printHorizontal();
    }

    private static void commandExit() {
        printHorizontal();
        printText("Bye. Hope to see you again soon!");
        printHorizontal();
    }

    public static void main(String[] args) {
        printStartup();

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String command = scanner.nextLine();
            switch (command) {
                case "bye":
                    commandExit();
                    isExit = true;
                    break;
                default:
                    commandEcho(command);
            }
        }
    }
}
