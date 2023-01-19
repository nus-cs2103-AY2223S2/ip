import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    private static String DIV_OPEN = "____________________________________________________________\n";
    private static String DIV_CLOSE = "____________________________________________________________\n";

    public static ArrayList<Task> list = new ArrayList<Task>();
    public static int listNum = 1;
    public static boolean running = true;

    public static void parser(String commandLine) throws DukeException {

        System.out.println(DIV_OPEN);

        String[] command = commandLine.split(" ");

        switch(command[0]) {

            case "list":
                printList();
                break;

            case "mark":
                if (command.length != 2) {
                    throw new DukeException("Please check the number of your arguments!");
                }
                markTask(command[1]);

                break;

            case "unmark":
                if (command.length != 2) {
                    throw new DukeException("Please check the number of your arguments!");
                }
                unmarkTask(command[1]);
                break;

            case "todo":
                if (command.length < 2) {
                    throw new DukeException("The description of a todo cannot be empty!");
                }
                addTodo(command);
                break;

            case "deadline":
                int byIndex = Arrays.asList(command).indexOf("/by");
                if (command.length < 4 || byIndex == -1) {
                    throw new DukeException("Too few arguments!");
                }
                if (byIndex == command.length - 1) {
                    throw new DukeException("Check the format again!");
                }
                addDeadline(command, byIndex);
                break;

            case "event":
                int fromIndex = Arrays.asList(command).indexOf("/from");
                int toIndex = Arrays.asList(command).indexOf("/to");
                if (command.length < 6 || fromIndex == -1 || toIndex == -1) {
                    throw new DukeException("Too few arguments!");
                }
                if (fromIndex + 1 >= toIndex || toIndex == command.length - 1) {
                    throw new DukeException("Check the format again!");
                }
                addEvent(command, fromIndex, toIndex);
                break;

            default:
                /*
                msg = sc.nextLine(); // read finish the task
                Task task = new Task(msg);
                list[listNum] = task;
                System.out.println("added: " + msg);
                listNum++;

                 */
        }

        System.out.println(DIV_CLOSE); // DIV_CLOSE for output

    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < listNum; i++) {
            System.out.println(i + ". " + list[i]);
        }
    }

    public static void markTask(String arg) throws DukeException {
        try {
            int num = Integer.parseInt(arg);
            if (num >= listNum || num <= 0) {
                throw new DukeException("Task number is out of bounds!");
            }
            list[num].markDone();
            System.out.println("Nice! I've marked this task as done:\n  " + list[num]);
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid number");
        }
    }

    public static void unmarkTask(String arg) throws DukeException {
        try {
            int num = Integer.parseInt(arg);
            if (num >= listNum || num <= 0) {
                throw new DukeException("Task number is out of bounds!");
            }
            list[num].markUndone();
            System.out.println("Ok, I've marked this task as not done yet:\n  " + list[num]);
        } catch (NumberFormatException ex) {
            throw new DukeException("Invalid number");
        }
    }

    public static void addTodo(String[] args) {
        int len = args.length;
        StringBuilder taskName = new StringBuilder(args[1]);
        for (int i = 2; i < len; i++) {
            taskName.append(" ").append(args[i]);
        }
        Todo todo = new Todo(taskName.toString());
        list[listNum] = todo;
        System.out.println("Got it. I've added this task:\n  " + list[listNum]);
        System.out.println("Now you have " + listNum + " tasks in the list.");
        listNum++;
    }

    public static void addDeadline(String[] args, int by) {
        int len = args.length;
        StringBuilder taskName = new StringBuilder(args[1]);
        for (int i = 2; i < by; i++) {
            taskName.append(" ").append(args[i]);
        }
        StringBuilder byWhen = new StringBuilder(args[by + 1]);
        for (int i = by + 2; i < len; i++) {
            byWhen.append(" ").append(args[i]);
        }
        Deadline deadline = new Deadline(taskName.toString(), byWhen.toString());
        list[listNum] = deadline;
        System.out.println("Got it. I've added this task:\n  " + list[listNum]);
        System.out.println("Now you have " + listNum + " tasks in the list.");
        listNum++;
    }

    public static void addEvent(String[] args, int from, int to) {
        int len = args.length;
        StringBuilder taskName = new StringBuilder(args[1]);
        for (int i = 2; i < from; i++) {
            taskName.append(" ").append(args[i]);
        }
        StringBuilder fromWhen = new StringBuilder(args[from + 1]);
        for (int i = from + 2; i < to; i++) {
            fromWhen.append(" ").append(args[i]);
        }
        StringBuilder toWhen = new StringBuilder(args[to + 1]);
        for (int i = to + 2; i < len; i++) {
            toWhen.append(" ").append(args[i]);
        }
        Event event = new Event(taskName.toString(), fromWhen.toString(), toWhen.toString());
        list[listNum] = event;
        System.out.println("Got it. I've added this task:\n  " + list[listNum]);
        System.out.println("Now you have " + listNum + " tasks in the list.");
        listNum++;
    }

    public static void main(String[] args) {
        // Initialize
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "\n";
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";

        list.add(new Task("DUMMY"));

        System.out.println(DIV_OPEN + logo + greetings + DIV_CLOSE); // Initialization complete

        // Accept user input in a loop
        Scanner sc = new Scanner(System.in);
        while(running) {

            String msg = sc.nextLine();

            // Terminate Duke
            if (msg.equals("bye")) {
                break;
            }

            try {
                parser(msg);
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                System.out.println(DIV_CLOSE);
            }
        }




        // End of program
        sc.close();
        System.out.printf(DIV_OPEN + "Bye. Hope to see you again soon!\n"+ DIV_CLOSE);
    }
}
