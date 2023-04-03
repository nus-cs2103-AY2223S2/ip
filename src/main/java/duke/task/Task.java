package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Task.
 */
public abstract class Task {

    /** Description of this Task */
    protected String desc;
    /** Status of this Task */
    protected boolean isMarked;
    protected String priority;
    protected List<String> tags;


    /**
     * A constructor to initialize a Task.
     *
     * @param desc The title of this Task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isMarked = false;
        this.priority = null;
        this.tags = new ArrayList<>();
    }


    /**
     * Marks this Task as complete.
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Unmarks this Task.
     */
    public void unMark() {
        isMarked = false;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    /**
     * Sets the priority of this task to the level.
     *
     * @param level The priority level of this task.
     */
    public void setPriority(String level) {
        if (level != null) {
            if (level.equals("high")) {
                this.priority = "high";
            } else if (level.equals("medium")) {
                this.priority = "medium";
            } else if (level.equals("low")) {
                this.priority = "low";
            }
        }
    }

    public String getPriority() {
        return this.priority;
    }

    public String getPrioritySign() {
        String sign;
        try {
            switch (priority) {
            case "high":
                sign = "* * *";
                break;
            case "medium":
                sign = "  * *";
                break;
            case "low":
                sign = "    *";
                break;
            default:
                sign = "     ";
            }
        } catch (NullPointerException e) {
            sign = "     ";
        }
        return sign;
    }

    public void setTag(String tagName) {
        this.tags.add(tagName);
    }

    public void setAllTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLatestTag() {
        return this.tags.get(tags.size() - 1);
    }

    public String tagsToString() {
        String text = "";
        for (int i = 0; i < tags.size(); i++) {
            text += "#" + tags.get(i);
            if (i != tags.size() - 1) {
                text += " ";
            }
        }
        return text;
    }

    public boolean doesTagExist(String tagName) {
        return tags.contains(tagName);
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[X] " + desc;
        } else {
            return "[ ] " + desc;
        }
    }

}
