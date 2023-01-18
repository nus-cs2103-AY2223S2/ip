package jarvis;

import jarvis.exception.CommandParseException;
import jarvis.exception.InvalidActionException;
import jarvis.exception.TaskIOException;

import java.util.Scanner;

public class Jarvis {
    private static final String BOT_NAME = Jarvis.class.getSimpleName();

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    public Jarvis() {
        this.storage = new Storage();
        this.ui = new Ui(BOT_NAME);
        this.taskList = new TaskList(storage.readTasks());
    }

    public void run() {
        this.ui.printLogo();
        this.ui.printStandard(Ui.Response.INTRO);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                this.ui.printUserPrompt();
                continue;
            }

            Command command;
            try {
                command = Parser.parse(line);
            } catch (InvalidActionException e) {
                this.ui.printStandard(Ui.Response.CONFUSED);
                continue;
            }

            if (command.hasAction(Command.Action.BYE)) {
                break;
            } else if (command.hasAction(Command.Action.LIST)) {
                this.ui.print(this.taskList.getTasksForPrint());
            } else {
                try {
                    if (command.hasAction(Command.Action.LIST_FILTER)) {
                        this.ui.print(this.taskList.getTasksForPrint(command.toFilter()));
                    } else if (command.hasAction(Command.Action.MARK_DONE, Command.Action.MARK_UNDONE)) {
                        this.ui.print(this.taskList.setTaskDone(command));
                    } else if (command.hasAction(Command.Action.DELETE_TASK)) {
                        this.ui.print(this.taskList.deleteTask(command));
                    } else if (command.hasAction(Command.Action.CREATE_TODO)) {
                        this.ui.print(this.taskList.addTask(command.toToDoTask()));
                    } else if (command.hasAction(Command.Action.CREATE_DEADLINE)) {
                        this.ui.print(this.taskList.addTask(command.toDeadlineTask()));
                    } else if (command.hasAction(Command.Action.CREATE_EVENT)) {
                        this.ui.print(this.taskList.addTask(command.toEventTask()));
                    }
                } catch (CommandParseException e) {
                    this.ui.printError(e.getFriendlyMessage());
                }
            }
        }
        scanner.close();

        try {
            this.storage.saveTasks(this.taskList.getTasks());
        } catch (TaskIOException e) {
            this.ui.printError(e.getFriendlyMessage());
        }
        this.ui.printStandard(Ui.Response.GOODBYE);
    }

    public static void main(String[] args) {
        new Jarvis().run();
    }
}
