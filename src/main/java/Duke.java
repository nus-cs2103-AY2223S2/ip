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
        Task[] taskList = new Task[MAX_TASK];
        int taskCounter = 0;

        // use while loop to deal with user input through scanner
        untilBye:
        while (true) {
            String msgDescription = in.nextLine();
            String[] splitMessage = msgDescription.split(" ");
            switch (splitMessage[0]) {
                case "bye":
                    printByeMessage();
                    break untilBye;
                case "list":
                    Task.printTaskList(taskList);
                    break;
                default:
                    printMessage(msgDescription);
                    taskList[taskCounter] = new Task(msgDescription);
                    taskCounter++;
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

    public static void printMessage(String msgDescription) {
        Task.printHorizontalLine();
        System.out.println("     added: " + msgDescription);
        Task.printHorizontalLine();
    }
}
