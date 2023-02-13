package duke.functions;

import duke.dukeexceptions.DukeException;

public class DeleteTask  {

    /**
     * Method to define function of delete command
     *
     * @param inp Index of task specified
     */
    static public void delete(Functions fn, String inp) throws DukeException {
        try {
            int index = Integer.parseInt(inp) - 1;
            if (index + 1 > fn.getTl().count() || index + 1 <= 0) {
                fn.getOutputLayout().getChildren().add(fn.getDialogLabel("Task index does not exists..."));
                return;
            }
            String h = "Noted. I've removed this task:\n";
            String des = fn.getTl().getTask(index).printStatus();
            fn.getTl().removeTask(index);
            String c = fn.getTl().printCount();
            fn.getOutputLayout().getChildren().clear();
            fn.getOutputLayout().getChildren().add(fn.getDialogLabel(h + des + c));
            fn.getSt().save(fn.getTl());
        } catch (NumberFormatException e) {
            throw new DukeException(fn.getOutputLayout(), "Nothing is selected");
        }
    }
}
