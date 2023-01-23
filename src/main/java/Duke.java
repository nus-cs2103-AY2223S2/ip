import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static TaskList taskList = new TaskList();
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        System.out.println("Hello! I am Duke Nice To Meet You\n");
        boolean ongoing = true;

        while(ongoing) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! Hope to See You Again!");
                break;
            }

            if (command.contains("mark") || command.contains("unmark")) {
                if (command.substring(0, 4).equals("mark")) {
                    taskList.markTask(Integer.parseInt(command.substring(5)));
                    continue;
                }

                if (command.substring(0, 6).equals("unmark")) {
                    taskList.unmarkTask(Integer.parseInt(command.substring(7)));
                    continue;
                }
            }

            if (command.equals("list")) {
                taskList.printTasks();
                continue;
            }

            Task task = new Task(command);
            taskList.add(task);
        }
    }
}
