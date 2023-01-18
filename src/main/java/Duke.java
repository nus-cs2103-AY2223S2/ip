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
            String[] commands = in.trim().split(" ", 2);
            boolean single = commands.length < 2;
            try {
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
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        lst.mark(Integer.parseInt(commands[1]) - 1);
                        in = input.nextLine();
                        break;
                    case "unmark":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        lst.unmark(Integer.parseInt(commands[1]) - 1);
                        in = input.nextLine();
                        break;
                    case "todo":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        String t = in.trim().split(" ", 2)[1];
                        Todo.processTodo(t, lst);
                        in = input.nextLine();
                        break;
                    case "deadline":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        String d = in.trim().split(" ", 2)[1];
                        Deadline.processDeadline(d, lst);
                        in = input.nextLine();
                        break;
                    case "event":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        String e = in.trim().split(" ", 2)[1];
                        Event.processEvent(e, lst);
                        in = input.nextLine();
                        break;
                    case "delete":
                        if (single) {
                            throw new DukeException(commands[0]);
                        }
                        lst.deleteTask(Integer.parseInt(commands[1]) - 1);
                        in = input.nextLine();
                        break;
                    default:
                        throw new DukeException("none");
                        //in = input.nextLine();
                }
            } catch (DukeException e) {
                System.out.println(e);
                in = input.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!! This index is out of bounds!");
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
            System.out.println(" " + t);
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
