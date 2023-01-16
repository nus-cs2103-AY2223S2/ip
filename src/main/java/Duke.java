import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String indentation = "    ";
    private static final String horizontalLines = "____________________________________________________________";
    private static final String newLine = indentation + horizontalLines;
    private static ArrayList<Task> arrOfTask = new ArrayList<>();

    public static void echo(String command) {
        System.out.println(newLine);
        System.out.println(indentation + command);
        System.out.println(newLine);
    }

    public static void exit() {
        System.out.println(newLine);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(newLine);
    }

    public static void greet() {
        System.out.println(newLine);
        System.out.println(indentation + "Hello! I'm Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(newLine);
    }

    public static void addTask(String command) {
        System.out.println(newLine);
        arrOfTask.add(new Task(command));
        System.out.println(indentation + "added: " + command);
        System.out.println(newLine);
    }

    public static void list() {
        System.out.println(newLine);
        System.out.println("Here are the tasks in your list:");
        for (Task t : arrOfTask) {
            System.out.println(indentation + t.getIndexOfTask() + "." + t);
        }
        System.out.println(newLine);
    }

    public static void taskDone(int index) {
        System.out.println(newLine);
        Task t = arrOfTask.get(index - 1);
        t.taskDone();
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + t);
        System.out.println(newLine);
    }

    public static void taskNotDone(int index) {
        System.out.println(newLine);
        Task t = arrOfTask.get(index - 1);
        t.taskNotDone();
        System.out.println(indentation + "OK, I've marked this task as not done yet:");
        System.out.println(indentation + t);
        System.out.println(newLine);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")) {
                list();
            } else if (command.startsWith("mark")) {
                taskDone(Integer.parseInt(command.substring(5)));
            } else if (command.startsWith("unmark")) {
                taskNotDone(Integer.parseInt(command.substring(7)));
            } else {
                addTask(command);
            }
        }
    }
}
