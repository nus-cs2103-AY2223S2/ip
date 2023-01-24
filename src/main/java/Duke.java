import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;

// A chatbot
public class Duke {

    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser();
    private static Storage storage;
    private static Ui ui = new Ui();

    private Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = storage.load(parser);
        } catch (DukeException e) {
            ui.printMessage(e.toString());
        }
    }

    private void run() {
        ui.greet();
        acceptCommands();
        ui.sayGoodbye();
    }

    private boolean isExitCommand(String input) {
        return input.equals(CommandType.EXIT.getCommand());
    }

    // Loop for user input
    private void acceptCommands() {
        String input;
        while (true) {
            ui.printPromptForInput();
            input = ui.getInputFromUser();
            if (isExitCommand(input)) {
                return;
            }
            executeOneCommand(input);
        }
    }

    // Executes a command, except exit command
    private void executeOneCommand(String input) {
        // Split into two parts at the first space
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        CommandType commandType;
        try {
            commandType = CommandType.getCommandType(command);
            if (commandType.hasArguments()) {
                executeCommandWithArgument(commandType, parts);
            } else {
                executeCommandWithNoArgument(commandType);
            }
            storage.save(taskList);
        } catch (DukeException e) {
            ui.printMessage(e.toString());
        }

    }

    public void executeCommandWithArgument(CommandType command, String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new EmptyArgumentDukeException();
        }
        switch (command) {
            case MARK_TASK_AS_DONE:
                markTaskAsDone(parts[1]);
                break;
            case MARK_TASK_AS_UNDONE:
                markTaskAsNotDone(parts[1]);
                break;
            case TODO:
                addTodoToList(parts[1]);
                break;
            case DEADLINE:
                addDeadlineToList(parts[1]);
                break;
            case EVENT:
                addEventToList(parts[1]);
                break;
            case DELETE:
                deleteTask(parts[1]);
                break;
        }
    }

    public void executeCommandWithNoArgument(CommandType command) {
        switch (command) {
            case DISPLAY_LIST:
                displayTasks();
                break;
        }
    }

    private void addTodoToList(String description) {
        Task task = new ToDoTask(description);
        taskList.add(task);
        ui.printMessage("added: " + task);
    }

    private void addDeadlineToList(String arguments) throws DukeException {
        try {
            String[] splitArgs = arguments.split(" /by ");
            Task task = new DeadlineTask(splitArgs[0], parser.parseDateTime(splitArgs[1]));
            taskList.add(task);
            ui.printMessage("added: " + task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgumentDukeException();
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentDukeException();
        }
    }

    private void addEventToList(String arguments) throws DukeException {
        try {
            String[] splitArgs = arguments.split(" /from ");
            String[] times = splitArgs[1].split(" /to ");
            Task task = new EventTask(splitArgs[0], parser.parseDateTime(times[0]), parser.parseDateTime(times[1]));
            taskList.add(task);
            ui.printMessage("added: " + task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgumentDukeException();
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentDukeException();
        }
    }

    private void displayTasks() {
        new DisplayListCommand().execute(taskList, ui, storage);
    }

    private void markTaskAsDone(String arguments) throws InvalidArgumentDukeException {
        try {
            int number = Integer.parseInt(arguments);
            taskList.markTaskAsDone(number);
            ui.printMessage("Good job. You have finished this task:\n"
                    + taskList.getTaskString(number)
            );
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentDukeException();
        }
    }

    private void markTaskAsNotDone(String arguments) throws InvalidArgumentDukeException {
        try {
            int number = Integer.parseInt(arguments);
            taskList.markTaskAsNotDone(number);
            ui.printMessage("Ok. I have marked this task as not done:\n"
                    + taskList.getTaskString(number)
            );
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentDukeException();
        }
    }

    private void deleteTask(String arguments) throws InvalidArgumentDukeException {
        int number = Integer.parseInt(arguments);
        new DeleteTaskCommand(number).execute(taskList, ui, storage);
    }

    // Main method
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
