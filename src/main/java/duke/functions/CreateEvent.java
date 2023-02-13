package duke.functions;

import duke.task.Event;

import java.time.LocalDateTime;

public class CreateEvent {

    /**
     * Method to define function of event command. Create event task
     *
     * @param des   Description of the task
     * @param start Start time of the task
     * @param end   End time of the task
     */
    static public void events(Functions fn, String des, LocalDateTime start, LocalDateTime end) {
        Event ev = new Event(false, des, start, end);
        String s = fn.getTl().addTask(ev);
        fn.getOutputLayout().getChildren().clear();
        fn.getOutputLayout().getChildren().add(fn.getDialogLabel(s));
        fn.getSt().save(fn.getTl());
    }
}
