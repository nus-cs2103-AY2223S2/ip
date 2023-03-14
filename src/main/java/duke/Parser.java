package duke;

import task.DeadlineTask;
import task.DurationTask;
import task.EventTask;
import task.Task;
import task.TaskList;
import task.TodoTask;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Parser class that handles inputs made by user.
 */
public class Parser {
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for a parser.
     * @param storage Storage instance of Duke.
     * @param taskList TaskList containing the tasks.
     */
    public Parser(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Returns a boolean, continues running the program if true, terminates it if false.
     * Takes in user input and carries out functionality of the command.
     * @param input Command input by user.
     * @return True for Duke to continue running.
     * @throws DukeException Thrown when unexpected behaviour occurs.
     * @throws IOException Thrown when file system encounters an error.
     */
    public String readInput(String input) throws DukeException, IOException {
        String firstInput = input.split(" ")[0];
        LocalDate currentTime = LocalDate.now();
        try {
            return readCommand(firstInput, currentTime, input);
        } catch (IOException io) {
            throw new DukeException("Something is up with your files it seems");
        } catch (DateTimeParseException dl) {
            throw new DukeException("Beep boop "
                    + "this robot can only understand dates in the form yyyy-mm-dd");
        }
    }

    /**
     * Reads the input command and returns the appropriate replies from Duke.
     * @param input The command word given by user.
     * @param currentTime Current time in real world.
     * @return String representation of Duke's reply to the user's query.
     * @throws DukeException Thrown when unexpected behaviour occurs.
     * @throws IOException Thrown when file system encounters an error.
     */
    private String readCommand(String firstInput
            , LocalDate currentTime, String input) throws DukeException, IOException {
        switch (firstInput) {
        case "list":
            return handleListCommand();
        case "bye":
            return null;
        case "mark":
            return handleMarkCommand(input);
        case "unmark":
            return handleUnmarkCommand(input);
        case "todo":
            return handleTodoCommand(input);
        case "deadline":
            return handleDeadlineCommand(input, currentTime);
        case "event":
            return handleEventCommand(input, currentTime);
        case "duration":
            return handleDurationCommand(input);
        case "delete":
            return handleDeleteCommand(input);
        case "find":
            return handleFindCommand(input);
        default:
            throw new DukeException("Oops I do not recognise this command...");
        }
    }

    /**
     * Handles the case when user uses the list command.
     * @return String representation of current;y stored tasks.
     */
    private String handleListCommand() {
        String listOutput = "Here are the tasks you asked for!\n";
        for (int i = 0; i < taskList.size(); i += 1) {
            int currItem = i + 1;
            listOutput += currItem + ": " + taskList.get(i);
            listOutput += "\n";
        }
        listOutput += "You now have " + taskList.size() + " items in your list.";
        return listOutput;
    }

    /**
     * Marks the target task as done.
     * @param input User's input.
     * @return Duke's reply.
     * @throws DukeException Thrown when unexpected behaviour occurs.
     * @throws IOException Thrown when file system encounters an error.
     */
    private String handleMarkCommand(String input) throws DukeException, IOException {
        if (input.split(" ").length < 2) {
            throw new DukeException("Mark? Mark what?");
        }
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            Task selectedTask = taskList.get(taskIndex);
            selectedTask.check();
            String markOutput = "Done! I've marked this task as done :D\n";
            markOutput += selectedTask;
            storage.save(taskList);
            return markOutput;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops, that task number does not exist");
        }
    }

    /**
     * Unmarks target task.
     * @param input User's input.
     * @return Duke's reply.
     * @throws DukeException Thrown when unexpected behaviour occurs.
     * @throws IOException Thrown when file system encounters an error.
     */
    private String handleUnmarkCommand(String input) throws DukeException, IOException {
        if (input.split(" ").length < 2) {
            throw new DukeException("Unmark? Unmark what?");
        }
        try {
            int unmarkIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            Task unselectedTask = taskList.get(unmarkIndex);
            String unmarkOutput = "This task is apparently not done huh D:\n";
            unselectedTask.unCheck();
            unmarkOutput += unselectedTask;
            storage.save(taskList);
            return unmarkOutput;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops, that task number does not exist");
        }
    }

    /**
     * Handler for when user wants to add a todo task.
     * @param input User's input.
     * @return Duke's reply.
     * @throws DukeException Thrown when unexpected behaviour occurs.
     */
    private String handleTodoCommand(String input) throws DukeException {
        try {
            String todoTaskName = input.substring(5);
            TodoTask todoTask = new TodoTask(todoTaskName);
            return addTask(todoTask, todoTaskName);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            throw new DukeException("Oops, you can't enter an empty task!");
        }
    }

