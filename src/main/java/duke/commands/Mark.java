package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.taskType.TaskList;

public class Mark extends Command {
    private String cmdLine;

    public Mark(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    public void operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if(cmdLine.length()<=5) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you want to mark any task or not?");
            int i = Integer.parseInt(cmdLine.substring(5));
            lst.get(i-1).mark();
            System.out.println("Good! You finished that! I marked that as done. Roarrrrrrrrrrrrrr!");
            System.out.println("  "+lst.get(i-1).toString());
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
