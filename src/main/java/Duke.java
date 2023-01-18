import java.util.Scanner;

public class Duke {
    public static final int MAX_TASK = 1000;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASK];

        untilBye:
        while (true) {
            String msgDescription = in.nextLine();
            switch (msgDescription) {
                case "bye":
                    printByeMessage();
                    break;
                default:
                    printTaskMessage(msgDescription);
                    break ;
            }
        }

    }
    public static void printByeMessage() {
        Task.printHorizontalLine();
        System.out.println("     Bye. Hope to see you again soon!");
        Task.printHorizontalLine();
    }

    public static void printWelcomeMessage() {
        Task.printHorizontalLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        Task.printHorizontalLine();
    }

    public static void printTaskMessage(String description) {
        Task.printHorizontalLine();
        System.out.println(description);
        Task.printHorizontalLine();
    }
}
