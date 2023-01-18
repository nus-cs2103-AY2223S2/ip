import java.util.*;

import Tasks.*;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("----------------------------------------");

        while (true) {
            String in = sc.nextLine();
            System.out.println("----------------------------------------");
            String[] split = in.split(" ", 2);

            try {
                if (split[0].equals("bye") && split.length == 1) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (split[0].equals("list") && split.length == 1) {
                    for (int i = 0; i < list.size(); i++) {
                        Task curr = list.get(i);
                        System.out.println((i + 1) + "." + curr);
                    }
                } else if (split[0].equals("mark")) {
                    mark(split);
                } else if (split[0].equals("unmark")) {
                    unmark(split);
                } else if (split[0].equals("delete")) {
                    delete(split);
                } else if (Arrays.asList("todo", "deadline", "event").contains(split[0])) {
                    System.out.println("Got it. I've added this task:");
                    switch(split[0]) {
                        case "todo":
                            todo(split);
                            break;
                        case "deadline":
                            deadline(split);
                            break;
                        case "event":
                            event(split);
                            break;
                    }
                    System.out.println(list.get(list.size()-1));
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException d) {
                System.out.println(d.getMessage());
            } finally {
                System.out.println("----------------------------------------");
            }
        }
        sc.close();
    }

    public static void mark(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException("Please specify the task you want to mark.");
        }
        try {
            Integer i = Integer.parseInt(split[1]);
            if (i > list.size() || i < 1) {
                throw new DukeException("Please provide a valid task number.");
            }
            Task task = list.get(i-1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide the number of the task you want to mark.");
        }
    }

    public static void unmark(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException("Please specify the task you want to unmark.");
        }
        try {
            Integer i = Integer.parseInt(split[1]);
            if (i > list.size() || i < 1) {
                throw new DukeException("Please provide a valid task number.");
            }
            Task task = list.get(i-1);
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide the number of the task you want to unmark.");
        }

    }

    public static void todo(String[] split) throws DukeException{
        if (split.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        list.add(new Todo(split[1]));
    }

    public static void deadline(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] tokens = split[1].split(" /by ", 2);
        if (tokens.length == 1) {
            throw new DukeException("Please provide a deadline for this task.");
        }
        list.add(new Deadline(tokens[0], tokens[1]));
    }

    public static void event(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        String[] tokens = split[1].split(" /from ", 2);
        if (tokens.length == 1) {
            throw new DukeException("Please provide a start time for this event.");
        }
        String[] tokens2 = tokens[1].split(" /to ", 2);
        if (tokens2.length == 1) {
            throw new DukeException("Please provide an end time for this event.");
        }
        list.add(new Event(tokens[0], tokens2[0], tokens2[1] ));
    }

    public static void delete(String[] split) throws DukeException {
        if (split.length == 1) {
            throw new DukeException("Please specify the task you want to delete.");
        }
        try {
            Integer i = Integer.parseInt(split[1]);
            if (i > list.size() || i < 1) {
                throw new DukeException("Please provide a valid task number.");
            }
            Task task = list.get(i-1);
            list.remove(i-1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide the number of the task you want to delete.");
        }
    }
}
