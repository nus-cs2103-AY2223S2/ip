package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Creates a new parser.
 *
 * @author Evan Lee
 * @version CS2103 AY22/23 Semester 2
 */
public class Parser {
    /**
     * Creates task and stores it in Task arraylist.
     *
     * @param tasks List of tasks.
     * @param taskType Type of task.
     * @param descriptions The descriptions of the task.
     * @return Message containing task result.
     */
    protected static String parseInput(TaskList tasks, String taskType, String[] descriptions) {
        String output = "";

        try {
            switch (taskType) {
            case "list":
                output = tasks.listTasks();
                break;
            case "mark":
                int markTaskIndex = Integer.parseInt(descriptions[1]);

                if (markTaskIndex < 1 || markTaskIndex > tasks.getTaskList().size()) {
                    throw new DukeException("There is no item numbered " + markTaskIndex + ".\n");
                }

                output = tasks.markTask(tasks.getTask(markTaskIndex - 1));
                break;
            case "unmark":
                int unmarkTaskIndex = Integer.parseInt(descriptions[1]);

                if (unmarkTaskIndex < 1 || unmarkTaskIndex > tasks.getTaskList().size()) {
                    throw new DukeException("There is no item numbered " + unmarkTaskIndex + ".\n");
                }

                output = tasks.unmarkTask(tasks.getTask(unmarkTaskIndex - 1));
                break;
            case "todo":
                String todoTaskName = descriptions[1];

                if (descriptions.length != 2 || todoTaskName.equals("")) {
                    throw new DukeException("The description of a todo cannot be empty.\n");
                }

                output = tasks.addTask(new ToDo(todoTaskName));
                break;
            case "deadline":
                String[] deadlineDescription = descriptions[1].split("/by ");
                String deadlineTaskName = deadlineDescription[0];

                if (deadlineDescription.length != 2 || deadlineTaskName.equals("")) {
                    throw new DukeException("The description of a deadline cannot be empty.\n");
                }

                output = tasks.addTask(new Deadline(deadlineTaskName, deadlineDescription[1]));
                break;
            case "event":
                String[] eventDescription = descriptions[1].split("/from | /to ");
                String eventTaskName = eventDescription[0];

                if (eventDescription.length != 3 || eventTaskName.equals("")) {
                    throw new DukeException("The description of an event cannot be empty.\n");
                }

                output = tasks.addTask(new Event(eventTaskName, eventDescription[1], eventDescription[2]));
                break;
            case "delete":
                int deleteTaskIndex = Integer.parseInt(descriptions[1]);

                if (deleteTaskIndex < 1 || deleteTaskIndex > tasks.getTaskList().size()) {
                    throw new DukeException("There is no item numbered " + deleteTaskIndex + ".\n");
                }

                output = tasks.removeTask( deleteTaskIndex - 1);
                break;
            case "find":
                TaskList filteredTasks = new TaskList(tasks.filteredTaskList(descriptions[1]));
                output = filteredTasks.listTasks();
                break;
            default:
                output = Ui.unknownInputMsg();
                break;
            }
        } catch (DukeException e) {
            output = Ui.errorMsg(e.getMessage());
        }

        return output;
    }
}

