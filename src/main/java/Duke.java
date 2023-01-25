import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();



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
                list.getTaskDetails();
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
                    list.getTaskDetails();
                    horizontalLine();
                } else if (taskStatus.equals("unmark")) {
                    horizontalLine();
                    list.markUndone(taskNumber);
                    list.getTaskDetails();
                    horizontalLine();
                }
                userInput = scanner.nextLine();
                continue;
            } else if (userInputComponents[0].equals("todo")) {
                horizontalLine();
                list.addTask(new ToDo(userInput.substring(5)));
                list.getTaskDetails();
                horizontalLine();
                userInput = scanner.nextLine();
                continue;
            } else if (userInputComponents[0].equals("deadline")) {
                String[] splitDeadline = userInput.split("/");
                String description = splitDeadline[0].substring(9);
                String deadline = splitDeadline[1];
                horizontalLine();
                list.addTask(new Deadline(description, deadline));
                list.getTaskDetails();
                horizontalLine();
                userInput = scanner.nextLine();
                continue;

            } else if (userInputComponents[0].equals("event")) {
                String[] splitTimes = userInput.split("/");
                String description = splitTimes[0].substring(6);
                String startDayTime = splitTimes[1];
                String endDayTime = splitTimes[2];
                horizontalLine();
                list.addTask(new Event(startDayTime, endDayTime, description));
                list.getTaskDetails();
                horizontalLine();
                userInput = scanner.nextLine();
                continue;
            }

            horizontalLine();
            list.addTask(new Task(userInput));
            list.getTaskDetails();
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
