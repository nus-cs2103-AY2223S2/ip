package duke.message;

import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidTodoException;
import duke.exception.InvalidEventException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;


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

    public DukeMessage generate(MessageStatus status, String message)
            throws InvalidDeadlineException, InvalidTodoException, InvalidEventException {
        Task task;

        switch (status) {
        case LIST:
            message = generateListMessage();
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
        default:
            break;
        }
        return new DukeMessage(status, message);
    }
}
