import java.util.*;

public class Duke {
    protected static String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    protected static String line = "____________________________________________________________";
    protected static LinkedList<Task> lst = new LinkedList<>();
    protected static boolean cont = true;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you? :)");
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
                    printList();
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
                default:
                    add(in);
                    in = input.nextLine();
            }
        }
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
            lst.add(t);
            System.out.println("added: " + t.getTaskName());
            printLine();
        }
    }

    public static void mark(int taskNum) {
        Task t = lst.get(taskNum);
        t.markAsDone();
        printLine();
        System.out.println("Okay! I've marked this task as done:");
        System.out.println(String.format(" %s", t));
        printLine();
    }

    public static void unmark(int taskNum) {
        Task t = lst.get(taskNum);
        t.markAsUndone();
        printLine();
        System.out.println("Okay! I've marked this task as not done yet:");
        System.out.println(String.format(" %s", t));
        printLine();
    }

    public static void printList() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < lst.size(); i++) {
            String elem = lst.get(i).toString();
            System.out.println(String.format("%d. %s", i + 1, elem));
        }
        printLine();
    }

    public static void end() {
        printLine();
        System.out.println("Bye bye! Hope to see you again soon!!");
        printLine();
    }

}
