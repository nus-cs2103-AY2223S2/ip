import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Dudu {
    private static final String DIVIDER = "___________________________________________\n";
    private static final String LOGO =
              " ____  _   _ ____  _   _ \n"
            + "|  _ \\| | | |  _ \\| | | |\n"
            + "| | | | | | | | | | | | |\n"
            + "| |_| | |_| | |_| | |_| |\n"
            + "|____/ \\___/|____/ \\___/\n";
    private static final String GREETING = DIVIDER + "Hello from\n" + LOGO + "What can I do for you?\n" + DIVIDER;
    private static void addTask(String type, ArrayList<Task> list, String input) {
        Task task;
        if (type.equals("deadline")) {
            String[] inputStr = input.substring(9).split(" /by ");
            task = new Deadline(inputStr[0], inputStr[1]);
        } else if (type.equals("todo")) {
            task = new Todo(input.substring(5));
        } else {
            String[] inputStr = input.substring(6).split(" /from ");
            String[] dateStr = inputStr[1].split(" /to ");
            task = new Event(inputStr[0],dateStr[0],dateStr[1]);
        }
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println(getTotalTask(list));
    }
    private static String getTotalTask(ArrayList<Task> tasks) {
        String secondHalf;
        if (tasks.size() == 1) {
            secondHalf = " task in the list.";
        } else {
            secondHalf = " tasks in the list.";
        }
        return "Now you have " + tasks.size() + secondHalf;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println(GREETING);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ");
            System.out.print(DIVIDER);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputArr[0].equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    Task currTask = list.get(i);
                    System.out.println(i+1 + "." + currTask);
                }
            } else if (inputArr[0].equals("mark")) {
                int index = Integer.parseInt(inputArr[1]) -1;
                Task currTask = list.get(index);
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currTask);
            } else if (inputArr[0].equals("unmark")) {
                int index = Integer.parseInt(inputArr[1]) -1;
                Task currTask = list.get(index);
                currTask.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(currTask);
            } else if (inputArr[0].equals("deadline")) {
//                String[] inputStr = input.substring(9).split(" /by ");
//                Deadline task = new Deadline(inputStr[0], inputStr[1]);
//                list.add(task);
//                System.out.println("Got it. I've added this task:");
//                System.out.println(" " + task);
//                System.out.println(getTotalTask(list));
                addTask("deadline", list, input);
            } else if (inputArr[0].equals("todo")) {
//                Todo task = new Todo(input.substring(5));
//                list.add(task);
//                System.out.println("Got it. I've added this task:");
//                System.out.println(" " + task);
//                System.out.println(getTotalTask(list));
                addTask("todo", list, input);
            } else if (inputArr[0].equals("event")) {
//                String[] inputStr = input.substring(6).split(" /from ");
//                String[] dateStr = inputStr[1].split(" /to ");
//                Event task = new Event(inputStr[0],dateStr[0],dateStr[1]);
//                list.add(task);
//                System.out.println("Got it. I've added this task:");
//                System.out.println(" " + task);
//                System.out.println(getTotalTask(list));
                addTask("event", list, input);
            } else {
                Task task = new Task(input);
                list.add(task);
                System.out.println("added: " + task.getName());
            }
            System.out.print(DIVIDER);
        }
    }

}
