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
        for (Task t : arrOfTask) {
            System.out.println(indentation + t.getIndexOfTask() + ". " + t.getNameOfTask());
        }
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
            } else {
                addTask(command);
            }
        }
    }
}
