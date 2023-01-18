import java.util.Scanner;

public class Duke {
    private static final String EXIT_PROGRAM = "bye";
    private static final String LIST_TASKS = "list";

    private static final TaskList tasks = new TaskList();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        greetings();
        acceptCommands();
        exit();
    }

    private static void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + ", your personal assistant.\n"
                + "What can I do for you today?");
    }

    private static void acceptCommands() {
        String command = "";
        while (true) {
            command = sc.nextLine();

            if (command.equals(EXIT_PROGRAM)) {
                break;
            } else if (command.equals(LIST_TASKS)) {
                System.out.println(tasks.toString());
            } else {
                String[] mark = command.split(" ");
                String instruction = mark[0];

                if ((instruction.equals("mark") || instruction.equals("unmark")) && mark.length == 2) {
                    int index = Integer.parseInt(mark[1]);

                    if (index > tasks.size() || index <= 0) {
                        System.out.println("There is no task at index " + index + "!");
                    } else {
                        Task task = tasks.get(index - 1);
                        if (instruction.equals("mark")) {
                            task.markAsDone();
                            System.out.println("Good job! I have marked this task as done! \n" + task);
                        } else {
                            task.markAsUndone();
                            System.out.println("Oof! I have marked this task as undone for you! \n" + task);
                        }
                    }
                } else {
                    tasks.addTask(command);
                }
            }
        }

        sc.close();
    }

    private static void exit() {
        System.out.println("I hope you've managed to be productive today. Bye!");
        System.exit(0);
    }
}
