package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

import javafx.stage.Stage;

/**
 * Parser class is used for making sense of all possible user commands.
 *
 * @author Tseng Chen-Yu
 * @version %I%, %G%
 * @since 1.0
 */
public class Parser {
    private final Stage stage;

    public enum CmdType {
        bye,
        list,
        mark,
        unmark,
        delete,
        todo,
        deadline,
        event,
        find
    }

    public Parser(Stage stage) {
        this.stage = stage;
    }

    private boolean isByeCommand(String userInput) {
        return userInput.replaceAll("\\s", "")
                .equals(CmdType.bye.name());
    }

    private boolean isListCommand(String userInput) {
        return userInput.replaceAll("\\s", "")
                .equals(CmdType.list.name());
    }

    private boolean isMarkCommand(String userInput) {
        return (userInput.split(" ")[0])
                .equals(CmdType.mark.name());
    }

    private boolean isUnmarkCommand(String userInput) {
        return (userInput.split(" ")[0])
                .equals(CmdType.unmark.name());
    }

    private boolean isDeleteCommand(String userInput) {
        return userInput.split(" ")[0]
                .equals(CmdType.delete.name());
    }

    private boolean isTodoCommand(String userInput) {
        return userInput.split(" ")[0]
                .equals(CmdType.todo.name());
    }

    private boolean isDeadlineCommand(String userInput) {
        return userInput.split(" ")[0].equals(CmdType.deadline.name());
    }

    private boolean isEventCommand(String userInput) {
        return userInput.split(" ")[0]
                .equals(CmdType.event.name());
    }

    public boolean isFindCommand(String userInput) {
        return userInput.split(" ")[0].equals(CmdType.find.name());
    }

