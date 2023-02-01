package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.taskType.TaskList;
import duke.taskType.Todo;

public class CreateTodo extends Command {
    private String cmdLine;

    public CreateTodo(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    public String operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if (cmdLine.length() <= 5) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Todo task or not?");
            String task = cmdLine.substring(5);
            lst.add(new Todo(task));
            String response = "";
            response += "New Todo task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!\n";
            response += "  " + lst.get(lst.size() - 1).toString() + "\n";
            response += "You save " + lst.size() + " tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!";
            storage.save(lst);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
