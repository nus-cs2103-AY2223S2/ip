package duke.functions;

import duke.task.Event;

public class CreateEvent extends Functions {

    /**
     * Constructor for an instance of Function.
     *
     * @param fn Function object that defines the overall function of the Duke program
     */
    public CreateEvent(Functions fn) {
        super(fn.getTl(), fn.getSt(), fn.getOutputLayout());
    }

    /**
     * Method to define function of event command. Create event task
     *
     * @param des   Description of the task
     * @param start Start time of the task
     * @param end   End time of the task
     */
    public void events(String des, String start, String end) {
        Event ev = new Event(false, des, start, end);
        String s = tl.addTask(ev);
        outputLayout.getChildren().add(getDialogLabel(s));
        this.st.save(tl);
    }
}
