package duke.functions;

import duke.task.Task;

public class MarkTask extends Functions {
    /**
     * Constructor for an instance of Function.
     *
     * @param fn Function object that defines the overall function of the Duke program
     */
    public MarkTask(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define function of unmark command
     *
     * @param inp Index of task specified
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
