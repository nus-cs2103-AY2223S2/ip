package duke.driver;

import duke.command.DukeCommand;
import duke.parser.DukeParser;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class GuiDriver {
    private static TaskList taskList = Storage.readTaskList();

    public static String getResponse(String inputString) {
        try {
            DukeCommand command = DukeParser.parseCommand(inputString);
            String[] commandArgs = DukeParser.parseCommandArgs(command, inputString);

            switch (command) {
            case LIST:
                return processListCommand(taskList);

            case BYE:
                return "Bye. Hope to see you again soon!";

            // Find command
            case FIND:
                return processFindCommand(commandArgs);

            // Task Creation commands
            case DEADLINE:
            case EVENT:
            case TODO:
                return processTaskCreationCommand(command, commandArgs);

            // Index-based commands
            case MARK:
            case UNMARK:
            case DELETE:
                return processIndexBasedCommand(command, commandArgs);
            }

        } catch (Error e) {
            return e.getMessage();
        }
        return inputString;
    }



    private static String processListCommand(TaskList taskList) {
        String taskStringList = taskList.toString();
        return taskStringList;
    }

    private static String processFindCommand(String[] commandArgs) {
        String keyword = commandArgs[0];

        return Ui.getPrettyString("Here are the matching tasks in your list:",
                taskList.find(keyword).toString());
    }

    private static String processTaskCreationCommand(DukeCommand command, String[] commandArgs) {
        switch (command) {
        case DEADLINE: {
            return processDeadlineCommand(commandArgs);
        }
        case EVENT: {
            return processEventCommand(commandArgs);
        }
        case TODO: {
            return processTodoCommand(commandArgs);
        }
        default:
            // Shouldn't reach here
            return "";
        }
    }

    private static String processIndexBasedCommand(DukeCommand command, String[] commandArgs) {
        switch (command) {

        case MARK:
            return processMarkCommand(commandArgs);

        case UNMARK:
            return processUnmarkCommand(commandArgs);

        case DELETE:
            return processDeleteCommand(commandArgs);
        default:
            return "";
        }
    }


    // Task Creation helper functions
    private static String processTodoCommand(String[] commandArgs) {
        String description = commandArgs[0];
        Task task = new ToDo(description);

        taskList.addTask(task);
        int numTasks = taskList.getNumTasks();

        return Ui.getAddTaskString(task, numTasks);
    }

    private static String processEventCommand(String[] commandArgs) {
        String description = commandArgs[0];
        String from = commandArgs[1];
        String to = commandArgs[2];
        Event event = new Event(description, from, to);

        taskList.addTask(event);
        int numTasks = taskList.getNumTasks();

        return Ui.getAddTaskString(event, numTasks);
    }

    private static String processDeadlineCommand(String[] commandArgs) {
        String description = commandArgs[0];
        String by = commandArgs[1];
        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        int numTasks = taskList.getNumTasks();
        return Ui.getAddTaskString(deadline, numTasks);
    }

    // Index-based helper functions
    private static String processMarkCommand(String[] commandArgs) {
        try {
            int taskIndex = Integer.parseInt(commandArgs[0]);
            return Ui.getPrettyString("Nice! I've marked this task as done:",
                    taskList.markTask(taskIndex));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Please input numerals as your index!";
        }
    }

    private static String processUnmarkCommand(String[] commandArgs) {
        try {
            int taskIndex = Integer.parseInt(commandArgs[0]);
            return Ui.getPrettyString("OK, I've marked this task as not done yet:",
                    taskList.unmarkTask(taskIndex));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Please input numerals as your index!";
        }
    }

    private static String processDeleteCommand(String[] commandArgs) {
        try {
            int taskIndex = Integer.parseInt(commandArgs[0]);
            return Ui.getPrettyString("Noted. I've removed this task:",
                    taskList.deleteTask(taskIndex));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Please input numerals as your index!";
        }
    }


}
