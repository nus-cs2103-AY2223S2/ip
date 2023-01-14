import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String divider = "    ————————————————————————————————";
        System.out.println(divider);
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(divider);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            String command = sc.nextLine();
            System.out.println(divider);
            boolean addTask = true;
            if (command.equals("list")) {
                System.out.println(divider);
                int counter = 1;
                for (Task t : tasks) {
                    System.out.println(counter + ". " + t.toString());
                    counter++;
                }
                addTask = false;
            } else if (command.matches("^mark \\d")) {
                String[] input = command.split(" ");
                int taskNumber = Integer.parseInt(input[1]) - 1;
                tasks.get(taskNumber).setCompleted();
                System.out.println("    Nice! I've marked this task as done:\n" + "   " + tasks.get(taskNumber));
                addTask = false;
            } else if (command.matches("^unmark \\d")) {
                String[] input = command.split(" ");
                int taskNumber = Integer.parseInt(input[1]) - 1;
                tasks.get(taskNumber).setUncompleted();
                System.out.println("    OK, I've marked this task as not done yet:\n" + "   " + tasks.get(taskNumber));
                addTask = false;
            } else if (command.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(divider);
                sc.close(); // close scanner
                return;
            }
            if (addTask) {
                tasks.add(new Task(command));
                System.out.println("    added: " + command);
            }
            System.out.println(divider);
        }
    }
}
