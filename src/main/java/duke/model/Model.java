package duke.model;

import duke.command.Command;
import duke.exception.DukeRuntimeException;

public class Model {

    private TaskList taskList;

    public Model(TaskList taskList) {
        this.taskList = taskList;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public ExecutionResult execute(String input) {
        try {
            Command command = Parser.parseCommand(input);
            String msg = command.execute(taskList);
            boolean isExit = command.isExit();
            return new ExecutionResult(msg, isExit);
        } catch (DukeRuntimeException ex) {
            return new ExecutionResult(ex.getMessage(), false);
        }
    }
}
