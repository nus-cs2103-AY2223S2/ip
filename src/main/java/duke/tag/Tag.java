package duke.tag;

/**
 * A class that allows users to attach their task to a
 * tag
 */
public class Tag {
    private final String name;

    /**
     * Constructor for Tag.
     *
     * @param name Name or name of Tag.
     */
    public Tag(String name) {
        this.name = name;

    }

    public String getTagName() {
        return name;
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
            return this.name.equals(t.name);
        } else {
            return false;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return name;
    }
}
