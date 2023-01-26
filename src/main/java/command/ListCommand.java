package command;

import duke.DukeList;
import duke.TextBorder;

/**
 * Represents a Command that list stored tasks
 */
public class ListCommand extends Command{
    private DukeList dukeList;

    /**
     * Creates a ListCommand with the given DukeList
     * @param dukeList the DukeList to be listed
     */
    public ListCommand(DukeList dukeList) {
        this.dukeList = dukeList;
    }

    /**
     * Prints out all tasks in the current DukeList
     */
    @Override
    public void execute() {
        if (this.dukeList.isEmpty()) {
            System.out.println(new TextBorder("Yo there's nothing in the list."));
        } else {
            System.out.println(new TextBorder(this.dukeList.toString()));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
