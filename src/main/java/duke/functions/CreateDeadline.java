package duke.functions;

import duke.task.Deadline;
import duke.dukeExceptions.DukeException;

public class CreateDeadline extends Functions{
    /**
     * Constructor for an instance of Function.
     *
     * @param tl TaskList object that stores all defined tasks
     * @param st Storage object that controls writing and loading onto/from file
     */
    public CreateDeadline(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define function of deadline command. Create deadline task
     *
     * @param inp Description of deadline task. Define deadline after "/by".
     *            Example: deadline task1 /by 2023-12-12 12:12
     * @throws DukeException
     */
    public void deadline(String des, String end) {
        Deadline dl = new Deadline(false, des, end);
        String s = tl.addTask(dl);
        outputLayout.getChildren().add(getDialogLabel(s));
        this.st.save(tl);
    }
}
