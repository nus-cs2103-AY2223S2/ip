import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // Length of horizontal line
    private static final int HORIZONTAL_LINE_LENGTH = 80;

    public static void printHorizontalLine() {
        for (int i = 0; i < HORIZONTAL_LINE_LENGTH; i++) {
            System.out.print("\u2500");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        // Greeting
        printHorizontalLine();
        System.out.println(
            "Karen:\n" +
            "Can I speak to your manager?\n" +
            "Just kidding, this is Karen. How can I help you today?"
        );
        printHorizontalLine();

        // Commands
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            printHorizontalLine();
            System.out.println("Karen:");

            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task currTask = tasks.get(i);
                    System.out.printf("%d.[%s] %s\n", i + 1, currTask.getStatusIcon(), currTask.getDescription());
                }
            } else if (command.contains("mark") || command.contains("unmark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                String action = command.split(" ")[0];

                // check if task number is valid
                if (taskNumber > tasks.size() || taskNumber <= 0) {
                    System.out.println("Can you please get your eyes checked?");
                } else {
                    Task currTask = tasks.get(taskNumber - 1);

                    if (action.equals("mark")) {
                        currTask.markAsDone();
                        System.out.printf(
                            "Congrats, I guess you get a medal?\n" +
                            "[%s] %s\n", currTask.getStatusIcon(), currTask.getDescription()
                        );
                    } else if (action.equals("unmark")) {
                        currTask.markAsNotDone();
                        System.out.printf(
                            "Why are you so lazy?\n" +
                            "[%s] %s\n", currTask.getStatusIcon(), currTask.getDescription()
                        );
                    }
                }
            } else {
                tasks.add(new Task(command));
                System.out.printf("added: %s\n", command);
            }

            printHorizontalLine();
            command = sc.nextLine();
        }

        // Exit
        printHorizontalLine();
        System.out.println("Karen:\n" + "Bye. This was of great inconvenience.");
        printHorizontalLine();
    }
}