    /**
     * Handler for when user wants to add a deadline task.
     * @param input User's input.
     * @param currentTime Current time in real world.
     * @return Duke's reply.
     * @throws DukeException Thrown when unexpected behaviour occurs.
     * @throws IOException Thrown when file system encounters an error.
     */
    private String handleDeadlineCommand(String input
            , LocalDate currentTime) throws DukeException, IOException {
        String deadlineDetails = input.substring(9);
        if (deadlineDetails.split(" /by ").length < 2) {
            throw new DukeException("Wait a minute, "
                    + "you're missing something! Could be the name or date...");
        }
        String deadlineName = deadlineDetails.split(" /by ")[0];
        String deadlineDateStr = deadlineDetails.split(" /by ")[1];
        LocalDate deadlineDate = LocalDate.parse(deadlineDateStr);
        if (deadlineDate.isBefore(currentTime)) {
            throw new DukeException("Wait! Time travelling is not in my kit!");
        }
        DeadlineTask deadlineTask = new DeadlineTask(deadlineName, deadlineDate);
        return addTask(deadlineTask, deadlineName);
    }

    /**
     * Handler for when user wants to add an event task.
     * @param input User's input.
     * @param currentTime Current time in real world.
     * @return Duke's reply.
     * @throws DukeException Thrown when unexpected behaviour occurs.
     * @throws IOException Thrown when file system encounters an error.
     */
    private String handleEventCommand(String input
            , LocalDate currentTime) throws DukeException, IOException {
        String eventDetails = input.substring(6);
        if (eventDetails.split(" /from ").length < 2
                || eventDetails.split(" /to ").length < 2) {
            throw new DukeException("Hold up, you might be missing something here buddy!");
        }
        String eventName = eventDetails.split(" /from ")[0];
        String eventDate = eventDetails.split(" /from ")[1];
        String eventStartStr = eventDate.split(" /to ")[0];
        String eventEndStr = eventDate.split(" /to ")[1];
        LocalDate eventStart = LocalDate.parse(eventStartStr);
        LocalDate eventEnd = LocalDate.parse(eventEndStr);
        if (eventStart.isBefore(currentTime) || eventEnd.isBefore(currentTime)) {
            throw new DukeException("Wait! Time travelling is not in my kit!");
        }
        if (eventStart.isAfter(eventEnd)) {
            throw new DukeException("Ohhh I wasn't aware time travels backwards for you :O");
        }
        EventTask eventTask = new EventTask(eventName, eventStart, eventEnd);
        return addTask(eventTask, eventName);
    }

    /**
     * Handler for when user wants to add a duration task.
     * @param input User's input.
     * @return Duke's reply.
     * @throws DukeException Thrown when unexpected behaviour occurs.
     * @throws IOException Thrown when file system encounters an error.
     */
    private String handleDurationCommand(String input) throws DukeException, IOException {
        String durationDetails = input.substring(9);
        if (durationDetails.split(" /for ").length < 2) {
            throw new DukeException("Hold up, you might be missing something here buddy!");
        }
        String durationName = durationDetails.split(" /for ")[0];
        String durationLength = durationDetails.split(" /for ")[1];
        int hours = Integer.parseInt(durationLength.split(":")[0]);
        int mins = Integer.parseInt(durationLength.split(":")[1]);
        if (hours <= 0 && mins <= 0) {
            throw new DukeException("Wait, you're giving yourself no time to do this?");
        }
        if (mins > 59 || mins < 0) {
            throw new DukeException("Minutes cannot be more than 59 or less than 0 dear child");
        }
        DurationTask durationTask = new DurationTask(durationName, hours, mins);
        return addTask(durationTask, durationName);
    }

    /**
     * Handler for when user wants to delete a task.
     * @param input User's input.
     * @return Duke's reply.
     * @throws DukeException Thrown when unexpected behaviour occurs.
     */
    private String handleDeleteCommand(String input) throws DukeException {
        if (input.split(" ").length < 2) {
            throw new DukeException("Delete? Delete what?");
        }
        try {
            int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            Task deleteTask = taskList.get(deleteIndex);
            String deleteOutput = "Done! " + deleteTask + " has been deleted for good.";
            taskList.remove(deleteIndex);
            storage.save(taskList);
            return deleteOutput;
        } catch (IndexOutOfBoundsException | IOException e) {
            throw new DukeException("Oops, that task number does not exist");
        }
    }

    /**
     * Handler for when user wants to find tasks matching the search query.
     * @param input User's input.
     * @return List of tasks matching the search.
     * @throws DukeException Thrown when unexpected behaviour occurs.
     */
    private String handleFindCommand(String input) throws DukeException {
        String keyword = input.substring(5);
        if (keyword.length() <= 0) {
            throw new DukeException("Err... There's nothing to find here");
        }
        ArrayList<Task> filteredList = taskList.filterByKeyword(keyword);
        String findOutput = "";
        for (int i = 0; i < filteredList.size(); i += 1) {
            int currItem = i + 1;
            findOutput += (currItem + ": " + filteredList.get(i));
            findOutput += "\n";
        }
        return findOutput;
    }

    private String addTask(Task task, String name) throws DukeException, IOException {
        taskList.add(task);
        storage.save(taskList);
        return "Item added: " + name;
    }
}
