package duke.functions;

public class ListTask extends Functions {
    /**
     * Constructor for an instance of Function.
     *
     * @param fn Function object that defines the overall function of the Duke program
     */
    public ListTask(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define function of list command
     */
    public void list() {
        String h = "Here are the tasks in your list:\n";
        String l = "";
        for (int i = 0; i < tl.count(); i++) {
            String s = i + 1 + "." + tl.getTask(i).printStatus();
            l += s;
        }
        outputLayout.getChildren().add(getDialogLabel(h + l));
    }
}
