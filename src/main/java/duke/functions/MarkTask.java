package duke.functions;

import duke.dukeExceptions.DukeException;
import duke.task.Task;

public class MarkTask extends Functions {
    /**
     * Constructor for an instance of Function.
     *
     * @param tl TaskList object that stores all defined tasks
     * @param st Storage object that controls writing and loading onto/from file
     */
    public MarkTask(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define function of unmark command
     *
     * @param inp Index of task specified
     * @throws DukeException
     */
    public void mark(String inp, boolean flag) {
        int index = Integer.parseInt(inp) - 1;
        if (index + 1 > this.tl.count() || index + 1 <= 0) {
            outputLayout.getChildren().add(getDialogLabel("Task index does not exists..."));
            return;
        }
        Task t = tl.getTask(index);
        t.setStatus(flag);
        String h = flag ? "Nice! I've marked this task as done:\n" : "OK, I've marked this task as not done yet:\n";
        outputLayout.getChildren().add(getDialogLabel(h + t.printStatus()));
        this.st.save(tl);
    }

}
