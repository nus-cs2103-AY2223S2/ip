package duke.command;

import duke.task.Event;
import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;

<<<<<<< .merge_file_NwdzAA
/**
 * Event command with event and from and to timing.
 */
=======
>>>>>>> .merge_file_A30Ew7
public class EventCommand extends Command {
    private String message;
    private String from;
    private String to;

    public EventCommand(String input) {
<<<<<<< .merge_file_NwdzAA
        String[] checkerslash = input.split("/");
        String[] checkerevent = checkerslash[0].split("event ");
        String[] checker4 = checkerslash[1].split("from ");
        String[] checker5 = checkerslash[2].split("to ");
        this.message = checkerevent[1];
        this.from = checker4[1];
        this.to = checker5[1];
=======
        String[] checkerSlash = input.split("/");
        String[] checkerEvent = checkerSlash[0].split("event ");
        String[] checkerFrom = checkerSlash[1].split("from ");
        String[] checkerTo = checkerSlash[2].split("to ");
        this.message = checkerEvent[1];
        this.from = checkerFrom[1];
        this.to = checkerTo[1];
>>>>>>> .merge_file_A30Ew7
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        Event t = new Event(message, from, to);
        tasks.addToList(t);
        System.out.println("Got it, I've added this task:");
        System.out.println(t);
        tasks.statement();
        return true;
    }
}