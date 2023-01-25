package duke.helper;

import duke.exception.EmptyTaskException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Parser {
    /**
     * Validates the task input, checks for empty tasks. If task is empty, throws EmptyTaskException.
     *
     * @param input the user input.
     * @throws EmptyTaskException
     */
    public void validateTaskInput(String input) throws EmptyTaskException {
        String s = input.strip();
        if (s.equals("todo") || s.equals("deadline") || s.equals("event")) {
            throw new EmptyTaskException("Task " + s + " cannot be empty nya!\n");
        }
    }

    /**
     * Creates an appropriate task from the user input. Tasks created can be ToDo, Deadline or Event.
     *
     * @param input the user input.
     * @return Task the resultant task formed.
     */
    public Task makeTaskFromInput(String input) {
        validateTaskInput(input);
        String rest = input.substring(input.indexOf(" ") + 1);
        switch (input.split(" ")[0]) {
        case "todo":
            return new ToDo(rest);
        case "deadline":
            String[] words = rest.split("/by");
            return new Deadline(words[0].strip(), words[1].strip());
        default:
            String description = rest.split("/from")[0].strip();
            String start = rest.split("/from")[1].split("/to")[0].strip();
            String end = rest.split("/to")[1].strip();
            return new Event(description, start, end);
        }
    }

    /**
     * dispatches the commands based off the user input.
     *
     * @param command
     * @param ui
     * @param taskList
     * @throws InvalidCommandException
     */
    public void dispatch(String command, Ui ui, TaskList taskList) throws InvalidCommandException {
        switch (command.split(" ")[0]) {
        case "list":
            ui.print(taskList.listTasks());
            break;
        case "mark":
            ui.print(taskList.markTask(Integer.parseInt(command.split(" ")[1])));
            break;
        case "unmark":
            ui.print(taskList.unmarkTask(Integer.parseInt(command.split(" ")[1])));
            break;
        case "delete":
            ui.print(taskList.deleteTask(Integer.parseInt(command.split(" ")[1])));
            break;
        case "todo":
        case "deadline":
        case "event":
            ui.print(taskList.addTask(makeTaskFromInput(command)));
            break;
        case "find":
            ui.print(taskList.findTask(command.split(" ")[1]));
            break;
        default:
            throw new InvalidCommandException("Invalid command nya!\n"
                    + " Do it again and I will scratch you!\n");
        }
    }
}
