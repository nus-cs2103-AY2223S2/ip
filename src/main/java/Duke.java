import java.util.Scanner;

public class Duke {

    private static final String LINE = "------------------------------------------------------------";

    private static final Task[] TASKS = new Task[100];

    private static void listTasks() {
        if (Task.getTotalTasks() == 0) {
            System.out.println("You do not have any tasks added to the list.");
        } else {
            System.out.println("Listing all tasks...");
            for (int i = 0; i < Task.getTotalTasks(); i++) {
                System.out.println((i + 1) + ") " + TASKS[i]);
            }
        }
    }

    private static void processCommand(String command) {

        boolean toAdd = false;

        if (command.equals("list")) {
            listTasks();

        } else {
            String[] cmdParts = command.split(" ");

            if (cmdParts.length == 2) {
                try {
                    int taskNumber = Integer.parseInt(cmdParts[1]);
                    boolean isValidTaskNumber =
                            (taskNumber > 0 && taskNumber <= Task.getTotalTasks());

                    if (cmdParts[0].equals("mark")) {

                        if (isValidTaskNumber) {
                            TASKS[taskNumber - 1].mark();
                            System.out.println("I have marked Task " + taskNumber + " as done.");
                            System.out.println(TASKS[taskNumber - 1]);

                        } else {
                            System.out.println("Invalid command. Sorry :/");
                        }


                    } else if (cmdParts[0].equals("unmark")) {

                        if (isValidTaskNumber) {
                            TASKS[taskNumber - 1].unmark();
                            System.out.println("I have marked Task " + taskNumber + " as undone.");
                            System.out.println(TASKS[taskNumber - 1]);

                        } else {
                            System.out.println("Invalid command. Sorry :/");
                        }

                    } else {
                        toAdd = true;
                    }

                } catch (NumberFormatException e) {
                    toAdd = true;
                }

            } else {
                toAdd = true;
            }

        }

        if (toAdd) {
            TASKS[Task.getTotalTasks()] = new Task(command);
            System.out.println("I have added the task '" + command + "' to the list.");
        }

        System.out.println("\n" + LINE + "\n");
    }

    private static void start() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("You:\n");
            String command = sc.nextLine().strip().toLowerCase();

            System.out.println("\nDuke:");

            if (command.equals("bye")) {
                System.out.println("Bye. Have a nice day!\n");
                System.out.println(LINE);
                break;

            } else {
                processCommand(command);
            }

        }

        sc.close();

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\nHello from\n" + logo);

        System.out.println("How can I help you?\n");
        System.out.println(LINE + "\n");

        start();

    }
}
