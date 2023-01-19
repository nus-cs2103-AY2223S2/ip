import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    private static String DIV_OPEN = "____________________________________________________________\n";
    private static String DIV_CLOSE = "____________________________________________________________\n";

    public static Task[] list = new Task[101];
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
                addTodo(Arrays.copyOfRange(command, 1, command.length));
                break;

            case "deadline":
                int byIndex = Arrays.asList(command).indexOf("/by");
                if (command.length < 4 || byIndex == -1 || byIndex == command.length - 1) {
                    throw new DukeException("Check the format again!");
                }
                addDeadline(Arrays.copyOfRange(command, 1, command.length));
                int byIndex = msg.indexOf(" /by ");
                String dlName = msg.substring(1, byIndex);
                String dlBy = msg.substring(byIndex + 5);
                Deadline deadline = new Deadline(dlName, dlBy);
                list[listNum] = deadline;
                System.out.println("Got it. I've added this task:\n  " + list[listNum]);
                System.out.println("Now you have " + listNum + " tasks in the list.");
                listNum++;
                break;

            case "event":
                msg = sc.nextLine();
                int fromIndex = msg.indexOf(" /from ");
                int toIndex = msg.indexOf(" /to ");
                String eventName = msg.substring(1, fromIndex);
                String eventFrom = msg.substring(fromIndex + 7, toIndex);
                String eventTo = msg.substring(toIndex + 5);
                Event event = new Event(eventName, eventFrom, eventTo);
                list[listNum] = event;
                System.out.println("Got it. I've added this task:\n  " + list[listNum]);
                System.out.println("Now you have " + listNum + " tasks in the list.");
                listNum++;
                break;

            default:
                msg = sc.nextLine(); // read finish the task
                Task task = new Task(msg);
                list[listNum] = task;
                System.out.println("added: " + msg);
                listNum++;
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
        String taskName = "";
        for (int i = 0; i < len - 1; i++) {
            taskName += args[i] + " ";
        }
        taskName += args[len];
        Todo todo = new Todo(taskName);
        list[listNum] = todo;
        System.out.println("Got it. I've added this task:\n  " + list[listNum]);
        System.out.println("Now you have " + listNum + " tasks in the list.");
        listNum++;
    }

    public static void addDeadline(String[] args) {

    }

    public static void addEvent(String[] args) {
        
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



        System.out.println(DIV_OPEN + logo + greetings + DIV_CLOSE); // Initialization complete

        // Accept user input in a loop
        Scanner sc = new Scanner(System.in);
        while(running) {

            String msg = sc.nextLine();

            // Terminate Duke
            if (msg.equals("bye")) {
                break;
            }

            parser(msg);

        }




        // End of program
        sc.close();
        System.out.printf(DIV_OPEN + "Bye. Hope to see you again soon!\n"+ DIV_CLOSE);
    }
}
