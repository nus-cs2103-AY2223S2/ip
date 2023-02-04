package duke.functions;

import duke.task.ToDo;

public class CreateTodo  {

    /**
     * Method to define function of todo command. Creates todo task
     *
     * @param inp Description of todo task
     */
    static public void todo(Functions fn, String inp) {
        ToDo td = new ToDo(false, inp);
        String s = fn.getTl().addTask(td);
        fn.getOutputLayout().getChildren().add(fn.getDialogLabel(s));
        fn.getSt().save(fn.getTl());
    }

}
