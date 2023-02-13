package duke.functions;

import duke.dukeexceptions.DukeException;
import duke.task.ToDo;

public class CreateTodo  {

    /**
     * Method to define function of todo command. Creates todo task
     *
     * @param inp Description of todo task
     */
    static public void todo(Functions fn, String inp) throws DukeException {
        if (inp.length() == 0) {
            throw new DukeException(fn.getOutputLayout(), "Inputs cannot be empty");
        }
        ToDo td = new ToDo(false, inp);
        String s = fn.getTl().addTask(td);
        fn.getOutputLayout().getChildren().add(fn.getDialogLabel(s));
        fn.getSt().save(fn.getTl());
    }

}
