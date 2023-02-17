package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * duke.Parser class encapsulates the logic implementation of the chatbot.
 */
public class Parser {
    public Parser() {}

    /**
     * Handles the input from the user and do the corresponding actions.
     * Checks for some incorrect input format.
     *
     * @param command The input from user.
     * @param taskList A duke.TaskList object encapsulate all tasks' information.
     * @return Returns true if the input is bot terminating command ("bye"), false otherwise.
     * @throws DukeException If the user input is not in the right format.
     */
    public boolean handleInput(String command, TaskList taskList) throws DukeException {
        if (command.equals("bye")) {
            return true;
        } else if (command.equals("list")) {
            Ui.displayTasks(taskList);
            return false;
        }

        if (command.startsWith("mark ")) {
            taskList.mark(command);
            return false;
        } else if (command.startsWith("unmark ")) {
            taskList.unmark(command);
            return false;
        } else if (command.startsWith("delete ")) {
            taskList.delete(command);
            return false;
        }

        String description = command;
        Task newTask = new Task(command);
        String exceptionMessage = "";

        if (command.startsWith("todo")) {
            if (description.length() <= 5) {
                exceptionMessage = "The description of a todo cannot be empty.\n";
                throw new DukeException(exceptionMessage);
            }
            newTask = new ToDo(description.substring(5));
        } else if (command.startsWith("deadline")) {
            if (description.length() <= 9) {
                exceptionMessage = "The description of a deadline cannot be empty.\n";
                throw new DukeException(exceptionMessage);
            }
            LocalDate by = LocalDate.parse(description.substring(description.indexOf(" /by ") + 5),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            description = description.substring(9, description.indexOf(" /by "));
            newTask = new Deadline(description, by);
        } else if (command.startsWith("event")) {
            if (description.length() <= 6) {
                exceptionMessage = "The description of an event cannot be empty:\n";
                throw new DukeException(exceptionMessage);
            }
            LocalDate start = LocalDate.parse(description.substring(description.indexOf(" /from ") + 7,
                    description.indexOf(" /to ")), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate end = LocalDate.parse(description.substring(description.indexOf(" /to ") + 5),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            description = description.substring(6, description.indexOf(" /from "));
            newTask = new Event(description, start, end);
        } else {
            exceptionMessage = "I'm sorry, but I don't know what that means :-(\n";
            throw new DukeException(exceptionMessage);
        }
        if (!taskList.contains(newTask)) {
            taskList.addTask(newTask);
        }
        return false;
    }
}
