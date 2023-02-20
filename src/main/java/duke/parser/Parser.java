package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.DialogBox;
import duke.ui.Ui;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * duke.parser.Parser class encapsulates the logic implementation of the chatbot.
 */
public class Parser {
    private TaskList taskList;

    /**
     * Initializes a Parser object referring to a TaskList argument object containing the list of tasks
     * using on chatbot.
     *
     * @param taskList TaskList object encapsulate all tasks' information.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Handle the user input and display it back to the dialogContainer.
     *
     * @param userInput The input user typed in.
     * @param dialogContainer The dialog container.
     * @param dukeImg The image represents duke.
     * @param userImg The image represnets user.
     */
    public void handleUserInput(TextField userInput, VBox dialogContainer, ImageView dukeImg, ImageView userImg) {
        Label userText = new Label(userInput.getText());
        String responseMessage = "";
        try {
            responseMessage = doAndGetResponse(userInput.getText());
        } catch (DukeException e) {
            responseMessage = e.getMessage();
        }

        Label dukeText = new Label(responseMessage);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, dukeImg),
                DialogBox.getDukeDialog(dukeText, userImg)
        );
        userInput.clear();
    }

    /**
     * Handles the input from the user, do the corresponding actions and return the response.
     *
     * @param command The input from user.
     * @return Returns A message responsed by chatbot for the argument user input.
     * @throws DukeException If the user input is not in the right format.
     */
    public String doAndGetResponse(String command) throws DukeException {

        //1st check for 2 most general command
        if (command.equals("bye")) {
            return Ui.getOutroMessage();
        } else if (command.equals("list")) {
            return Ui.getTaskListMessage(taskList);
        } else if (command.equals("help")) {
            return Ui.getHelpResponse();
        }

        //2nd check for interactive command related to task
        if (command.startsWith("mark ")) {
            return taskList.mark(command);
        } else if (command.startsWith("unmark ")) {
            return taskList.unmark(command);
        } else if (command.startsWith("delete ")) {
            return taskList.delete(command);
        } else if (command.startsWith("find ")) {
            return taskList.find(command);
        }

        //3rd check for adding task command
        return getAddingTaskResponse(command);
    }

    private String getAddingTaskResponse(String command) throws DukeException {
        String description = command;
        Task newTask;
        String responseMessage = "";

        if (command.startsWith("todo")) {
            newTask = parseToDo(description);
        } else if (command.startsWith("deadline")) {
            newTask = parseDeadline(description);
        } else if (command.startsWith("event")) {
            newTask = parseEvent(description);
        } else {
            responseMessage = "I'm sorry, but I don't know what that means :-(\n";
            throw new DukeException(responseMessage);
        }

        boolean isAlreadyExisted = taskList.contains(newTask);
        if (!isAlreadyExisted) {
            responseMessage = taskList.addTask(newTask);
        } else {
            responseMessage = "This task is already existed.";
        }
        return responseMessage;
    }

    private Task parseToDo(String description) throws DukeException {
        if (description.length() <= 5) {
            String responseMessage = "The description of a todo cannot be empty.\n";
            throw new DukeException(responseMessage);
        }
        return new ToDo(description.substring(5));
    }

    private Task parseDeadline(String description) throws DukeException {
        if (description.length() <= 9) {
            String responseMessage = "The description of a deadline cannot be empty.\n";
            throw new DukeException(responseMessage);
        }

        LocalDate by;
        try {
             by = LocalDate.parse(description.substring(description.indexOf(" /by ") + 5),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            description = description.substring(9, description.indexOf(" /by "));
        } catch (Exception e) {
            String responseMessage = "You are adding a Deadline task with wrong format :(. Type \"help\""
                    + "to see more about my command syntax.";
            throw new DukeException(responseMessage);
        }
        return new Deadline(description, by);
    }

    private Task parseEvent(String description) throws DukeException {
        if (description.length() <= 6) {
            String responseMessage = "The description of an event cannot be empty:\n";
            throw new DukeException(responseMessage);
        }

        LocalDate start;
        LocalDate end;
        try {
            start = LocalDate.parse(description.substring(description.indexOf(" /from ") + 7,
                    description.indexOf(" /to ")), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            end = LocalDate.parse(description.substring(description.indexOf(" /to ") + 5),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            description = description.substring(6, description.indexOf(" /from "));
        } catch (Exception e) {
            String responseMessage = "You are adding an event task with wrong format :(. Type \"help\""
                    + "to see more about my command syntax.";
            throw new DukeException(responseMessage);
        }
        return new Event(description, start, end);
    }

}
