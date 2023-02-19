package seedu.duke;

import java.io.Serializable;

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
