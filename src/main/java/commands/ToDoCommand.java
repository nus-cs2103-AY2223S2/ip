package commands;

import java.io.IOException;

import exceptions.EmptyTaskException;
import storage.Storage;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

/**
 * Represents command for adding a ToDO
 */
public class ToDoCommand extends Command {
    public ToDoCommand(String string) {
        super(string);
    }

    /**
     * Execute adding a ToDo command
     * @param tasks the current list of tasks
     * @param ui the user interface
     * @param storage the storage where the changes done by command action stored
     * @throws IOException
     */
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

    /**
     * @param tasks
     * @param ui
     * @param storage
     * @param idx index of task
     */
    public void replace(TaskList tasks, Ui ui, Storage storage, int idx) {
        try {
            if (super.getCommand().equals("")) {
                throw new EmptyTaskException("The task cant be empty");
            }
            ToDo todo = new ToDo(super.getCommand());
            tasks.set(idx, todo);
            storage.replaceToDo(idx, super.getCommand());
        } catch (Exception e) {
            ui.todoErrMsg();
        }
    }

    /**
     * generate the action to the save file
     * @param tasks
     * @param ui
     * @param storage
     * @return the string line that will be concatenated to save file
     */
    public String generate(TaskList tasks, Ui ui, Storage storage) {
        ToDo todo = new ToDo(super.getCommand());
        return ui.printAddTask() + todo.toString();
    }
}
