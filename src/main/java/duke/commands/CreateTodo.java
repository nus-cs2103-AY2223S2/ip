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

    public void operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if(cmdLine.length()<=5) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Todo task or not?");
            String task=cmdLine.substring(5);
            lst.add(new Todo(task));
            System.out.println("New Todo task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
            System.out.println("  "+lst.get(lst.size()-1).toString());
            System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
            storage.save(lst);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
