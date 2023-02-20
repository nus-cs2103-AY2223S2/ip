package duke.tag;

/**
 * {@code Tag} class that stores tag for any task
 */
public class Tag {

    /**
     * Name of tag
     */
    protected String tagName;

    /**
     * Constructor method for Tag
     * @param tagName name of tag
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Generates string representation of tag
     * @return string representation of tag
     */
    public String toString() {
        String tagString = "#" + this.tagName;
        return tagString;
    }

    /**
     * Compares two objects. If object is a tag, compares name of tags
     * @param o Object that is compared with tag
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Tag) {
            Tag cmpTag = (Tag) o;
            return this.tagName.equalsIgnoreCase(cmpTag.tagName);
        } else {
            return false;
        }
    }
}
