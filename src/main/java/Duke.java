import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "__________________________________________________________\n";
    private ArrayList<Task> taskList = new ArrayList<>(100);
    private int counter = 0;

    public void greet() {
        System.out.println(Duke.LINE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + Duke.LINE);
    }

    public void exit() {
        System.out.println(Duke.LINE
                + "Bye. Hope to see you again soon!\n"
                + Duke.LINE);
    }

    public void list() {
        System.out.println(Duke.LINE + "Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
        System.out.println(Duke.LINE);
    }

    public void mark(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toChange = taskList.get(position);
        toChange.mark();
        System.out.println(Duke.LINE + "Nice! I've marked this task as done:\n"
                + toChange.toString() + "\n" + Duke.LINE);
    }

    public void unmark(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toChange = taskList.get(position);
        toChange.unmark();
        System.out.println(Duke.LINE + "Okay, I've marked this task as not done yet:\n"
                + toChange.toString() + "\n" + Duke.LINE);
    }

    public void todo(String input) {
        Task toAdd = new Todos(input);
        taskList.add(toAdd);
        counter++;
        System.out.println(Duke.LINE + "Got it. I've added this task:\n" + toAdd.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void deadline(String input) {
        String[] splited = input.split(" /by ");
        Task toAdd = new Deadlines(splited[0], splited[1]);
        taskList.add(toAdd);
        counter++;
        System.out.println(Duke.LINE + "Got it. I've added this task:\n" + toAdd.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void event(String input) {
        String[] splited = input.split(" /from | /to " );
        Task toAdd = new Events(splited[0], splited[1], splited[2]);
        taskList.add(toAdd);
        counter++;
        System.out.println(Duke.LINE + "Got it. I've added this task:\n" + toAdd.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void start(Scanner sc) {
        this.greet();

        while(sc.hasNext()) {
            try {
                String[] input = sc.nextLine().split(" ", 2);

                switch (input[0]) {
                    case "bye":
                        this.exit();
                        break;

                    case "list":
                        this.list();
                        break;

                    case "mark":
                        if (input.length < 2) {
                            throw new NumberMissingException();
                        }
                        this.mark(input[1]);
                        break;

                    case "unmark":
                        if (input.length < 2) {
                            throw new NumberMissingException();
                        }
                        this.unmark(input[1]);
                        break;

                    case "todo":
                        if (input.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        this.todo(input[1]);
                        break;

                    case "deadline":
                        if (input.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        this.deadline(input[1]);
                        break;

                    case "event":
                        if (input.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        this.event(input[1]);
                        break;

                    default:
                        throw new InvalidCommandException();
                }
            }
            catch (Exception e) {
                System.out.println(Duke.LINE + e.getMessage());
                System.out.println(Duke.LINE);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();
        duke.start(scanner);
    }
}
