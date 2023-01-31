package duke.functions;

import duke.dukeExceptions.DukeException;

public class DeleteTask extends Functions {
    /**
     * Constructor for an instance of Function.
     *
     * @param tl TaskList object that stores all defined tasks
     * @param st Storage object that controls writing and loading onto/from file
     */
    public DeleteTask(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define function of delete command
     *
     * @param inp Index of task specified
     * @throws DukeException
     */
    public void delete(String inp) {
        int index = Integer.parseInt(inp) - 1;
        if (index + 1 > this.tl.count() || index + 1 <= 0) {
            outputLayout.getChildren().add(getDialogLabel("Task index does not exists..."));
            return;
        }
        String h = "Noted. I've removed this task:\n";
        String des = tl.getTask(index).printStatus();
        tl.removeTask(index);
        String c = tl.printCount();
        outputLayout.getChildren().add(getDialogLabel(h + des + c));
        this.st.save(tl);
    }
}
