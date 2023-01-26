package command;

import duke.DukeList;

/**
 * Represents a Command that marks or unmarks a task
 */
public class MarkCommand extends Command{

    private String task;
    private boolean marker = false;
    private DukeList dukeList;

    /**
     * Creates a MarkCommand with the given task, marker and DukeList
     * @param task the task to be marked
     * @param marker the boolean value that is true if the task is to be marked, false if the task is to be unmarked
     * @param dukeList the DukeList from which the task is to be marked
     */
    public MarkCommand(String task, boolean marker, DukeList dukeList) {
        this.task = task;
        this.marker = marker;
        this.dukeList = dukeList;
    }

    /**
     * Finds and marks the given task
     */
    @Override
    public void execute() {
        dukeList.findAndMark(task, marker);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
