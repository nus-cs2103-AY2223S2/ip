import enums.CommandType;
import java.util.Scanner;

public class Duke{
    private static final TaskList tasks = new TaskList();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws DukeException {
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

    private static void acceptCommands() throws DukeException {
        String command = sc.nextLine();
        boolean exitCommandGiven = false;

        while (!exitCommandGiven) {
            try {
                String[] commandList = command.split(" ");
                CommandType commandType = CommandType.valueOf(commandList[0].toUpperCase().strip());

                boolean tooFewArgs = commandList.length <= 1;

                switch(commandType) {
                    case LIST: {
                        if (tasks.isEmpty()) {
                            System.out.println("You have not added any tasks yet!");
                        } else {
                            System.out.println(tasks);
                        }
                        break;
                    }
                    case DEADLINE: {
                        if (tooFewArgs) {
                            throw new DukeException("Please give a name for your deadline!");
                        } else if (!command.contains(" /by ")) {
                            throw new DukeException("Please give a date/time for your deadline!");
                        }
                        String[] parseCommand = command.split("/by");
                        String name = parseCommand[0].replaceFirst("deadline ", "");
                        String by = parseCommand[1].strip();
                        tasks.addTask(new Deadline(name, by));
                        break;
                    }
                    case TODO: {
                        if (tooFewArgs) {
                            throw new DukeException("Please give a name for your ToDo task!");
                        }
                        tasks.addTask(new ToDo(command.replaceFirst("todo ", "").strip()));
                        break;
                    }
                    case EVENT: {
                        if (tooFewArgs) {
                            throw new DukeException("Please give a name for your event!");
                        } else if (!command.contains(" /from ")) {
                            throw new DukeException("Please give a starting date/time for your event!");
                        } else if (!command.contains(" /to ")) {
                            throw new DukeException("Please give an ending date/time for your event!");
                        }
                        String[] parseCommand = command.split("/from");
                        String name = parseCommand[0].replaceFirst("event ", "");
                        parseCommand = parseCommand[1].split("/to");
                        String from = parseCommand[0].strip();
                        String by = parseCommand[1].strip();
                        tasks.addTask(new Event(name, from, by));
                        break;
                    }
                    case MARK: {
                        if (tooFewArgs) {
                            throw new DukeException("Please provide the index of the task!");
                        }
                        int index = Integer.parseInt(commandList[1]);
                        if (index > tasks.size() || index <= 0) {
                            System.out.println("There is no task at index " + index + "!");
                        } else {
                            Task task = tasks.get(index - 1);
                            task.markAsDone();
                            System.out.println("Good job! I have marked this task as done! \n" + "\t" + task);
                        }
                        break;
                    }
                    case UNMARK: {
                        if (tooFewArgs) {
                            throw new DukeException("Please provide the index of the task!");
                        }
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
                    case BYE: {
                        exitCommandGiven = true;
                        break;
                    }
                }
            } catch (DukeException e) {
                System.out.println(e);
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry, that command is not recognised. \n" +
                        "P.S. Maybe you could contact @dsja612 on github to request for more types of commands :)");
            } finally {
                sc.reset();
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
