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
                if (descriptions.length != 2) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                }

                output = tasks.addTask(new ToDo(descriptions[1]));
                break;
            case "deadline":
                String[] deadlineDescription = descriptions[1].split("/by ");

                if (deadlineDescription.length != 2) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
                }

                output = tasks.addTask(new Deadline(deadlineDescription[0], deadlineDescription[1]));
                break;
            case "event":
                String[] eventDescription = descriptions[1].split("/from | /to ");

                if (eventDescription.length != 3) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.\n");
                }

                output = tasks.addTask(new Event(eventDescription[0], eventDescription[1], eventDescription[2]));
                break;
            case "delete":
                output = tasks.removeTask(Integer.parseInt(descriptions[1]) - 1);
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

