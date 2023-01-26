package command;

import java.time.format.DateTimeParseException;

import duke.DukeList;

/**
 * Represents a Command that adds a given Task
 */
public class AddCommand extends Command {
    private String type;
    private String text;
    private DukeList dukeList;

    /**
     * Creates a AddCommand with the given type, text and DukeList
     * @param type the type of task to be added
     * @param text the text description of the task to be added
     * @param dukeList the DukeList to which the task will be added
     */
    public AddCommand (String type, String text, DukeList dukeList) {
        this.type = type;
        this.text = text;
        this.dukeList = dukeList;
    }

    /**
     * Tries to add the task to the DukeList
     */
    @Override
    public void execute() {
        try {
            dukeList.add(this.type, this.text);
        } catch (DateTimeParseException e) {
            System.out.println("Hey, I can't see what date that is man.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
