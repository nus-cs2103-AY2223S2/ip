package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * duke.Parser class encapsulates the logic implementation of the chatbot.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Handles the input from the user, do the corresponding actions and return the response.
     *
     * @param command The input from user.
     * @param taskList A duke.TaskList object encapsulate all tasks' information.
     * @return Returns A message responsed by chatbot for the argument user input.
     * @throws DukeException If the user input is not in the right format.
     */
    public String doAndGetResponse(String command, TaskList taskList) throws DukeException {
        if (command.equals("bye")) {
            return Ui.getOutroMessage();
        } else if (command.equals("list")) {
            return Ui.getTaskListMessage(taskList);
        }

        if (command.startsWith("mark ")) {
            return taskList.mark(command);
        } else if (command.startsWith("unmark ")) {
            return taskList.unmark(command);
        } else if (command.startsWith("delete ")) {
            return taskList.delete(command);
        } else if (command.startsWith("find ")) {
            return taskList.find(command);
        }

        String description = command;
        Task newTask = new Task(command);
        String responseMessage = "";

        if (command.startsWith("todo")) {
            if (description.length() <= 5) {
                responseMessage = "The description of a todo cannot be empty.\n";
                throw new DukeException(responseMessage);
            }
            newTask = new ToDo(description.substring(5));
        } else if (command.startsWith("deadline")) {
            if (description.length() <= 9) {
                responseMessage = "The description of a deadline cannot be empty.\n";
                throw new DukeException(responseMessage);
            }
            LocalDate by = LocalDate.parse(description.substring(description.indexOf(" /by ") + 5),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            description = description.substring(9, description.indexOf(" /by "));
            newTask = new Deadline(description, by);
        } else if (command.startsWith("event")) {
            if (description.length() <= 6) {
                responseMessage = "The description of an event cannot be empty:\n";
                throw new DukeException(responseMessage);
            }
            LocalDate start = LocalDate.parse(description.substring(description.indexOf(" /from ") + 7,
                    description.indexOf(" /to ")), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate end = LocalDate.parse(description.substring(description.indexOf(" /to ") + 5),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            description = description.substring(6, description.indexOf(" /from "));
            newTask = new Event(description, start, end);
        } else {
            responseMessage = "I'm sorry, but I don't know what that means :-(\n";
            throw new DukeException(responseMessage);
        }
        if (!taskList.contains(newTask)) {
            responseMessage = taskList.addTask(newTask);
        } else {
            responseMessage = "This task is already existed.";
        }
        return responseMessage;
    }
}
