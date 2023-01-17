import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Dudu {
    private static final String DIVIDER = "___________________________________________\n";
    private static final String LOGO =
              " ____  _   _ ____  _   _\n"
            + "|  _ \\| | | |  _ \\| | | |\n"
            + "| | | | | | | | | | | | |\n"
            + "| |_| | |_| | |_| | |_| |\n"
            + "|____/ \\___/|____/ \\___/\n";
    private static final String GREETING = DIVIDER + LOGO + "Hello! I'm Dudu\n" + "What can I do for you?\n" + DIVIDER;
    private static void addTask(String type, ArrayList<Task> list, String input) throws EmptyDescriptionException {
        Task task;
        if (type.equals("deadline")) {
            if (input.length() == 8) {
                throw new EmptyDescriptionException(type, "Missing task description");
            }
            String[] inputStr = input.substring(9).split(" /by ");
            task = new Deadline(inputStr[0], inputStr[1]);
        } else if (type.equals("todo")) {
            if (input.length() == 4) {
                throw new EmptyDescriptionException(type, "Missing task description");
            }
            task = new Todo(input.substring(5));
        } else {
            if (input.length() == 5) {
                throw new EmptyDescriptionException(type, "Missing task description");
            }
            String[] inputStr = input.substring(6).split(" /from ");
            String[] dateStr = inputStr[1].split(" /to ");
            task = new Event(inputStr[0],dateStr[0],dateStr[1]);
        }
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println(getTotalTask(list));
    }
    private static String getTotalTask(ArrayList<Task> tasks) {
        String secondHalf;
        if (tasks.size() <= 1) {
            secondHalf = " task in the list.";
        } else {
            secondHalf = " tasks in the list.";
        }
        return "Now you have " + tasks.size() + secondHalf;
    }
    private static Task getTask(ArrayList<Task> tasks, int index) throws TaskNumRangeException {
        if (index >= tasks.size() || index < 0) {
            throw new TaskNumRangeException(String.valueOf(index));
        }
        return tasks.get(index);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.print(GREETING);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inputArr[0].equals("list")) {
                if (list.size() == 0) {
                    System.out.println("There is no task in your list");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        Task currTask = list.get(i);
                        System.out.println(i + 1 + "." + currTask);
                    }
                }
            } else if (inputArr[0].equals("delete")) {
                int index = Integer.parseInt(inputArr[1]) -1;
                try {
                    Task currTask = getTask(list, index);
                    list.remove(currTask);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + currTask);
                    System.out.println(getTotalTask(list));
                } catch (TaskNumRangeException ex) {
                    System.out.println(ex);
                }
            } else if (inputArr[0].equals("mark")) {
                int index = Integer.parseInt(inputArr[1]) -1;
                try {
                    Task currTask = getTask(list, index);
                    currTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currTask);
                } catch (TaskNumRangeException ex) {
                    System.out.println(ex);
                }
            } else if (inputArr[0].equals("unmark")) {
                int index = Integer.parseInt(inputArr[1]) -1;
                try {
                    Task currTask = getTask(list, index);
                    currTask.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currTask);
                } catch (TaskNumRangeException ex) {
                    System.out.println(ex);
                }
            } else if (inputArr[0].equals("deadline")) {
                try {
                    addTask("deadline", list, input);
                } catch (EmptyDescriptionException ex) {
                    System.out.println(ex);
                }
            } else if (inputArr[0].equals("todo")) {
                try {
                    addTask("todo", list, input);
                } catch (EmptyDescriptionException ex) {
                    System.out.println(ex);
                }
            } else if (inputArr[0].equals("event")) {
                try {
                    addTask("event", list, input);
                } catch (EmptyDescriptionException ex) {
                    System.out.println(ex);
                }
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.print(DIVIDER);
        }
        System.out.print(DIVIDER);
    }

}
