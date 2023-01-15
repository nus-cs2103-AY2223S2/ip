import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static Scanner instr = new Scanner(System.in);
    public static List<Tasks> todoList = new ArrayList<>(100);
    public static void Greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you? "+
                "\n('bye' to terminate Duke)" +
                "\n('list' to access list of tasks)" +
                "\n('un/mark X' to un/mark X task on list)" +
                "\n('todo/deadline/event' for keeping note of different tasks)");
    }
    public static void Echo(String str) throws Exception {
        if (str.equals("bye")) {
            Exit();
        } else {
            if (str.equals("list")) {
                if (todoList.isEmpty()) {
                    System.out.println("You have nothing scheduled, add something to the list.");
                } else {
                    int n = 1;
                    for (Tasks t : todoList) {
                        System.out.println(n + ". "
                                + t.icon()
                                + t.symbol() + " "
                                + t.getDesc());
                        n++;
                    }
                }
            } else if (str.contains("mark")) {
                if (str.contains("un")) {
                    int index = Integer.parseInt((str.substring(7)));
                    Tasks t = todoList.get(index - 1);
                    t.unmark();
                    System.out.println("Oops! Stop procrastinating: \n"
                            + t.symbol() + " " + t.getDesc());

                } else {
                    int index = Integer.parseInt(str.substring(5));
                    Tasks t = todoList.get(index - 1);
                    t.mark();
                    System.out.println("Nice! I've marked this task as done: \n"
                            + t.symbol() + " " + t.getDesc());
                }
            } else {
                String type = str.split(" ", 2)[0];
                if (str.split(" ",2 ).length > 1) {
                    switch (type) {
                        case "todo":
                            Tasks t = new ToDo(str);
                            todoList.add(t);
                            System.out.println(t.mssg() +
                                    "\nNow you have " +
                                    todoList.size() +
                                    " tasks in the list");
                            break;
                        case "deadline":
                            Tasks d = new Deadline(str);
                            todoList.add(d);
                            System.out.println(d.mssg() +
                                    "\nNow you have " +
                                    todoList.size() +
                                    " tasks in the list");
                            break;
                        case "event":
                            Tasks e = new Event(str);
                            todoList.add(e);
                            System.out.println(e.mssg() +
                                    "\nNow you have " +
                                    todoList.size() +
                                    " tasks in the list");
                            break;
                        default:
                            throw new unrecogException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    }
                } else {
                    if (str.equals("todo") || str.equals("event")||str.equals("deadline")){
                        throw new emptyDescException("☹ OOPS!!! The description of a "+type+" cannot be empty.\n");
                    } else {
                        throw new unrecogException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    }
                }
                }
        }
    }
    public static void Exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
        while (instr.hasNextLine()) {
            String str = instr.nextLine();
            Echo(str);
        }

    }

}
