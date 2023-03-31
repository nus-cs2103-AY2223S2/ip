package duke.command;

import duke.PriorityLevel;
import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidIndexException;

public class PriorityCommand extends Command {
    private final PriorityLevel priority;
    private final int idx;

    public PriorityCommand(PriorityLevel priority, int idx) {
        this.priority = priority;
        this.idx = idx - 1;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            tasks.get(this.idx).setPriority(this.priority);
            switch (this.priority) {
                case LOW:
                    this.setOutput("Changed this task's priority ne~",
                            tasks.get(this.idx).toString(),
                            "Take your time~");
                    break;
                case MEDIUM:
                    this.setOutput("Changed this task's priority ne~",
                            tasks.get(this.idx).toString(),
                            "Don't forget to do it yo~");
                    break;
                case HIGH:
                    this.setOutput("Changed this task's priority ne~",
                            tasks.get(this.idx).toString(),
                            "It's super urgent da yo!!!");
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }

        storage.saveTasklistToFile(tasks);
    }
}
