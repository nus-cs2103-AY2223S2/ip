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
        HELP,
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND
    }

    public Parser(Stage stage) {
        this.stage = stage;
    }

    public String parseCommandWithResponse(String userInput) {
        String resultString = "";
        try {
            if (isHelpCommand(userInput)) {
                resultString = processHelpCommand();
            } else if (isByeCommand(userInput)) {
                resultString = "Bye. Hope to see you again soon!\n";
                stage.close();
            } else if (isListCommand(userInput)) {
                resultString = processListCommand();
            } else if (isMarkCommand(userInput)) {
                resultString = processMarkCommand(userInput);
            } else if (isUnmarkCommand(userInput)) {
                resultString = processUnmarkCommand(userInput);
            } else if (isDeleteCommand(userInput)) {
                resultString = processDeleteCommand(userInput);
            } else if (isTodoCommand(userInput)) {
                resultString = processTodoCommand(userInput);
            } else if (isDeadlineCommand(userInput)) {
                resultString = processDeadlineCommand(userInput);
            } else if (isEventCommand(userInput)) {
                resultString = processEventCommand(userInput);
            } else if (isFindCommand(userInput)) {
                resultString = processFindCommand(userInput);
            } else {
                throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        } catch (DukeException e) {
            return e.toString();
        }
        return resultString;
    }

    private boolean isHelpCommand(String userInput) {
        return userInput.replaceAll("\\s", "").toUpperCase()
                .equals(CmdType.HELP.name());
    }

    private boolean isByeCommand(String userInput) {
        return userInput.replaceAll("\\s", "").toUpperCase()
                .equals(CmdType.BYE.name());
    }

    private boolean isListCommand(String userInput) {
        return userInput.replaceAll("\\s", "").toUpperCase()
                .equals(CmdType.LIST.name());
    }

    private boolean isMarkCommand(String userInput) {
        return (userInput.split(" ")[0]).toUpperCase()
                .equals(CmdType.MARK.name());
    }

    private boolean isUnmarkCommand(String userInput) {
        return (userInput.split(" ")[0]).toUpperCase()
                .equals(CmdType.UNMARK.name());
    }

    private boolean isDeleteCommand(String userInput) {
        return userInput.split(" ")[0].toUpperCase()
                .equals(CmdType.DELETE.name());
    }

    private boolean isTodoCommand(String userInput) {
        return userInput.split(" ")[0].toUpperCase()
                .equals(CmdType.TODO.name());
    }

    private boolean isDeadlineCommand(String userInput) {
        return userInput.split(" ")[0].toUpperCase()
                .equals(CmdType.DEADLINE.name());
    }

    private boolean isEventCommand(String userInput) {
        return userInput.split(" ")[0].toUpperCase()
                .equals(CmdType.EVENT.name());
    }

    private boolean isFindCommand(String userInput) {
        return userInput.split(" ")[0].toUpperCase()
                .equals(CmdType.FIND.name());
    }

    private String processHelpCommand() {
        String header = "Duke Command Formats:\n";
        String todoCommandFormat = "1) Add a todo task: add <task description> \n";
        String listCommandFormat = "2) List all tasks: list \n";
        String markCommandFormat = "3) Mark a task as completed: mark <task number> \n";
        String unmarkCommandFormat = "4) Unmark a task to incompleted: unmark <task number> \n";
        String deleteCommandFormat = "5) Delete a task: delete <task number> \n";
        String deadlineCommandFormat = "6) Add a task with end deadline: deadline <task description> " +
                "/by <YYYY-MM-DD HH:MM> \n";
        String eventCommandFormat = "7) Add a task with start and end date: event <task description> " +
                "/from <YYYY-MM-DD HH:MM> /to <YYYY-MM-DD HH:MM> \n";
        String findCommandFormat = "8) Find a task: find <task description>\n";
        StringBuilder builder = new StringBuilder();
        builder.append(header)
                .append(todoCommandFormat)
                .append(listCommandFormat)
                .append(markCommandFormat)
                .append(unmarkCommandFormat)
                .append(deleteCommandFormat)
                .append(deadlineCommandFormat)
                .append(eventCommandFormat)
                .append(findCommandFormat);
        return builder.toString();
    }

    private String processListCommand() {
        StringBuilder listOfInputs = new StringBuilder();
        for (int i = 0; i < TaskList.getUserTasks().size(); i++) {
            listOfInputs.append(i + 1)
                    .append(".")
                    .append(TaskList.getUserTasks().get(i)).append("\n");
        }
        return "Here are the tasks in your list:\n"
                + listOfInputs;
    }

    private String processMarkCommand(String userInput) throws DukeException{
        String resultString = "";
        int indexOfFirstSpace = userInput.indexOf(" ");
        if (indexOfFirstSpace == -1 || userInput.substring(indexOfFirstSpace + 1).isBlank()) {
            throw new DukeException(" OOPS!!! Please supply a task number you wish to mark as completed.\n");
        }
        try {
            int numToMark = Integer.parseInt(userInput.split(" ")[1]);
            if (numToMark == 0 || (numToMark > TaskList.getUserTasks().size())) {
                throw new DukeException(" OOPS!!! Invalid mark selection.\n");
            }
            TaskList.getUserTasks().get(numToMark - 1).setIsDone(true);
            resultString = "Nice! I've marked this task as done:\n"
                    + TaskList.getUserTasks().get(numToMark - 1) + "\n";
        } catch (NumberFormatException nfe) {
            throw new DukeException(" OOPS!!! Please supply a valid task number you wish to mark as completed.\n");
        }
        Storage.saveTasksToFile(TaskList.getUserTasks());
        return resultString;
    }

    private String processUnmarkCommand(String userInput) throws DukeException{
        String resultString = "";
        int indexOfFirstSpace = userInput.indexOf(" ");
        if (indexOfFirstSpace == -1 || userInput.substring(indexOfFirstSpace + 1).isBlank()) {
            throw new DukeException(" OOPS!!! Please supply a task number you wish to mark as incomplete.\n");
        }
        try {
            int numToUnmark = Integer.parseInt(userInput.split(" ")[1]);
            if (numToUnmark == 0 || (numToUnmark > TaskList.getUserTasks().size())) {
                throw new DukeException(" OOPS!!! Invalid unmark selection.\n");
            }
            TaskList.getUserTasks().get(numToUnmark - 1).setIsDone(false);
            resultString = "OK, I've marked this task as not done yet:\n"
                    + TaskList.getUserTasks().get(numToUnmark - 1) + "\n";
        } catch (NumberFormatException nfe) {
            throw new DukeException(" OOPS!!! Please supply a valid task number you wish to mark as incomplete.\n");
        }
        Storage.saveTasksToFile(TaskList.getUserTasks());
        return resultString;
    }

    private String processDeleteCommand(String userInput) throws DukeException{
        String resultString = "";
        int indexOfFirstSpace = userInput.indexOf(" ");
        if (indexOfFirstSpace == -1 || userInput.substring(indexOfFirstSpace + 1).isBlank()) {
            throw new DukeException(" OOPS!!! Please supply a task number you wish to delete.\n");
        }
        try {
            int numToDelete = Integer.parseInt(userInput.split(" ")[1]);
            if (numToDelete == 0 || (numToDelete > TaskList.getUserTasks().size())) {
                throw new DukeException(" OOPS!!! Invalid delete selection.\n");
            }
            resultString = "Noted, I've removed this task: \n"
                    + TaskList.getUserTasks().get(numToDelete - 1) + "\n";
            TaskList.getUserTasks().remove(numToDelete - 1);
            resultString += "Now you have " + TaskList.getUserTasks().size() + " tasks in the list.\n";
        } catch (NumberFormatException nfe) {

            throw new DukeException( " OOPS!!! Please supply a valid task number you wish to delete.\n");
        }
        Storage.saveTasksToFile(TaskList.getUserTasks());
        return resultString;
    }

    private String processTodoCommand(String userInput) throws DukeException {
        String resultString = "";
        int indexOfFirstSpace = userInput.indexOf(" ");
        String taskDescription = userInput.substring(indexOfFirstSpace + 1);
        if (indexOfFirstSpace == -1 || taskDescription.isBlank()) {
            throw new DukeException(" OOPS!!! The description of a todo cannot be empty.\n");
        }
        Todo newTodo = new Todo(taskDescription);
        TaskList.getUserTasks().add(newTodo);
        resultString = "Got it. I've added this task:\n"
                + newTodo + "\n"
                + "Now you have " + TaskList.getUserTasks().size() + " tasks in the list.\n";
        Storage.saveTasksToFile(TaskList.getUserTasks());
        return resultString;
    }

    private String processDeadlineCommand(String userInput) throws DukeException {
        String resultString = "";
        int indexOfBy = userInput.indexOf("/by");
        int indexOfFirstSpace = userInput.indexOf(" ");
        if (indexOfFirstSpace == -1 || indexOfBy == -1) {
            throw new DukeException(" OOPS!!! Please include /by followed by the actual deadline.\n");
        }
        if (indexOfBy + 4 >= userInput.length()) {
            throw new DukeException(" OOPS!!! The deadline specified after /by cannot be empty.\n");
        }
        String[] s = userInput.substring(indexOfBy + 4).split(" ");
        if (s.length != 2) {
            throw new DukeException(" OOPS!!! The task deadline must be defined as '/by YYYY-MM-DD HH:MM' .\n");
        }
        String deadlineDate = s[0];
        String deadlineTime = s[1];
        if (indexOfFirstSpace + 1 > indexOfBy - 1) {
            throw new DukeException(" OOPS!!! The task description for a deadline cannot be empty.\n");
        }
        try {
            LocalDate parsedDate = LocalDate.parse(deadlineDate);
            Date parsedTime = new SimpleDateFormat("hh:mm").parse(deadlineTime);
            String taskDescription = userInput.substring(indexOfFirstSpace + 1, indexOfBy - 1);
            if (taskDescription.isBlank()) {
                throw new DukeException(" OOPS!!! The task description for a deadline cannot be empty.\n");
            }
            Deadline newDeadline = new Deadline(taskDescription, parsedDate, parsedTime, false);
            TaskList.getUserTasks().add(newDeadline);
            resultString = "Got it. I've added this task:\n"
                    + newDeadline + "\n"
                    + "Now you have " + TaskList.getUserTasks().size() + " tasks in the list.\n";
            Storage.saveTasksToFile(TaskList.getUserTasks());
        } catch (DateTimeParseException | ParseException e) {
            throw new DukeException(" OOPS!!! The task deadline must be defined as '/by YYYY-MM-DD HH:MM' .\n");
        }
        return resultString;
    }

    private String processEventCommand(String userInput) throws DukeException {
        String resultString = "";
        int indexOfFrom = userInput.indexOf("/from");
        int indexOfTo = userInput.indexOf("/to");
        int indexOfFirstSpace = userInput.indexOf(" ");
        if (indexOfFirstSpace == -1 || indexOfFrom == -1 || indexOfTo == -1) {
            throw new DukeException(" OOPS!!! The event command must contain both /from and /to\n");
        }
        if (indexOfFrom > indexOfTo) {
            throw new DukeException(" OOPS!!! /from cannot be after /to\n");
        }
        String eventStart = userInput.substring(indexOfFrom + 6, indexOfTo - 1);
        String eventEnd = userInput.substring(indexOfTo + 4);
        String[] eventStartSplitStr = eventStart.split(" ");
        String[] eventEndSplitStr = eventEnd.split(" ");

        if (eventStartSplitStr.length != 2 || eventEndSplitStr.length != 2) {
            throw new DukeException(" OOPS!!! The event start and the event end must defined " +
                    "as '/from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM' .\n");
        }
        String eventStartDate = eventStartSplitStr[0];
        String eventStartTime = eventStartSplitStr[1];
        String eventEndDate = eventEndSplitStr[0];
        String eventEndTime = eventEndSplitStr[1];

        if (indexOfFirstSpace + 1 > indexOfFrom - 1) {
            throw new DukeException(" OOPS!!! The event description cannot be empty!\n");
        }
        try {
            LocalDate parsedStartDate = LocalDate.parse(eventStartDate);
            LocalDate parsedEndDate = LocalDate.parse(eventEndDate);
            Date parsedStartTime = new SimpleDateFormat("hh:mm").parse(eventStartTime);
            Date parsedEndTime = new SimpleDateFormat("hh:mm").parse(eventEndTime);
            String taskDescription = userInput.substring(indexOfFirstSpace + 1, indexOfFrom - 1);
            if (taskDescription.isBlank()) {
                throw new DukeException(" OOPS!!! The Event description cannot be empty!\n");
            }
            Event newEvent = new Event(taskDescription, parsedStartDate, parsedStartTime,
                    parsedEndDate, parsedEndTime, false);
            TaskList.getUserTasks().add(newEvent);
            resultString = "Got it. I've added this task:\n"
                    + newEvent + "\n"
                    + "Now you have " + TaskList.getUserTasks().size() + " tasks in the list.\n";
            Storage.saveTasksToFile(TaskList.getUserTasks());
        } catch (DateTimeParseException | ParseException e) {
            throw new DukeException(" OOPS!!! The event start and the event end must defined " +
                    "as '/from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM' .\n");
        }
        return resultString;
    }

    private String processFindCommand(String userInput) throws DukeException{
        String resultString = "";
        int indexOfFirstSpace = userInput.indexOf(" ");
        String toFind = userInput.substring(indexOfFirstSpace + 1);
        if (indexOfFirstSpace == -1 || toFind.isBlank()) {
            throw new DukeException(" OOPS!!! Invalid find, please supply a sequence of characters to find.\n");
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
        return resultString;
    }
}
