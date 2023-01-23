import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> task = new ArrayList<>();

    public static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }
    public static void add(Task t) {
        task.add(t);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:\n\t  " + t + "\n\tNow you have " + task.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    public static void list() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < task.size(); i++) {
            int tmp = i + 1;
            System.out.println("\t" + tmp + "." + task.get(i));
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void mark(String s) throws DukeInvalidArgumentException {
        try {
            int i = Integer.parseInt(s) - 1;
            if (i >= task.size()) {
                throw new DukeInvalidArgumentException("mark");
            }
            task.get(i).setDone(true);

            System.out.println("\t____________________________________________________________");
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + task.get(i));
            System.out.println("\t____________________________________________________________");
        } catch ( NumberFormatException e) {
            System.out.println("enter");
            throw new DukeInvalidArgumentException("mark");
        }
    }

    public static void unmark(String s) throws DukeInvalidArgumentException {
        try {
            int i = Integer.parseInt(s) - 1;
            if (i >= task.size()) {
                throw new DukeInvalidArgumentException("mark");
            }
            task.get(i).setDone(false);

            System.out.println("\t____________________________________________________________");
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t  " + task.get(i));
            System.out.println("\t____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("mark");
        }
    }

    public static void delete(String s) throws DukeInvalidArgumentException {
        try {
            int i = Integer.parseInt(s) - 1;
            if (i >= task.size()) {
                throw new DukeInvalidArgumentException("mark");
            }
            task.remove(i);

            System.out.println("\t____________________________________________________________");
            System.out.println("\tNoted. I've removed this task:\n\t  " + task.get(i) + "\n\tNow you have " + task.size() + " tasks in the list.");
            System.out.println("\t____________________________________________________________");
        } catch ( NumberFormatException e) {
            System.out.println("enter");
            throw new DukeInvalidArgumentException("delete");
        }
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                String[] split = input.split(" ", 2);
                String cmd = split[0];

                switch (cmd) {
                    case "list":
                        list();
                        break;
                    case "mark":
                    case "unmark":
                    case "delete":
                        if (split.length == 1 || split[1].isEmpty()) {
                            throw new DukeEmptyArgumentException(cmd);
                        }
                        if (cmd.equals("mark")) {
                            mark(split[1]);
                        } else if (cmd.equals("unmark")){
                            unmark(split[1]);
                        } else {
                            delete(split[1]);
                        }
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        if (split.length == 1 || split[1].isEmpty()) {
                            throw new DukeEmptyArgumentException(cmd);
                        }
                        Task t = null;
                        switch (cmd) {
                            case "todo":
                                t = new ToDos(split[1]);
                                break;
                            case "deadline":
                                String[] s1 = split[1].split("/by ", 2);
                                t = new Deadlines(s1[0], LocalDateTime.parse(s1[1]));
                                break;
                            case "event":
                                String[] s2 = split[1].split("/from ", 2);
                                String[] s3 = s2[1].split("/to ", 2);
                                t = new Events(s2[0], LocalDateTime.parse(s3[0]), LocalDateTime.parse(s3[1]));
                                break;
                        }
                        add(t);
                        break;
                    default:
                        throw new DukeUnknownCommandException(input);
                }
            } catch (DukeUnknownCommandException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t____________________________________________________________");
            } catch (DukeEmptyArgumentException e) {
                System.out.println("\t____________________________________________________________");
                System.out.printf("\t☹ OOPS!!! The description of a %s cannot be empty.\n", e.getMessage());
                System.out.println("\t____________________________________________________________");
            } catch (DukeInvalidArgumentException e) {
                System.out.println("\t____________________________________________________________");
                System.out.printf("\t☹ OOPS!!! The description of a %s is invalid.\n", e.getMessage());
                System.out.println("\t____________________________________________________________");
            } catch (DateTimeParseException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t☹ OOPS!!! The format of date-time is invalid.");
                System.out.println("\t____________________________________________________________");
            } finally {
                input = scanner.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        greet();
        run();
        exit();
    }
}
