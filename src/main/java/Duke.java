import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String indentation = "     ";
    private static final String newLine = "    ____________________________________________________________";
    private static ArrayList<Task> arrOfTask = new ArrayList<>();
    public enum Action {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        LIST,
        DELETE,
        BYE
    }

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

    public static void addTask(Task t) {
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
        if (index > Task.getTotalNumOfTask()) {
            throw new InvalidIndexException("Index too large");
        }
        Task t = arrOfTask.get(index - 1);
        t.taskDone();
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + t);
    }

    public static void taskNotDone(int index) {
        if (index > Task.getTotalNumOfTask()) {
            throw new InvalidIndexException("Index too large");
        }
        Task t = arrOfTask.get(index - 1);
        t.taskNotDone();
        System.out.println(indentation + "OK, I've marked this task as not done yet:");
        System.out.println(indentation + t);
    }

    public static void deleteTask(int index) {
        if (index > Task.getTotalNumOfTask()) {
            throw new InvalidIndexException("Index too large");
        }
        Task t = arrOfTask.get(index - 1);
        System.out.println(indentation + "Noted. I've removed this task:");
        System.out.println(indentation + t);
        Task.decreaseNumOfTask();
        totalItems();
    }

    public static void totalItems() {
        System.out.println(indentation + "Now you have " + Task.getTotalNumOfTask() + " tasks in the list.");
    }

    public static Action getAction(String command) {
        if (command.equals("bye")) {
            return Action.BYE;
        } else if (command.equals("list")) {
            return Action.LIST;
        } else if (command.startsWith("mark")) {
            return Action.MARK;
        } else if (command.startsWith("unmark")) {
            return Action.UNMARK;
        } else if (command.startsWith("delete")) {
            return Action.DELETE;
        } else if (command.startsWith("todo")) {
            return Action.TODO;
        } else if (command.startsWith("deadline")) {
            return Action.DEADLINE;
        } else if (command.startsWith("event")) {
            return Action.EVENT;
        } else {
            throw new InvalidCommandException("Incorrect command");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        while (true) {
            try {
                String command = sc.nextLine();
                System.out.println(newLine);
                Action action = getAction(command);
                if (action == Action.BYE) {
                    exit();
                    break;
                }
                switch (action) {
                    case LIST:
                        list();
                        break;
                    case MARK:
                        taskDone(Integer.parseInt(command.substring(5)));
                        break;
                    case UNMARK:
                        taskNotDone(Integer.parseInt(command.substring(7)));
                        break;
                    case DELETE:
                        deleteTask(Integer.parseInt(command.substring(7)));
                        break;
                    case TODO:
                        addTask(new Todo(command.substring(5)));
                        break;
                    case DEADLINE:
                        String[] str1 = command.substring(9).split("/");
                        addTask(new Deadline(str1[0], str1[1].substring(3)));
                        break;
                    case EVENT:
                        String[] str2 = command.substring(6).split("/");
                        addTask(new Event(str2[0], str2[1].substring(5), str2[2].substring(3)));
                }
            } catch (InvalidCommandException e1) {
                System.out.println(indentation + e1);
            } catch  (InvalidIndexException e2) {
                System.out.println(indentation + e2);
            } catch (IndexOutOfBoundsException e3) {
                System.out.println(indentation + "☹ OOPS!!! The description of a task cannot be empty.");
            } finally {
                System.out.println(newLine);
            }
        }
    }
}
