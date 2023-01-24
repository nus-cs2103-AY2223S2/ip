package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.taskType.TaskList;

public class Delete extends Command {
    private String cmdLine;

    public Delete(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    public void operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if(cmdLine.length()<=7) throw new DukeException("Roarrrrrrrrrrrrrrrrrrr! Do you want to delete any task or not?");
            int i = Integer.parseInt(cmdLine.substring(7));
            System.out.println("Fine! This task is deleted. Roarrrrrrrrrrrrrr!");
            System.out.println("  "+lst.get(i-1).toString());
            lst.remove(i-1);
            System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
            storage.save(lst);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Roarrrrrrrrrrrrrrrrrrrr! I cannot identify that task as it is not an integer!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Roarrrrrrrrrrrrrrrrrrrr! You did not add that many tasks in the list!");
        }
    }
}
