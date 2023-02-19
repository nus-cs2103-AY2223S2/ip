package seedu.duke;

import java.io.Serializable;

/**
 * Represents a tag added to a task by a user
 */
public class Tag implements Serializable {
    private String tagName;

    Tag(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return tagName;
    }
}
