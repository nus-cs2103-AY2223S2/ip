import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> todo = new ArrayList<>();
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    static String line = "---------------------------------";

    static void add(Task cur) {
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + cur.symbol + "] " + "[" + cur.getStatusIcon() + "] " + cur.description);
        todo.add(cur);
        System.out.println("Now you have " + todo.size() + " tasks in the list.");
    }

    static void list(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (Task cur : todo) {
            System.out.println(todo.indexOf(cur) + 1 + ". [" + cur.symbol + "] " + "[" + cur.getStatusIcon() + "] " + cur.description);
        }
    }

    static void markTask(int index) {
        Task cur = todo.get(index);
        cur.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + cur.getStatusIcon() + "] " + cur.description);
    }

    static void unmarkTask(int index) {
        Task cur = todo.get(index);
        cur.markUndone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + cur.getStatusIcon() + "] " + cur.description);
    }
    static void deleteTask(int index) {
        Task cur = todo.get(index);
        todo.remove(index);
        System.out.println("Noted, I've removed this task: ");
        System.out.println("[" + cur.getStatusIcon() + "] " + cur.description);
        System.out.println("Now you have " + todo.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo + "  managed by Wesley Teo.\n\nWhat can I do for you?\n" + line);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                System.out.println(line);
                QueryType inputType = Query.queryType(input);
                String[] inputArr = input.split(" ");
                int index;

                switch (inputType) {
                    case list:
                        list(todo);
                        break;
                    case todo:
                        Todo todo = new Todo(input);
                        add(todo);
                        break;
                    case deadline:
                        Deadline deadline = new Deadline(input);
                        add(deadline);
                        break;
                    case event:
                        Event event = new Event(input);
                        add(event);
                        break;
                    case mark:
                        index = Integer.parseInt(inputArr[1]) - 1;
                        markTask(index);
                        break;
                    case unmark:
                        index = Integer.parseInt(inputArr[1]) - 1;
                        unmarkTask(index);
                        break;
                    case delete:
                        index = Integer.parseInt(inputArr[1]) - 1;
                        deleteTask(index);
                        break;
                    default:
                        throw new InvalidCommandDukeException("Invalid command, please try again");
                }
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
                System.out.println(line);
                input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
    }
}