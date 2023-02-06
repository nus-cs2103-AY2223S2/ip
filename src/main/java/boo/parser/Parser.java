package boo.parser;

import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

import boo.command.ByeCommand;
import boo.command.Command;
import boo.command.DeadlineCommand;
import boo.command.DeleteCommand;
import boo.command.EventCommand;
import boo.command.ExceptionCommand;
import boo.command.FindCommand;
import boo.command.HelpCommand;
import boo.command.InvalidCommand;
import boo.command.ListCommand;
import boo.command.MarkCommand;
import boo.command.OnCommand;
import boo.command.ToDoCommand;
import boo.command.UnmarkCommand;
import boo.datetime.DateTime;
import boo.exception.DukeException;
import boo.storage.Storage;
import boo.tasklist.TaskList;

/**
 * Represents a parser that will parse and process commands entered by the user into the chatbot.
 */
public class Parser {
    /** The task list required to check whether command is valid. */
    private TaskList tasks;

    /**
     * Constructs a {@code Parser} instance.
     *
     * @param tasks Task list of the user
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Processes command to determine if it is in the format of any valid commands,
     * and checks for invalid input.
     *
     * @return the type of command given by the user.
     */
    public CommandType parseRawCommand(String rawCommand) {
        //Single word commands
        switch (rawCommand) {
        case "bye":
            return CommandType.BYE;
        case "list":
            return CommandType.LIST;
        case "help":
            return CommandType.HELP;
        default:
            //Do nothing
        }

        //Multiple word commands
        String[] inputArray = rawCommand.split(" ");
        String firstWord = inputArray[0];

        switch (firstWord) {
        case "mark":
            try {
                if (inputArray.length != 2) {
                    throw new DukeException("The mark command must be followed by a single number.");
                }
                if (!isInteger(inputArray[1])) {
                    throw new DukeException("The mark command must be followed by a single integer.");
                }
                int indexOfTask = Integer.parseInt(inputArray[1]) - 1;
                if (!(indexOfTask <= tasks.getSizeOfTaskList() - 1 && indexOfTask >= 0)) {
                    throw new DukeException("Please enter a valid task number. You currently have "
                           + tasks.getSizeOfTaskList() + " tasks.");
                }
                CommandType ctMark = CommandType.MARK;
                ctMark.setIndex(indexOfTask);
                return ctMark;
            } catch (DukeException dukeException) {
                CommandType ctException = CommandType.EXCEPTION;
                ctException.setExceptionMessage(dukeException.getMessage());
                return ctException;
            }
        case "unmark":
            try {
                if (inputArray.length != 2) {
                    throw new DukeException("The unmark command must be followed by a single number.");
                }
                if (!isInteger(inputArray[1])) {
                    throw new DukeException("The unmark command must be followed by a single integer.");
                }
                int indexOfTask = Integer.parseInt(inputArray[1]) - 1;
                if (!(indexOfTask <= tasks.getSizeOfTaskList() - 1 && indexOfTask >= 0)) {
                    throw new DukeException("Please enter a valid task number. You currently have "
                            + tasks.getSizeOfTaskList() + " tasks.");
                }
                CommandType ctUnmark = CommandType.UNMARK;
                ctUnmark.setIndex(indexOfTask);
                return ctUnmark;
            } catch (DukeException dukeException) {
                CommandType ctException = CommandType.EXCEPTION;
                ctException.setExceptionMessage(dukeException.getMessage());
                return ctException;
            }
        case "delete":
            try {
                if (inputArray.length != 2) {
                    throw new DukeException("The delete command must be followed by a single number.");
                }
                if (!isInteger(inputArray[1])) {
                    throw new DukeException("The delete command must be followed by a single integer.");
                }
                int indexOfTask = Integer.parseInt(inputArray[1]) - 1;
                if (!(indexOfTask <= tasks.getSizeOfTaskList() - 1 && indexOfTask >= 0)) {
                    throw new DukeException("Please enter a valid task number. You currently have "
                            + tasks.getSizeOfTaskList() + " tasks.");
                }
                CommandType ctDelete = CommandType.DELETE;
                ctDelete.setIndex((indexOfTask));
                return ctDelete;
            } catch (DukeException dukeException) {
                CommandType ctException = CommandType.EXCEPTION;
                ctException.setExceptionMessage(dukeException.getMessage());
                return ctException;
            }
        case "todo":
            try {
                if (inputArray.length == 1) {
                    throw new DukeException("The todo command cannot be left blank.");
                }
                int indexOfType = rawCommand.indexOf("todo");
                String taskName = rawCommand.substring(indexOfType + 5);
                CommandType ctToDo = CommandType.TODO;
                ctToDo.setTaskName(taskName);
                return ctToDo;
            } catch (DukeException dukeException) {
                CommandType ctException = CommandType.EXCEPTION;
                ctException.setExceptionMessage(dukeException.getMessage());
                return ctException;
            }
        case "deadline":
            try {
                int indexOfType = rawCommand.indexOf("deadline");
                if (indexOfType + 8 > rawCommand.length() - 1) {
                    throw new DukeException("The deadline command cannot be left blank.");
                }
                int indexOfBy = rawCommand.indexOf("/by");
                if (indexOfBy == -1) {
                    throw new DukeException("The deadline cannot be left blank.");
                }
                //deadline/by
                if (indexOfType + 8 == indexOfBy) {
                    throw new DukeException("There seems to be a missing task name.");
                }
                if (indexOfBy + 4 > rawCommand.length() - 1) {
                    throw new DukeException("The deadline cannot be left blank.");
                }
                if (indexOfType + 9 > indexOfBy - 1) {
                    throw new DukeException("There seems to be a missing task name.");
                }
                String taskName = rawCommand.substring(indexOfType + 9, indexOfBy - 1);
                String deadlineOfTask;
                if (rawCommand.charAt(indexOfBy + 3) == ' ') {
                    deadlineOfTask = rawCommand.substring(indexOfBy + 4);
                } else {
                    deadlineOfTask = rawCommand.substring(indexOfBy + 3);
                }
                if (taskName.isBlank()) {
                    throw new DukeException("The task name cannot be left blank.");
                }
                if (deadlineOfTask.isBlank()) {
                    throw new DukeException("The deadline cannot be left blank.");
                }
                Temporal deadlineObject = DateTime.getDateTimeObject(deadlineOfTask);
                CommandType ctDeadline = CommandType.DEADLINE;
                ctDeadline.setTaskName(taskName);
                ctDeadline.setDeadline(deadlineOfTask);
                return ctDeadline;

            } catch (DukeException dukeException) {
                CommandType ctException = CommandType.EXCEPTION;
                ctException.setExceptionMessage(dukeException.getMessage());
                return ctException;
            } catch (DateTimeParseException dateTimeException) {
                CommandType ctException = CommandType.EXCEPTION;
                ctException.setExceptionMessage(
                        "Please check that you entered a valid date, and that the date should be in "
                        + "the format of\nyyyy-MM-dd hh:mm or yyyy-MM-dd.");
                return ctException;
            }
        case "event":
            try {
                int indexOfType = rawCommand.indexOf("event");
                if (indexOfType + 5 > rawCommand.length() - 1) {
                    throw new DukeException("The event command cannot be left blank.");
                }

                int indexOfFrom = rawCommand.indexOf("/from");
                if (indexOfFrom == -1) {
                    throw new DukeException("There seems to be a missing from date.");
                }

                int indexOfTo = rawCommand.indexOf("/to");
                if (indexOfTo == -1) {
                    throw new DukeException("There seems to be a missing to date.");
                }

                //Check taskName
                if ((indexOfType + 6 > indexOfFrom - 1)) {
                    throw new DukeException("There seems to be a missing task name.");
                }

                String taskName = rawCommand.substring(indexOfType + 6, indexOfFrom - 1);
                if (taskName.isBlank()) {
                    throw new DukeException("The task name cannot be left blank.");
                }

                //Check startDate
                if (indexOfFrom + 6 > indexOfTo - 1) {
                    throw new DukeException("There seems to be a missing start date.");
                }

                String startDate = rawCommand.substring(indexOfFrom + 6, indexOfTo - 1);
                if (startDate.isBlank()) {
                    throw new DukeException("The start date cannot be left blank.");
                }

                //Check endDate
                if (indexOfTo + 4 > rawCommand.length() - 1) {
                    throw new DukeException("There seems to be a missing end date.");
                }

                String endDate = rawCommand.substring(indexOfTo + 4);
                if (endDate.isBlank()) {
                    throw new DukeException("The end date cannot be left blank.");
                }

                //Create new event task
                Temporal start = DateTime.getDateTimeObject(startDate);
                Temporal end = DateTime.getDateTimeObject(endDate);

                if (!DateTime.isValidDuration(start, end)) {
                    throw new DukeException("Start date must be before end date.");
                }

                CommandType ctEvent = CommandType.EVENT;
                ctEvent.setTaskName(taskName);
                ctEvent.setStartDate(startDate);
                ctEvent.setEndDate(endDate);
                return ctEvent;
            } catch (DukeException dukeException) {
                CommandType ctException = CommandType.EXCEPTION;
                ctException.setExceptionMessage(dukeException.getMessage());
                return ctException;
            } catch (DateTimeParseException dateTimeException) {
                CommandType ctException = CommandType.EXCEPTION;
                ctException.setExceptionMessage(
                        "Please check that you entered a valid date, and that the date should be in "
                                + "the format of\nyyyy-MM-dd hh:mm or yyyy-MM-dd.");
                return ctException;
            }
        case "on":
            try {
                String dateString = rawCommand.substring(3);
                if (dateString.equals("")) {
                    throw new DukeException("The date cannot be left blank.");
                }
                DateTime.getDateTimeObject(dateString);
                CommandType ctOn = CommandType.ON;
                ctOn.setOnDate(dateString);
                return ctOn;
            } catch (DukeException dukeException) {
                CommandType ctException = CommandType.EXCEPTION;
                ctException.setExceptionMessage(dukeException.getMessage());
                return ctException;
            } catch (DateTimeParseException dateTimeException) {
                CommandType ctException = CommandType.EXCEPTION;
                ctException.setExceptionMessage(
                        "Please check that you entered a valid date, and that the date should be in "
                                + "the format of\nyyyy-MM-dd hh:mm or yyyy-MM-dd.");
                return ctException;
            }
        case "find":
            CommandType ctFind = CommandType.FIND;
            ctFind.setKeyPhrase(rawCommand.substring(5));
            return ctFind;
        default:
            return CommandType.INVALID;
        }
    }

