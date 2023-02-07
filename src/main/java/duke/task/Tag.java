package duke.task;

/**
 * Class for the tag object, to allow tagging of tasks.
 */
public class Tag {
    private String tagName = "";

    /**
     * Constructor for tag.
     *
     * @param tagDescription Tag Description.
     */
    public Tag(String tagDescription) {
        this.tagName = tagDescription;
    }

    /**
     * Converts Tag object to string output.
     *
     * @return String output of tag object.
     */
    @Override
    public String toString() {
        if (this.tagName.equals("")) {
            return this.tagName;
        } else {
            return "#" + this.tagName;
        }
    }

    /**
     * Sets the tag description to the tag object.
     *
     * @param tagDescription Tag Description.
     */
    public void putTag(String tagDescription) {
        this.tagName = tagDescription;
    }
}
