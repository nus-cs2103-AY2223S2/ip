package duke.command;
import java.time.LocalDate;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command class main logic of code
 */
public class Command {
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for command class
     */
    public Command() {
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Execute command based on the different actions
     * @param action
     * @param userInput
     * @param tasks
     * @throws DukeException
     */
    public String executeCommand(Parser.Action action, String userInput, TaskList tasks, Storage storage)
            throws DukeException {
        switch(action) {
        case LIST:
            return ui.getTaskListDetailsMessage(tasks);
        case FIND:
            TaskList filteredTaskList = tasks.findTask(this.parser.getKeyword(userInput));
            return ui.getFindTaskListMessage(filteredTaskList);
        case TODO:
            ToDo todo = new ToDo(this.parser.getTodoDescription(userInput));
            tasks.addTask(todo);
            storage.saveData(tasks);
            return ui.getAddTaskMessage(todo, tasks.getSize());
        case DEADLINE:
            Deadline deadline = new Deadline(this.parser.getDeadlineDescription(userInput),
                    this.parser.getDeadlineDate(userInput));
            tasks.addTask(deadline);
            storage.saveData(tasks);
            return ui.getAddTaskMessage(deadline, tasks.getSize());
        case EVENT:
            LocalDate[] eventDetails = this.parser.getEventDateDetails(userInput);
            Event event = new Event(this.parser.getEventDescription(userInput),
                    eventDetails[0], eventDetails[1]);
            tasks.addTask(event);
            storage.saveData(tasks);
            return ui.getAddTaskMessage(event, tasks.getSize());
        case MARK:
            Task markTask = tasks.getTask(this.parser.getTaskIndex(userInput));
            tasks.markTask(this.parser.getTaskIndex(userInput));
            storage.saveData(tasks);
            return ui.getMarkTaskMessage(markTask);
        case UNMARK:
            Task unmarkTask = tasks.getTask(this.parser.getTaskIndex(userInput));
            tasks.unmarkTask(this.parser.getTaskIndex(userInput));
            storage.saveData(tasks);
            return ui.getUnmarkTaskMessage(unmarkTask);
        case DELETE:
            Task deleteTask = tasks.getTask(parser.getTaskIndex(userInput));
            String response = ui.getRemoveTaskMessage(deleteTask);
            tasks.removeTask(this.parser.getTaskIndex(userInput));
            storage.saveData(tasks);
            response = response + ui.getTotalTasksMessage(tasks.getSize());
            return response;
        case BYE:
            return ui.getGoodByeMessage();
        case STATS:
            return ui.getStatsMessage(tasks);
        default:
            return "I'm sorry, but I don't know what that means :-(";
        }
    }
}