    /**
     * Returns the appropriate Command object associated with the CommandType.
     *
     * @return the Command object for the given CommandType.
     */
    public Command parseCommandType(CommandType command, TaskList tasks, Storage storage) {
        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand(tasks);
        case MARK:
            return new MarkCommand(command.getIndex(), tasks, storage);
        case UNMARK:
            return new UnmarkCommand(command.getIndex(), tasks, storage);
        case TODO:
            return new ToDoCommand(command.getTaskName(), tasks, storage);
        case DEADLINE:
            return new DeadlineCommand(command.getTaskName(), command.getDeadline(), tasks, storage);
        case EVENT:
            return new EventCommand(command.getTaskName(), command.getStartDate(), command.getEndDate(),
                    tasks, storage);
        case DELETE:
            return new DeleteCommand(command.getIndex(), tasks, storage);
        case ON:
            return new OnCommand(command.getOnDate(), tasks);
        case HELP:
            return new HelpCommand();
        case FIND:
            return new FindCommand(command.getKeyPhrase(), tasks);
        case INVALID:
            return new InvalidCommand();
        default:
            return new ExceptionCommand(command.getExceptionMessage());
        }
    }

    /**
     * Checks if a string can be converted into an {@code Integer}.
     *
     * @param stringToCheck {@code String} to check whether the conversion is possible.
     * @return true if it can be converted, else return false.
     */
    public boolean isInteger(String stringToCheck) {
        try {
            int intVersion = Integer.parseInt(stringToCheck);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        return true;
    }

}
