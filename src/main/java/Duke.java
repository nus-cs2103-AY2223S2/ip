import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList(new ArrayList<Task>());



        horizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello there! I am: \n" + logo + "\nWhat can I help you with!");
        System.out.println("You can let me know by typing it below, please!");
        horizontalLine();

        String userInput = scanner.nextLine();



        while (!userInput.equals("bye")) {
            String[] userInputComponents = userInput.split(" ");

            if (userInput.equals("list")) {
                horizontalLine();
                list.printItems();
                horizontalLine();
                userInput = scanner.nextLine();
                continue;
            } else if (userInputComponents[0].equals("mark") ||
                       userInputComponents[0].equals("unmark")) {
                String taskStatus = userInputComponents[0];
                int taskNumber = Integer.parseInt(userInputComponents[1]);
                if (taskStatus.equals("mark")) {
                    horizontalLine();
                    list.markDone(taskNumber);
                    horizontalLine();
                } else if (taskStatus.equals("unmark")) {
                    horizontalLine();
                    list.markUndone(taskNumber);
                    horizontalLine();
                }
                userInput = scanner.nextLine();
                continue;
            }
            list.addTask(new Task(userInput));
            horizontalLine();
            System.out.println("    added: " + userInput);
            horizontalLine();
            userInput = scanner.nextLine();
        }

            System.out.println("Bye for now! Hope to see you again!");

    }

    public static void horizontalLine() {


        for (int i = 0; i < 50; i++) {
            char horizontalBar = '\u2015';
            System.out.print(horizontalBar);

        }


        System.out.print("\n");

    }
}
