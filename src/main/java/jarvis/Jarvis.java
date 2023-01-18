package jarvis;

import jarvis.exception.CommandParseException;
import jarvis.exception.InvalidActionException;
import jarvis.exception.TaskIOException;

import java.util.Scanner;

public class Jarvis {
    private static final String BOT_NAME = Jarvis.class.getSimpleName();
    private static final String LOGO = "     _   _    ______     _____ ____  \n" +
            "    | | / \\  |  _ \\ \\   / /_ _/ ___| \n" +
            " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\ \n" +
            "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n" +
            " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

    private final Storage storage;
    private final Printer printer;
    private final TaskList taskList;

    public Jarvis() {
        this.storage = new Storage();
        this.printer = new Printer(BOT_NAME);
        this.taskList = new TaskList(storage.readTasks());
    }

    public void run() {
        System.out.println(LOGO);
        this.printer.printStandardResponse(Printer.Response.INTRO);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                this.printer.printUserCaret();
                continue;
            }

            Command command;
            try {
                command = new Command(new Scanner(line));
            } catch (InvalidActionException e) {
                this.printer.printStandardResponse(Printer.Response.CONFUSED);
                continue;
            }

            if (command.hasAction(Command.Action.BYE)) {
                break;
            } else if (command.hasAction(Command.Action.LIST)) {
                this.printer.printResponse(this.taskList.getTasksForPrint());
            } else {
                try {
                    if (command.hasAction(Command.Action.LIST_FILTER)) {
                        this.printer.printResponse(this.taskList.getTasksForPrint(command.toFilter()));
                    } else if (command.hasAction(Command.Action.MARK_DONE, Command.Action.MARK_UNDONE)) {
                        this.printer.printResponse(this.taskList.setTaskDone(command));
                    } else if (command.hasAction(Command.Action.DELETE_TASK)) {
                        this.printer.printResponse(this.taskList.deleteTask(command));
                    } else if (command.hasAction(Command.Action.CREATE_TODO)) {
                        this.printer.printResponse(this.taskList.addTask(command.toToDoTask()));
                    } else if (command.hasAction(Command.Action.CREATE_DEADLINE)) {
                        this.printer.printResponse(this.taskList.addTask(command.toDeadlineTask()));
                    } else if (command.hasAction(Command.Action.CREATE_EVENT)) {
                        this.printer.printResponse(this.taskList.addTask(command.toEventTask()));
                    }
                } catch (CommandParseException e) {
                    this.printer.printErrorResponse(e.getFriendlyMessage());
                }
            }
        }
        scanner.close();

        try {
            this.storage.saveTasks(this.taskList.getTasks());
        } catch (TaskIOException e) {
            this.printer.printErrorResponse(e.getFriendlyMessage());
        }
        this.printer.printStandardResponse(Printer.Response.GOODBYE);
    }

    public static void main(String[] args) {
        new Jarvis().run();
    }
}
