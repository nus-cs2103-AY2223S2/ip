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

        switch (taskType) {
        case "list":
            output = tasks.listTasks();
            break;
        case "mark":
            output = tasks.markTask(tasks.getTask(Integer.parseInt(descriptions[1]) - 1));
            break;
        case "unmark":
            output = tasks.unmarkTask(tasks.getTask(Integer.parseInt(descriptions[1]) - 1));
            break;
        case "todo":
            try {
                if (descriptions.length != 2) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                }

                output = tasks.addTask(new ToDo(descriptions[1]));
            } catch (DukeException error) {
                output = Ui.errorMsg(error.getMessage());
            }
            break;
        case "deadline":
            try {
                String[] deadlineDescription = descriptions[1].split("/by ");

                if (deadlineDescription.length != 2) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
                }

                output = tasks.addTask(new Deadline(deadlineDescription[0], deadlineDescription[1]));
            } catch (DukeException error) {
                output = Ui.errorMsg(error.getMessage());
            }
            break;
        case "event":
            try {
                String[] eventDescription = descriptions[1].split("/from | /to ");

                if (eventDescription.length != 3) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.\n");
                }

                output = tasks.addTask(new Event(eventDescription[0], eventDescription[1], eventDescription[2]));
            } catch (DukeException e) {
                output = Ui.errorMsg(e.getMessage());
            }
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

        return output;
    }
}

