package duke.tag;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A class that stores all the tags that are used in a
 * TaskList.
 */
public class TagList {
    private Set<Tag> tags;

    /**
     * Constructor for TagList.
     */
    public TagList() {
        this.tags = new HashSet<>();
    }

    @Override
    public String toString() {
        if (tags.isEmpty()) {
            return "There are no tags!";
        } else {
            Iterator<Tag> it = tags.iterator();
            String printedTagList = "Here are the tags you have created:\n";
            while (it.hasNext()) {
                printedTagList += String.format("\t%s\n", it.next());
            }
            return printedTagList;
        }
    }
}
