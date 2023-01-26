package command;

import duke.DukeList;
import duke.TextBorder;

/**
 * Represents a Command that finds any listed task text with a given substring
 */
public class FindCommand extends Command{
    private String subString;
    private DukeList dukeList;

    /**
     * Creates a FindCommand with the given
     * @param subString the given subString to be checked
     * @param dukeList the DukeList from which the subString will be checked
     */
    public FindCommand(String subString, DukeList dukeList) {
        this.subString = subString;
        this.dukeList = dukeList;
    }

    /**
     * Tries to find the given subString in the tasks of the DukeList and prints them out
     */
    @Override
    public void execute() {
        DukeList foundList = this.dukeList.findSubString(subString);
        if (foundList.isEmpty()) {
            System.out.println("Sorry man, couldn't find anything with [" + subString + "]");
        } else {
            System.out.println(new TextBorder("So I've found these:"));
            System.out.println(foundList);
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
