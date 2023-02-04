package duke.functions;

public class DeleteTask  {

    /**
     * Method to define function of delete command
     *
     * @param inp Index of task specified
     */
    static public void delete(Functions fn, String inp) {
        int index = Integer.parseInt(inp) - 1;
        if (index + 1 > fn.getTl().count() || index + 1 <= 0) {
            fn.getOutputLayout().getChildren().add(fn.getDialogLabel("Task index does not exists..."));
            return;
        }
        String h = "Noted. I've removed this task:\n";
        String des = fn.getTl().getTask(index).printStatus();
        fn.getTl().removeTask(index);
        String c = fn.getTl().printCount();
        fn.getOutputLayout().getChildren().add(fn.getDialogLabel(h + des + c));
        fn.getSt().save(fn.getTl());
    }
}
