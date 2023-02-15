package command;

import java.time.format.DateTimeParseException;

import duncan.DuncanList;

/**
 * Represents a Command that adds a given Task
 */
public class AddCommand extends Command {
    private String type;
    private String text;
    private DuncanList duncanList;

    /**
     * Creates a AddCommand with the given type, text and DuncanList
     * @param type the type of task to be added
     * @param text the text description of the task to be added
     * @param duncanList the DuncanList to which the task will be added
     */
    public AddCommand (String type, String text, DuncanList duncanList) {
        this.type = type;
        this.text = text;
        this.duncanList = duncanList;
    }

    /**
     * Tries to add the task to the DuncanList
     */
    @Override
    public void execute() {
        try {
            duncanList.add(this.type, this.text);
        } catch (DateTimeParseException e) {
            System.out.println("Hey, I can't see what date that is man.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
