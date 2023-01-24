package commands;

import exceptions.DukeException;
import files.Storage;
import parsers.TaskInfoParser;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

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
