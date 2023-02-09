package duke.message;

import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidTodoException;
import duke.exception.InvalidEventException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a Duke message generator. A MessageGenerator object has
 * associated methods to generate messages in response to tasks.
 */
public class MessageGenerator {

    TaskList taskList;
    Storage storage;

    public MessageGenerator(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    private Task processMark(String message) {
        String[] messageSplit = message.split(" ");
        String action = messageSplit[0];
        int taskNum = Integer.parseInt(messageSplit[1]);

        this.taskList.markTask(action, taskNum);

        // Mark task in storage
        this.storage.markTask(message);

        return this.taskList.getTask(taskNum);
    }

    /**
     * Returns response message from Duke, in response to user's task's status
     * and task content.
     *
     * @param status Status of message.
     * @param task   Task object representing user's task input.
     * @return String consisting of the header, associated task and end.
     */
    String generateTaskMessage(MessageStatus status, Task task) {
        String heading = "";
        String end = "";

        switch (status) {
        case MARK:
            heading = task.getDoneStatus()
                    ? "Nice! I've marked this task as done:"
                    : "OK, I've marked this task as not done yet:";
            break;
        case ADD:
            heading = "Got it. I've added this task:";
            end = String.format("\nNow you have %d tasks in the list.", this.taskList.getTaskCount());
            break;
        case DELETE:
            heading = "Noted. I've removed this task:";
            end = String.format("\nNow you have %d tasks in the list.", this.taskList.getTaskCount());
            break;
        default:
            break;
        }

        return String.format("%s\n%s%s", heading, task.toString(), end);
    }

    String generateListMessage() {
        String heading = "Here are the tasks in your list:\n";
        return heading + this.taskList.toString();
    }

    String generateSortMessage() {
        String heading = "Here are the sorted tasks in your list:\n";
        return heading + this.taskList.toSortedString();
    }

    ArrayList<Task> filterTaskList(String message) {
        String[] messageSplit = message.split(" ");
        String messageToFilterBy = messageSplit[1];
        ArrayList<Task> filteredList = this.taskList.filter(messageToFilterBy);
        return filteredList;
    }

    public String filteredListToString(ArrayList<Task> filteredTaskList) {
        String listString = "";
        for (int i = 0; i < filteredTaskList.size(); i++) {
            Task task = filteredTaskList.get(i);
            listString += String.format("%d.%s\n", i + 1, task.toString());
        }
        return listString;
    }

    String generateFindMessage(String message) {
        String heading = "Here are the matching tasks in your list:\n";
        ArrayList<Task> filteredTaskList = filterTaskList(message);
        return heading + filteredListToString(filteredTaskList);
    }

    /**
     * Returns response message from Duke, in response to user's input.
     *
     * @param status  Status of message.
     * @param message Contents of message.
     * @return Duke message representing the response.
     * @throws InvalidDeadlineException If user input is invalid for deadline task.
     * @throws InvalidTodoException     If user input is invalid for todo task.
     * @throws InvalidEventException    If user input is invalid for event task.
     */
    public DukeMessage generate(MessageStatus status, String message)
            throws InvalidDeadlineException, InvalidTodoException, InvalidEventException {
        Task task;

        switch (status) {
        case LIST:
            message = generateListMessage();
            break;
        case SORT:
            message = generateSortMessage();
            break;
        case MARK:
            task = processMark(message);
            message = generateTaskMessage(status, task);
            break;
        case ADD:
            task = taskList.addTask(message);

            // Add task in storage
            this.storage.addTask(message);

            message = generateTaskMessage(status, task);
            break;
        case DELETE:
            task = taskList.deleteTask(message);

            // Delete task in storage
            this.storage.deleteTask(message);

            message = generateTaskMessage(status, task);
            break;
        case FIND:
            message = generateFindMessage(message);
            break;
        default:
            break;
        }
        return new DukeMessage(status, message);
    }
}
