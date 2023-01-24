package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.taskType.Deadline;
import duke.taskType.TaskList;

public class CreateDeadline extends Command {
    private String cmdLine;

    public CreateDeadline(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    public void operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if(cmdLine.length()<=9) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Deadline task or not?");
            String task=cmdLine.substring(9);
            int pos=task.indexOf("/by");
            if(pos==-1) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a deadline for this task or not?");
            String time=task.substring(pos+4);
            task=task.substring(0,pos-1);
            if(task.isEmpty()) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Deadline task or not?");
            if(time.isEmpty()) throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a deadline for this task or not?");
            lst.add(new Deadline(task,time));
            System.out.println("New Deadline task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!");
            System.out.println("  "+lst.get(lst.size()-1).toString());
            System.out.println("You save "+lst.size()+" tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!");
            storage.save(lst);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Roarrrrrrrrrrrrrrrr! I cannot add this Deadline task! Check your input format!");
        }
    }
}
