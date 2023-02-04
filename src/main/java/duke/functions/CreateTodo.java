package duke.functions;

import duke.task.ToDo;

public class CreateTodo extends Functions {
    /**
     * Constructor for an instance of Function.
     *
     * @param fn Function object that defines the overall function of the Duke program
     */
    public CreateTodo(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define function of todo command. Creates todo task
     *
     * @param inp Description of todo task
     */
    public void todo(String inp) {
        ToDo td = new ToDo(false, inp);
        String s = tl.addTask(td);
        outputLayout.getChildren().add(getDialogLabel(s));
        this.st.save(tl);
    }

}
