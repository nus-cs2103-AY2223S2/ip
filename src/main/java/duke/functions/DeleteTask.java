package duke.functions;

public class DeleteTask extends Functions {
    /**
     * Constructor for an instance of Function.
     *
     * @param fn Function object that defines the overall function of the Duke program
     */
    public DeleteTask(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define function of delete command
     *
     * @param inp Index of task specified
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
