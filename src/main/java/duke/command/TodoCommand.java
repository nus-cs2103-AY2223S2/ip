package duke.command;

import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.event.Task;
import duke.event.Todo;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class TodoCommand extends Command {
    
    public TodoCommand(String[] contents) {
        super(contents,false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String userInput = super.parser.convertToUserInput(super.contents,TypeOfTask.todo,"");
        Task newTask = new Todo(userInput);
        taskList.addTask(newTask);
        ui.displayResult(TypeOfTask.todo, newTask, taskList);
    }
}
