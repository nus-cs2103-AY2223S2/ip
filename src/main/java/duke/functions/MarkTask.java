package duke.functions;

import duke.task.Task;

public class MarkTask {
    /**
     * Method to define function of unmark command
     *
     * @param inp Index of task specified
     */
    static public void mark(Functions fn, String inp, boolean flag) {
        int index = Integer.parseInt(inp) - 1;
        if (index + 1 > fn.getTl().count() || index + 1 <= 0) {
            fn.getOutputLayout().getChildren().add(fn.getDialogLabel("Task index does not exists..."));
            return;
        }
        Task t = fn.getTl().getTask(index);
        t.setMark(flag);
        String h = flag ? "Nice! I've marked this task as done:\n" : "OK, I've marked this task as not done yet:\n";
        fn.getOutputLayout().getChildren().add(fn.getDialogLabel(h + t.printStatus()));
        fn.getSt().save(fn.getTl());
    }

}
