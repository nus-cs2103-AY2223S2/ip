package duke;

import java.util.HashMap;

/**
 * {@code TagLibrary} class encapsulates a list of
 * all tags added
 */
public class TagLibrary {
    /**
     * Map of all tags added and number of tasks with that tag
     */
    protected HashMap<Tag, Integer> tags;

    /**
     * Constructor Method that creates an empty HashMap
     */
    public TagLibrary(){
        this.tags = new HashMap<>();
    }

    /**
     * Adds tag into map if the tag is not in the map.
     * Otherwise, increments the number of tasks with that tag.
     * @param tag tag to be added or have its count incremented
     */
    public void appendTag(Tag tag) {
        if (this.tags.containsKey(tag)) {
            int oldCount = this.tags.get(tag);
            this.tags.put(tag, oldCount + 1);
        } else {
            this.tags.put(tag, 1);
        }
    }


    /**
     * Decreases number of tasks with a tag when task is removed
     * from Tasklist. If no tasks have that tag, it is removed from
     * tag library
     * @param tag tag to be removed or have its count decremented
     */
    public void decrementCount(Tag tag){
        int currentCount = this.tags.get(tag);

        currentCount --;

        if (currentCount <= 0) {
            this.tags.remove(tag);
        }
    }
}