    public String parseCommandWithResponse(String userInput) {
        String resultString = "";
        String errMsg = "";
        try {
            if (isByeCommand(userInput)) {
                resultString = "Bye. Hope to see you again soon!\n";
                stage.close();
            } else if (isListCommand(userInput)) {
                StringBuilder listOfInputs = new StringBuilder();
                for (int i = 0; i < TaskList.getUserTasks().size(); i++) {
                    listOfInputs.append(i + 1)
                            .append(".")
                            .append(TaskList.getUserTasks().get(i)).append("\n");
                }
                resultString = "Here are the tasks in your list:\n"
                        + listOfInputs;
            } else if (isMarkCommand(userInput)) {
                int indexOfFirstSpace = userInput.indexOf(" ");
                if (indexOfFirstSpace == -1 || userInput.substring(indexOfFirstSpace + 1).isBlank()) {
                    errMsg = " ☹ OOPS!!! Please supply a task number you wish to mark as completed.\n";
                    throw new DukeException(errMsg);
                }
                try {
                    int numToMark = Integer.parseInt(userInput.split(" ")[1]);
                    if (numToMark == 0 || (numToMark > TaskList.getUserTasks().size())) {
                        errMsg = " ☹ OOPS!!! Invalid mark selection.\n";
                        throw new DukeException(errMsg);
                    }
                    TaskList.getUserTasks().get(numToMark - 1).setIsDone(true);
                    resultString = "Nice! I've marked this task as done:\n"
                            + TaskList.getUserTasks().get(numToMark - 1) + "\n";
                } catch (NumberFormatException nfe) {
                    errMsg = " ☹ OOPS!!! Please supply a valid task number you wish to mark as completed.\n";

                    throw new DukeException(errMsg);
                }
                Storage.saveTasksToFile(TaskList.getUserTasks());
            } else if (isUnmarkCommand(userInput)) {
                int indexOfFirstSpace = userInput.indexOf(" ");
                if (indexOfFirstSpace == -1 || userInput.substring(indexOfFirstSpace + 1).isBlank()) {
                    errMsg = " ☹ OOPS!!! Please supply a task number you wish to mark as incomplete.\n";
                    throw new DukeException(errMsg);
                }
                try {
                    int numToUnmark = Integer.parseInt(userInput.split(" ")[1]);
                    if (numToUnmark == 0 || (numToUnmark > TaskList.getUserTasks().size())) {
                        errMsg = " ☹ OOPS!!! Invalid unmark selection.\n";
                        throw new DukeException(errMsg);
                    }
                    TaskList.getUserTasks().get(numToUnmark - 1).setIsDone(false);
                    resultString = "OK, I've marked this task as not done yet:\n"
                            + TaskList.getUserTasks().get(numToUnmark - 1) + "\n";
                } catch (NumberFormatException nfe) {
                    errMsg = " ☹ OOPS!!! Please supply a valid task number you wish to mark as incomplete.\n";
                    throw new DukeException(errMsg);
                }
                Storage.saveTasksToFile(TaskList.getUserTasks());
            } else if (isDeleteCommand(userInput)) {
                int indexOfFirstSpace = userInput.indexOf(" ");
                if (indexOfFirstSpace == -1 || userInput.substring(indexOfFirstSpace + 1).isBlank()) {
                    errMsg = " ☹ OOPS!!! Please supply a task number you wish to delete.\n";
                    throw new DukeException(errMsg);
                }
                try {
                    int numToDelete = Integer.parseInt(userInput.split(" ")[1]);
                    if (numToDelete == 0 || (numToDelete > TaskList.getUserTasks().size())) {
                        errMsg = " ☹ OOPS!!! Invalid delete selection.\n";
                        throw new DukeException(errMsg);
                    }
                    resultString = "Noted, I've removed this task: \n"
                            + TaskList.getUserTasks().get(numToDelete - 1) + "\n";
                    TaskList.getUserTasks().remove(numToDelete - 1);
                    resultString += "Now you have " + TaskList.getUserTasks().size() + " tasks in the list.\n";
                } catch (NumberFormatException nfe) {
                    errMsg = " ☹ OOPS!!! Please supply a valid task number you wish to delete.\n";
                    throw new DukeException(errMsg);
                }
                Storage.saveTasksToFile(TaskList.getUserTasks());
            } else if (isTodoCommand(userInput)) {
                int indexOfFirstSpace = userInput.indexOf(" ");
                String taskDescription = userInput.substring(indexOfFirstSpace + 1);
                if (indexOfFirstSpace == -1 || taskDescription.isBlank()) {
                    errMsg = " ☹ OOPS!!! The description of a todo cannot be empty.\n";
                    throw new DukeException(errMsg);
                }
                Todo newTodo = new Todo(taskDescription);
                TaskList.getUserTasks().add(newTodo);
                resultString = "Got it. I've added this task:\n"
                        + newTodo + "\n"
                        + "Now you have " + TaskList.getUserTasks().size() + " tasks in the list.\n";
                Storage.saveTasksToFile(TaskList.getUserTasks());
            } else if (isDeadlineCommand(userInput)) {
                int indexOfBy = userInput.indexOf("/by");
                int indexOfFirstSpace = userInput.indexOf(" ");
                if (indexOfFirstSpace == -1 || indexOfBy == -1) {
                    errMsg = " ☹ OOPS!!! Please include /by followed by the actual deadline.\n";
                    throw new DukeException(errMsg);
                }
                if (indexOfBy + 4 >= userInput.length()) {
                    errMsg =  " ☹ OOPS!!! The deadline specified after /by cannot be empty.\n";
                    throw new DukeException(errMsg);
                }
                String[] s = userInput.substring(indexOfBy + 4).split(" ");
                if (s.length != 2) {
                    errMsg =  " ☹ OOPS!!! The task deadline must be defined as '/by YYYY-MM-DD HH:MM' .\n";
                    throw new DukeException(errMsg);
                }
                String deadlineDate = s[0];
                String deadlineTime = s[1];
                if (indexOfFirstSpace + 1 > indexOfBy - 1) {
                    errMsg = " ☹ OOPS!!! The task description for a deadline cannot be empty.\n";
                    throw new DukeException(errMsg);
                }
                try {
                    LocalDate parsedDate = LocalDate.parse(deadlineDate);
                    Date parsedTime = new SimpleDateFormat("hh:mm").parse(deadlineTime);
                    String taskDescription = userInput.substring(indexOfFirstSpace + 1, indexOfBy - 1);
                    if (taskDescription.isBlank()) {
                        errMsg = " ☹ OOPS!!! The task description for a deadline cannot be empty.\n";
                        throw new DukeException(errMsg);
                    }
                    Deadline newDeadline = new Deadline(taskDescription, parsedDate, parsedTime, false);
                    TaskList.getUserTasks().add(newDeadline);
                    resultString = "Got it. I've added this task:\n"
                            + newDeadline + "\n"
                            + "Now you have " + TaskList.getUserTasks().size() + " tasks in the list.\n";
                    Storage.saveTasksToFile(TaskList.getUserTasks());
                } catch (DateTimeParseException | ParseException e) {
                    errMsg = " ☹ OOPS!!! The task deadline must be defined as '/by YYYY-MM-DD HH:MM' .\n";
                    throw new DukeException(errMsg);
                }
            } else if (isEventCommand(userInput)) {
                int indexOfFrom = userInput.indexOf("/from");
                int indexOfTo = userInput.indexOf("/to");
                int indexOfFirstSpace = userInput.indexOf(" ");
                if (indexOfFirstSpace == -1 || indexOfFrom == -1 || indexOfTo == -1) {
                    errMsg = " ☹ OOPS!!! The event command must contain both /from and /to\n";
                    throw new DukeException(errMsg);
                }
                if (indexOfFrom > indexOfTo) {
                    errMsg = " ☹ OOPS!!! /from cannot be after /to\n";
                    throw new DukeException(errMsg);
                }
                String eventStart = userInput.substring(indexOfFrom + 6, indexOfTo - 1);
                String eventEnd = userInput.substring(indexOfTo + 4);

                String[] eventStartSplitStr = eventStart.split(" ");
                String[] eventEndSplitStr = eventEnd.split(" ");

                if (eventStartSplitStr.length != 2 || eventEndSplitStr.length != 2) {
                    errMsg = " ☹ OOPS!!! The event start and the event end must defined " +
                            "as '/from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM' .\n";
                    throw new DukeException(errMsg);
                }
                String eventStartDate = eventStartSplitStr[0];
                String eventStartTime = eventStartSplitStr[1];
                String eventEndDate = eventEndSplitStr[0];
                String eventEndTime = eventEndSplitStr[1];

                if (indexOfFirstSpace + 1 > indexOfFrom - 1) {
                    errMsg = " ☹ OOPS!!! The event description cannot be empty!\n";
                    throw new DukeException(errMsg);
                }
                try {
                    LocalDate parsedStartDate = LocalDate.parse(eventStartDate);
                    LocalDate parsedEndDate = LocalDate.parse(eventEndDate);
                    Date parsedStartTime = new SimpleDateFormat("hh:mm").parse(eventStartTime);
                    Date parsedEndTime = new SimpleDateFormat("hh:mm").parse(eventEndTime);
                    String taskDescription = userInput.substring(indexOfFirstSpace + 1, indexOfFrom - 1);
                    if (taskDescription.isBlank()) {
                        errMsg = " ☹ OOPS!!! The Event description cannot be empty!\n";
                        throw new DukeException(errMsg);
                    }
                    Event newEvent = new Event(taskDescription, parsedStartDate, parsedStartTime,
                            parsedEndDate, parsedEndTime, false);
                    TaskList.getUserTasks().add(newEvent);
                    resultString = "Got it. I've added this task:\n"
                            + newEvent + "\n"
                            + "Now you have " + TaskList.getUserTasks().size() + " tasks in the list.\n";
                    Storage.saveTasksToFile(TaskList.getUserTasks());
                } catch (DateTimeParseException | ParseException e) {
                    errMsg = " ☹ OOPS!!! The event start and the event end must defined " +
                            "as '/from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM' .\n";
                    throw new DukeException(errMsg);
                }
            } else if (isFindCommand(userInput)) {
                int indexOfFirstSpace = userInput.indexOf(" ");
                String toFind = userInput.substring(indexOfFirstSpace + 1);
                if (indexOfFirstSpace == -1 || toFind.isBlank()) {
                    errMsg = " ☹ OOPS!!! Invalid find, please supply a sequence of characters to find.\n";
                    throw new DukeException(errMsg);
                }
                ArrayList<Task> matches = new ArrayList<>();
                for (int i = 0; i < TaskList.getUserTasks().size(); i++) {
                    if (TaskList.getUserTasks().get(i).getDescription().contains(toFind)) {
                        matches.add(TaskList.getUserTasks().get(i));
                    }
                }
                if (matches.size() == 0) {
                    resultString = "No matching tasks in the list.\n";
                } else {
                    StringBuilder listOfMatches = new StringBuilder();
                    for (int i = 0; i < matches.size(); i++) {
                        listOfMatches.append(i + 1)
                                .append(".")
                                .append(matches.get(i)).append("\n");
                    }
                    resultString = "Here are the matching tasks in your list:\n"
                            + listOfMatches ;
                }
            } else {
                errMsg = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
                throw new DukeException(errMsg);
            }

        } catch (DukeException e) {
            return e.toString();
        }
        return resultString;
    }
}
