package duke.functions;

import duke.task.Deadline;

public class CreateDeadline extends Functions {
    /**
     * Constructor for an instance of Function.
     *
     * @param fn Function object that defines the overall function of the Duke program
     */
    public CreateDeadline(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define function of deadline command. Create deadline task
     *
     * @param des Description of deadline task.
     * @param end Deadline of task.
     */
    public void deadline(String des, String end) {
        Deadline dl = new Deadline(false, des, end);
        String s = tl.addTask(dl);
        outputLayout.getChildren().add(getDialogLabel(s));
        this.st.save(tl);
    }
}
