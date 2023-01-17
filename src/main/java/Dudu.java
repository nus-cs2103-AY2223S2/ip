import java.util.ArrayList;
import java.util.Scanner;
public class Dudu {
    private static final String DIVIDER = "________________________________\n";
    private static final String LOGO =
              " ____  _   _ ____  _   _ \n"
            + "|  _ \\| | | |  _ \\| | | |\n"
            + "| | | | | | | | | | | | |\n"
            + "| |_| | |_| | |_| | |_| |\n"
            + "|____/ \\___/|____/ \\___/\n";
    private static final String GREETING = DIVIDER + "Hello from\n" + LOGO + "What can I do for you?\n" + DIVIDER;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println(GREETING);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ");
            System.out.println(DIVIDER);
            if (inputArr[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputArr[0].equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    Task currTask = list.get(i);
                    System.out.println(i+1 + ".[" + currTask.getStatusIcon() + "] " + currTask.getName());
                }
            } else if (inputArr[0].equals("mark")) {
                int index = Integer.parseInt(inputArr[1]) -1;
                Task currTask = list.get(index);
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + currTask.getStatusIcon() + "] " + currTask.getName());
            } else if (inputArr[0].equals("unmark")) {
                int index = Integer.parseInt(inputArr[1]) -1;
                Task currTask = list.get(index);
                currTask.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + currTask.getStatusIcon() + "] " + currTask.getName());
            } else {
                Task task = new Task(input);
                list.add(task);
                System.out.println("added: " + task.getName());
            }
            System.out.println(DIVIDER);
        }
    }

}
