package duke.commands;

import duke.Ui;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.IncorrectCapitalisationException;
import duke.exceptions.IncorrectToDoException;
import duke.exceptions.NeroException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends Command {

    public ToDoCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public String executeCommand(String userInput) throws NeroException {
        int index = userInput.indexOf("todo");
        if (index == -1) {
            throw new IncorrectCapitalisationException();
        }
        try {
            String description = userInput.substring(index + 5);
            Task newTask = new ToDo(description);
            taskList.addTask(newTask);
            return ui.printAddedTasks(newTask.toString(), taskList.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyDescriptionException();
        }
    }
}
