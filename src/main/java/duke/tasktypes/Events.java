package duke.tasktypes;

import duke.DukeExceptions;

/**
 * Class which represents an event that has a start and end period.
 */
public class Events extends Task {
    protected String fromDetails;
    protected String toDetails;

    /**
     * Constructor to initialize an event task.
     *
     * @param taskName String containing the task's name and due date.
     * @throws DukeExceptions if there is no start & end period being input.
     */
    public Events(String taskName) throws DukeExceptions {
        super(taskName.split("/from ")[0]);
        if (taskName.length() <= 0 || taskName.isBlank()) {
            throw new DukeExceptions("event");
        }
        String[] initialSplit = taskName.split("/from ");
        String[] nextSplit = initialSplit[1].split("/to ");
        this.fromDetails = nextSplit[0];
        this.toDetails = nextSplit[1];
    }

    /**
     * Function to print String representation of Event task.
     *
     * @return String representation of Event task.
     */
    @Override
    public String toString() {
        String toReturn = "";
        if (this.isDone) {
            toReturn = "[E][X]" + this.getName() + "(from: " + this.fromDetails + "to: " + this.toDetails + ")";
        } else {
            toReturn = "[E][ ]" + this.getName() + "(from: " + this.fromDetails + "to: " + this.toDetails + ")";
        }
        return toReturn;
    }
}
