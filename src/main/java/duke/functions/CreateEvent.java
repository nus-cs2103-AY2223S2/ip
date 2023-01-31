package duke.functions;

import duke.task.Event;

public class CreateEvent extends Functions{

    /**
     * Constructor for an instance of Function.
     *
     * @param tl TaskList object that stores all defined tasks
     * @param st Storage object that controls writing and loading onto/from file
     */
    public CreateEvent(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define function of event command. Create event task
     *
     * @param inp Description of event task. Define event with "/from ... /to ...".
     *            Example: deadline task1 /from 12/12/2023 12:12 /to 12/12/2023 23:59
     */
    public void events(String des, String start, String end) {
        Event ev = new Event(false, des, start, end);
        String s = tl.addTask(ev);
        outputLayout.getChildren().add(getDialogLabel(s));
        this.st.save(tl);
    }
}
