package duke;

public class Controller {

    private static final String COMMAND_SEPARATOR = " ";
    private static final String FAREWELL_COMMAND = "bye";

    private static final String MARK_COMMAND = "mark ";
    private static final String UNMARK_COMMAND = "unmark ";
    private static final String DELETE_COMMAND = "delete ";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo ";
    private static final String DEADLINE_COMMAND = "deadline ";
    private static final String EVENT_COMMAND = "event ";
    private static final String INVALID_COMMAND_EXCEPTION_MESSAGE = "Invalid Command received.";

    private TaskList tasks;
    private UserInterface ui;
    private Storage storage;

    Controller(UserInterface ui, Storage storage) {
        try {
            tasks = storage.recoverList();
        } catch (DukeException exception) {
            ui.showExceptionMessage(exception);
        }
        this.ui = ui;
        this.storage = storage;
    }

    public void runExecutionLoop() {
        while(true) {
            try {
                String command = ui.getCommand();
                String result = executeCommand(command);
                ui.displayResult(result);
            } catch (DukeException exception) {
                ui.showExceptionMessage(exception);
            }
        }
    }

    public String executeCommand(String command) throws DukeException {
        int firstSeparatorIndex = command.indexOf(COMMAND_SEPARATOR);
        String commandType = command.substring(0, firstSeparatorIndex);
        String info = command.substring(firstSeparatorIndex + 1);

        String result;
        switch (commandType) {
        case TODO_COMMAND:
            result = tasks.addTask(command);
            break;
        case EVENT_COMMAND:
            result = tasks.addTask(command);
            break;
        case DEADLINE_COMMAND:
            result = tasks.addTask(command);
            break;
        case LIST_COMMAND:
            result = tasks.toString();
            break;
        case FAREWELL_COMMAND:
            result = UserInterface.FAREWELL_MESSAGE;
            break;
        case MARK_COMMAND:
            result = tasks.markTask(info);
            break;
        case UNMARK_COMMAND:
            result = tasks.unmarkTask(info);
            break;
        default:
            throw new DukeException(INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
        storage.saveTaskChangesToFile(tasks);
        return result;
    }
}
