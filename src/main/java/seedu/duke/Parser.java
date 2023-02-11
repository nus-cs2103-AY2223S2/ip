package seedu.duke;

/**
 * Represents Parser object.
 */
public class Parser {

    /**
     * Enum for Commands for DukeBot.
     */
    public enum Commands {
        BYE, LIST, FIND, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, HELP}

    /**
     * The Parser parses the commands based on the user input.
     *
     * @param taskList the TaskList where the tasks are stored.
     * @param storage  manages saving and loading the files from taskList.txt.
     * @param ui       manages the user interface and the output text from Duke.
     * @param s        the user input.
     *
     * @return String Duke's response.
     *
     * @throws ArrayIndexOutOfBoundsException If there is an empty input.
     * @throws DukeException       If there is an unknown command.
     */

    public String parse(TaskList taskList, Storage storage, Ui ui, String s) throws DukeException {
        String response = "";
        try {
            // Split the user input into an array with the command and the description
            String[] description = s.split(" ", 2);
            String command = description[0];
            Commands currCommand = Commands.valueOf(command.toUpperCase());
            assert taskList.size() >= 0: "TaskList length cannot be negative.";

        switch (currCommand) {
        case BYE:
            response = ui.displayExit();
            break;
        case LIST: {
            response = ui.displayTaskList(taskList);
            break;
        }
        case FIND: {
            String keyword = description[1];
            response = ui.displayFindList(taskList.findTasks(keyword), keyword);
            break;
        }
        case MARK: {
            String input = description[1];
            response = TaskList.markTask(taskList, input);
            storage.write(taskList);
            break;
        }
        case UNMARK: {
            String input = description[1];
            response = TaskList.unmarkTask(taskList, input);
            storage.write(taskList);
            break;
        }
        case DELETE: {
            String input = description[1];
            response = TaskList.removeTask(taskList, input);
            storage.write(taskList);
            break;
        }
        case TODO: {
            try {
                String input = description[1];
                response = Todo.runTodo(taskList, input);
                storage.write(taskList);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Hey! The description of a todo cannot be empty!";
            }
            break;
        }
        case DEADLINE: {
            try {
                String input = description[1];
                response = Deadline.runDeadline(taskList, input);
                storage.write(taskList);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Hey! The description of a deadline cannot be empty!";
            }
            break;
        }
        case EVENT: {
            try {
                String input = description[1];
                response = Event.runEvent(taskList, input);
                storage.write(taskList);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Hey! The description of an event cannot be empty!";
            }
            break;
        }
        case HELP: {
            return "Visit this page for a detailed guide on how to use Duke!\n\nhttps://gremmyz.github.io/ip/";
        }
        default:
            return "Invalid argument";
        }
        } catch (IllegalArgumentException e) {
            return "I don't know what that means :(";
        }
        return response;
    }
}
