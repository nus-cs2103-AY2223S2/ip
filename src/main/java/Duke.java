import java.util.*;

import static java.lang.System.exit;

public class Duke {
    protected static String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    protected static String line = "____________________________________________________________";
    protected static TaskList lst = new TaskList();
    protected static boolean cont = true;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Kirby!\n" + "What can I do for you? :)");
        printLine();
        String in = input.nextLine();
        while(cont) {
            String[] commands = in.trim().split(" ");
            switch (commands[0]) {
                case "bye":
                    end();
                    cont = false;
                    break;
                case "list":
                    printLine();
                    lst.printList();
                    printLine();
                    in = input.nextLine();
                    break;
                case "mark":
                    mark(Integer.parseInt(commands[1]) - 1);
                    in = input.nextLine();
                    break;
                case "unmark":
                    unmark(Integer.parseInt(commands[1]) - 1);
                    in = input.nextLine();
                    break;
                case "todo":
                    String t = in.trim().split(" ", 2)[1];
                    processTodo(t);
                    in = input.nextLine();
                    break;
                case "deadline":
                    String d = in.trim().split(" ", 2)[1];
                    processDeadline(d);
                    in = input.nextLine();
                    break;
                case "event":
                    String e = in.trim().split(" ", 2)[1];;
                    processEvent(e);
                    in = input.nextLine();
                    break;
                default:
                    add(in);
                    in = input.nextLine();
            }
        }
        System.exit(0);
    }

    public static void printLine(){
        System.out.println(line);
    }

    public static void repeat(String s) {
        printLine();
        System.out.println(s);
        printLine();
    }

    public static void add(String s) {
        if (s.trim().isEmpty()) {
            printLine();
            printLine();
        } else {
            Task t = new Task(s.trim());
            printLine();
            lst.addTask(t);
            System.out.println("Got it! I've added: ");
            System.out.println(" " + t.toString());
            lst.printSize();
            printLine();
        }
    }

    public static void mark(int taskNum) {
        Task t = lst.getTask(taskNum);
        t.markAsDone();
        printLine();
        System.out.println("Okay! I've marked this task as done:");
        System.out.println(String.format(" %s", t));
        printLine();
    }

    public static void unmark(int taskNum) {
        Task t = lst.getTask(taskNum);
        t.markAsUndone();
        printLine();
        System.out.println("Okay! I've marked this task as not done yet:");
        System.out.println(String.format(" %s", t));
        printLine();
    }

    public static void processTodo(String command) {
        String taskName = command.trim();
        if (taskName.isEmpty()) {
            printLine();
            printLine();
        } else {
            Todo todo = new Todo(taskName);
            lst.addTask(todo);
            printLine();
            System.out.println("Got it! I've added: ");
            System.out.println(" " + todo.toString());
            lst.printSize();
            printLine();
        }
    }

    public static void processDeadline(String command) {
        String info = command.trim();
        if (info.isEmpty()) {
            printLine();
            printLine();
        } else {
            String[] details = info.split("/");
            String deadline = details[1].split(" ", 2)[1];
            Deadline d = new Deadline(details[0], deadline);
            lst.addTask(d);
            printLine();
            System.out.println("Got it! I've added: ");
            System.out.println(" " + d.toString());
            lst.printSize();
            printLine();
        }
    }

    public static void processEvent(String command) {
        String info = command.trim();
        if (info.isEmpty()) {
            printLine();
            printLine();
        } else {
            String[] details = info.split("/");
            String start = details[1].split(" ", 2)[1];
            String end = details[2].split(" ", 2)[1];
            Event e = new Event(details[0], start, end);
            lst.addTask(e);
            printLine();
            System.out.println("Got it! I've added: ");
            System.out.println(" " + e.toString());
            lst.printSize();
            printLine();
        }
    }

    public static void end() {
        printLine();
        System.out.println("Bye bye! Hope to see you again soon!!");
        printLine();
    }

}
