import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String indentation = "    ";
    private static final String horizontalLines = "____________________________________________________________";
    private static final String newLine = indentation + horizontalLines;
    private static ArrayList<Task> arrOfTask = new ArrayList<>();

    public static void echo(String command) {
        System.out.println(indentation + command);
    }

    public static void exit() {
        System.out.println(indentation + "Bye. Hope to see you again soon!");
    }

    public static void greet() {
        System.out.println(newLine);
        System.out.println(indentation + "Hello! I'm Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(newLine);
    }

    public static void addTask(String command) {
        Task t;
        if (command.startsWith("todo")) {
            t = new Todo(command);
        } else if (command.startsWith("deadline")) {
            String[] str = command.substring(9).split("/");
            t = new Deadline(str[0], str[1].substring(3));
        } else if (command.startsWith("event")) {
            String[] str = command.substring(6).split("/");
            t = new Event(str[0], str[1].substring(5), str[2].substring(3));
        } else {
            t = new Task(command);
        }
        arrOfTask.add(t);
        System.out.println(indentation + "Got it. I've added this task:");
        System.out.println(indentation + t);
        totalItems();
    }

    public static void list() {
        System.out.println(indentation + "Here are the tasks in your list:");
        for (Task t : arrOfTask) {
            System.out.println(indentation + t.getIndexOfTask() + "." + t);
        }
    }

    public static void taskDone(int index) {
        Task t = arrOfTask.get(index - 1);
        t.taskDone();
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + t);
    }

    public static void taskNotDone(int index) {
        Task t = arrOfTask.get(index - 1);
        t.taskNotDone();
        System.out.println(indentation + "OK, I've marked this task as not done yet:");
        System.out.println(indentation + t);
    }

    public static void totalItems() {
        System.out.println(indentation + "Now you have " + Task.getTotalNumOfTask() + " tasks in the list.");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        while (true) {
            String command = sc.nextLine();
            System.out.println(newLine);
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
            System.out.println(newLine);
        }
        System.out.println(newLine);
    }
}
