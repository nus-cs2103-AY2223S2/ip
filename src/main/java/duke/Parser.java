package duke;

import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

public class Parser {
    /** 
     * Creates task and stores it in Task arraylist.
     * 
     * @param tasks List of tasks.
     * @param taskType Type of task.
     * @param description The descriptions of the task.
     */
    protected static void parseInput(TaskList tasks, String taskType, String[] descriptions) {
        switch (taskType) {
        case "list":
            tasks.listTasks();
            break;
        case "mark":
            tasks.markTask(tasks.getTask(Integer.parseInt(descriptions[1]) - 1));
            break;
        case "unmark":
            tasks.unmarkTask(tasks.getTask(Integer.parseInt(descriptions[1]) - 1));
            break;
        case "todo":
            try {
                if (descriptions.length != 2) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                }

                tasks.addTask(new Todo(descriptions[1]));
            } catch (DukeException error) {
                Ui.errorMsg(error.getMessage());
            }
            break;
        case "deadline":
            try {
                String[] deadlineDescription = descriptions[1].split("/by ");

                if (deadlineDescription.length != 2) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
                }

                tasks.addTask(new Deadline(deadlineDescription[0], deadlineDescription[1]));
            } catch (DukeException error) {
                Ui.errorMsg(error.getMessage());
            }
            break;
        case "event":
            try {
                String[] eventDescription = descriptions[1].split("/from | /to ");
                if (eventDescription.length != 3) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.\n");
                }

                tasks.addTask(new Event(eventDescription[0], eventDescription[1], eventDescription[2]));
            } catch (DukeException e) {
                Ui.errorMsg(e.getMessage());
            }
            break;
        case "delete":
            tasks.removeTask(Integer.parseInt(descriptions[1]) - 1);
            break;
        default:
            Ui.unknownInputMsg();
            break;
        }
    }
}