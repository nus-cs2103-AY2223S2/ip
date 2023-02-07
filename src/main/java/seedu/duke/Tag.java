package seedu.duke;

public class Tag {
    private String tagName;

    Tag(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return tagName;
    }
}
