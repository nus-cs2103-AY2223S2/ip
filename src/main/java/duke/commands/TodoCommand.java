package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.utils.DukeIo;

/**
 * TodoCommand adds a To-Do task to TaskList.
 */
public class TodoCommand extends Command {
    private final String tokens;

    /**
     * Public constructor for Todo Command.
     * @param desc Tokenised user inputs from user.
     */
    public TodoCommand(String desc) {
        this.tokens = desc;
    }

    /**
     * Method to add To-Do task into TaskList
     *
     * @param dukeIo UI class used to return results.
     * @param tasks TaskList containing all tasks to be shown
     * @return Success toast upon adding tasks to the TaskList on GUI.
     */
    @Override
    public String exec(DukeIo dukeIo, TaskList tasks) {
        ToDo todo = createToDo();
        tasks.addTask(todo);
        return dukeIo.notifySuccessAdd(todo) + dukeIo.showCount();
    }

    private ToDo createToDo() {
        return new ToDo(tokens);
    }
}
