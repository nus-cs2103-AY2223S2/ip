import java.util.Scanner;

public class Duke {
    private static final Task[] task = new Task[100];

    public static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }
    public static void add(Task t) {
        int numOfTask = Task.getNumOfTask();
        task[numOfTask - 1] = t;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:\n\t  " + t + "\n\tNow you have " + numOfTask + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
    }

    public static void list() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");
        int numOfTask = Task.getNumOfTask();
        for(int i = 0; i < numOfTask; i++) {
            int tmp = i + 1;
            System.out.println("\t" + tmp + "." + task[i]);
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
            if (i >= Task.getNumOfTask()) {
                throw new DukeInvalidArgumentException("mark");
            }
            task[i].setDone(true);

            System.out.println("\t____________________________________________________________");
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + task[i]);
            System.out.println("\t____________________________________________________________");
        } catch ( NumberFormatException e) {
            System.out.println("enter");
            throw new DukeInvalidArgumentException("mark");
        }
    }

    public static void unmark(String s) throws DukeInvalidArgumentException {
        try {
            int i = Integer.parseInt(s) - 1;
            if (i >= Task.getNumOfTask()) {
                throw new DukeInvalidArgumentException("mark");
            }
            task[i].setDone(false);

            System.out.println("\t____________________________________________________________");
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t  " + task[i]);
            System.out.println("\t____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException("mark");
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
                        if (split.length == 1 || split[1].isEmpty()) {
                            throw new DukeEmptyArgumentException(cmd);
                        }
                        if (cmd.equals("mark")) {
                            mark(split[1]);
                        } else {
                            unmark(split[1]);
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
                                //System.out.println("todo");
                                t = new ToDos(split[1]);
                                break;
                            case "deadline":
                                //System.out.println("deadline");
                                String[] s1 = split[1].split("/by ", 2);
                                t = new Deadlines(s1[0], s1[1]);
                                break;
                            case "event":
                                String[] s2 = split[1].split("/from ", 2);
                                String[] s3 = s2[1].split("/to ", 2);
                                t = new Events(s2[0], s3[0], s3[1]);
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
