package duke;

import duke.exceptions.*;
import duke.task.*;

/**
 * Handles the reading and execution of inputs
 */
public class Parser {

    enum Types { LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, FIND, RESCHEDULE }

    Parser() { }

    /**
     * Reads input from user and splits it into cases based on Types specified
     * in enum
     * @param inputString Input from user
     * @param taskList       List containing all current tasks
     * @param ui             Ui that runs output
     * @return String which contains duke's response to the commands
     * @throws NeroException Throws an exception depending on the exception faced
     */
    String parseCommand(String inputString, TaskList taskList, Ui ui) throws NeroException {
        try {
            String[] input = inputString.split(" ");
            switch (Enum.valueOf(Types.class, input[0].toUpperCase())) {
            case BYE:
                return ui.printExitInstructions();
            case LIST:
                return ui.printTasksMessage() + "\n" + taskList.printTasks();
            case MARK: {
                try {
                    int taskToMark = Integer.parseInt(input[1]) - 1;
                    Task currTask = taskList.get(taskToMark);
                    currTask.setAsDone();
                    return ui.printMarkedTaskMessage(currTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new IncorrectIndexException(taskList.getSize());
                }
            }
            case UNMARK: {
                try {
                    int taskToUnmark = Integer.parseInt(input[1]) - 1;
                    Task currTask = taskList.get(taskToUnmark);
                    currTask.setAsUndone();
                    return ui.printUnmarkedTaskMessage(currTask.toString());
                } catch (IndexOutOfBoundsException e) {
                    throw new IncorrectIndexException(taskList.getSize());
                }
            }
            case TODO:
                int index = inputString.indexOf("todo");
                try {
                    String description = inputString.substring(index + 5);
                    Task newTask = new ToDo(description);
                    taskList.addTask(newTask);
                    return ui.printAddedTasks(newTask.toString(), taskList.getSize());
                } catch (IndexOutOfBoundsException e) {
                    throw new IncorrectToDoException();
                }
            case DEADLINE:
                try {
                    //cleanedString contains [description, duration]
                    String[] cleanedString = cleanDeadline(inputString);
                    Task newTask = new Deadline(cleanedString[0], cleanedString[1]);
                    taskList.addTask(newTask);
                    return ui.printAddedTasks(newTask.toString(), taskList.getSize());
                } catch (IndexOutOfBoundsException e) {
                    throw new IncorrectDeadlineFormatException();
                }
            case EVENT:
                try {
                    //cleanedString contains [description, startDate, endDate]
                    String[] cleanedString = cleanEvent(inputString);
                    Task newTask = new Event(cleanedString[0], cleanedString[1], cleanedString[2]);
                    taskList.addTask(newTask);
                    return ui.printAddedTasks(newTask.toString(), taskList.getSize());
                } catch (IndexOutOfBoundsException e) {
                    throw new IncorrectEventFormatException();
                }
            case DELETE:
                try {
                    int toDelete = Integer.parseInt(input[1]) - 1;
                    Task removedTask = taskList.get(toDelete);
                    taskList.removeTask(toDelete);
                    return ui.printDeletedTasks(removedTask.toString(), taskList.getSize());
                } catch (IndexOutOfBoundsException e) {
                    throw new IncorrectIndexException(taskList.getSize());
                }
            case FIND:
                TaskList newTaskList = taskList.findMatchingTasks(input[1]);
                if (newTaskList.getSize() > 0) {
                    return ui.printMatchingTasks() + "\n" + newTaskList.printTasks();
                } else {
                    return ui.printNoMatchingTasks();
                }
            case RESCHEDULE:
                try {
                    int taskIndex = Integer.parseInt(input[1]) - 1;
                    if (taskIndex >= taskList.getSize() || taskIndex < 0) {
                        throw new IncorrectIndexException(taskList.getSize());
                    }
                    Task currTask = taskList.get(taskIndex);
                    if (currTask instanceof ToDo) {
                        return ui.printFailedReschedule();
                    } else if (currTask instanceof Deadline) {
                        String newDeadline = input[3];
                        Task newTask = new Deadline(currTask.getDescription(), newDeadline);
                        taskList.set(taskIndex, newTask);
                        return ui.printSuccessfulReschedule(newDeadline.toString());
                    } else {
                        String startDate = input[3];
                        String endDate = input[5];
                        Task newTask = new Event(currTask.getDescription(), startDate, endDate);
                        taskList.set(taskIndex, newTask);
                        return ui.printSuccessfulReschedule(newTask.toString());
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new RescheduleException();
                } catch (NumberFormatException e) {
                    throw new RescheduleException();
                }
            default:
                return ui.printCommandNotDetected();
            }
        } catch (IllegalArgumentException e) {
            throw new IncorrectInputException();
        }
    }

    /**
     * Performs string cleaning on input from user for Deadline tasks
     * @param inputString input from user
     * @return array of strings containing [description of task, deadline of task]
     */
    String[] cleanDeadline(String inputString) {
        String[] cleanedString = new String[2];
        String[] splitString = inputString.split("/");
        String description = splitString[0].replace("deadline", "");
        String deadline = splitString[1].replace("by", "");
        cleanedString[0] = description;
        cleanedString[1] = deadline;
        return cleanedString;
    }

    /**
     * Performs string cleaning on input from user for Event tasks
     * @param inputString input from user
     * @return array of strings containing [description of task, start date of task, end date of task]
     */
    String[] cleanEvent(String inputString) {
        String[] cleanedString = new String[3];
        String[] splitString = inputString.split("/");
        String description = splitString[0].replace("event", "");
        String startDate = splitString[1].replace("from", "");
        String endDate = splitString[2].replace("to", "");
        cleanedString[0] = description;
        cleanedString[1] = startDate;
        cleanedString[2] = endDate;
        return cleanedString;
    }
}
