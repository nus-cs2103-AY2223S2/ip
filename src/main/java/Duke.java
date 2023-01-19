import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final String EXIT_PROGRAM = "bye";
    private static final String LIST_TASKS = "list";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";

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
        String command = sc.nextLine();
        while (!command.equals(EXIT_PROGRAM)) {
            String[] commandList = command.split(" ");
            String type = commandList[0].toLowerCase();

            switch(type) {
                case LIST_TASKS: {
                    if (tasks.size() < 1) {
                        System.out.println("You have not added any tasks yet!");
                    } else {
                        System.out.println(tasks);
                    }
                    break;
                }
                case DEADLINE: {
                    String[] parseCommand = command.split("/by");
                    String name = parseCommand[0].replace("deadline ", "");
                    String by = parseCommand[1].strip();
                    tasks.addTask(new Deadline(name, by));
                    break;
                }
                case TODO: {
                    tasks.addTask(new ToDo(command.replace("todo ", "").strip()));
                    break;
                }
                case EVENT: {
                    String[] parseCommand = command.split("/from");
                    String name = parseCommand[0].replace("event ", "");
                    parseCommand = parseCommand[1].split("/to");
                    String from = parseCommand[0].strip();
                    String by = parseCommand[1].strip();
                    tasks.addTask(new Event(name, from, by));
                    break;
                }
                case MARK: {
                    int index = Integer.parseInt(commandList[1]);
                    if (index > tasks.size() || index <= 0) {
                        System.out.println("There is no task at index " + index + "!");
                    } else {
                        Task task = tasks.get(index - 1);
                        task.markAsDone();
                        System.out.println("Good job! I have marked this task as done! \n" + task);
                    }
                    break;
                }
                case UNMARK: {
                    int index = Integer.parseInt(commandList[1]);
                    if (index > tasks.size() || index <= 0) {
                        System.out.println("There is no task at index " + index + "!");
                    } else {
                        Task task = tasks.get(index - 1);
                        task.markAsUndone();
                        System.out.println("Oof! I have marked this task as undone for you! \n" + task);
                    }
                    break;
                }
                default:
                    System.out.println("Sorry, that command is not recognised. \n" +
                            "Maybe you could contact @dsja612 on github to request for more types of commands :)");

            }
            command = sc.nextLine();
        }

        sc.close();
    }

    private static void exit() {
        System.out.println("I hope you've managed to be productive today. Bye!");
        System.exit(0);
    }
}
