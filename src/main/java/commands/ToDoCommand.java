package commands;

import exceptions.EmptyTaskException;
import storage.Storage;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import java.io.IOException;

public class ToDoCommand extends Command {
    public ToDoCommand(String string) {
        super(string);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            if (super.getCommand().equals("")) {
                throw new EmptyTaskException("The task cant be empty");
            }
            ToDo todo = new ToDo(super.getCommand());
            tasks.add(todo);
            storage.todo(super.getCommand());
            ui.addTaskMsg();
            ui.printTask(todo);
            ui.printListSize(tasks);
        } catch (Exception e) {
            ui.taskErrorMsg();
        }
    }
}
