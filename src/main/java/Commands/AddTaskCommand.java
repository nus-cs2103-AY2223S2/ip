package Commands;

import Exceptions.DukeException;
import Files.Storage;
import Parsers.TaskInfoParser;
import Tasks.TaskList;
import Tasks.Task;
import Ui.Ui;

public class AddTaskCommand extends Command {
    private String response;
    public AddTaskCommand(String response) {
        super();
        this.response = response;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = TaskInfoParser.parse(this.response);
            taskList.addTask(task);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
