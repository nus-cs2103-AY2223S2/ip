package duke.tag;

/**
 * A class that allows users to attach their task to a
 * tag
 */
public class Tag {
    private final String description;

    /**
     * Constructor for Tag.
     *
     * @param description Name or description of Tag.
     */
    public Tag(String description) {
        this.description = description;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if(obj instanceof Tag) {
            Tag t = (Tag) obj;
            return this.description.equals(t.description);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
