package duke.functions;

import duke.task.Deadline;

public class CreateDeadline{

    /**
     * Method to define function of deadline command. Create deadline task
     *
     * @param des Description of deadline task.
     * @param end Deadline of task.
     */
    static public void deadline(Functions fn, String des, String end) {
        Deadline dl = new Deadline(false, des, end);
        String s = fn.getTl().addTask(dl);
        fn.getOutputLayout().getChildren().add(fn.getDialogLabel(s));
        fn.getSt().save(fn.getTl());
    }
}
