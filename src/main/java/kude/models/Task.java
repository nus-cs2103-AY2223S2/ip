package kude.models;

import java.io.Serializable;
import java.util.Optional;

/**
 * Base class for all tasks
 */
public abstract class Task implements Serializable {
    private final String content;
    private final String place;
    private boolean isDone;

    /**
     * Initialize this task with the provided content
     */
    public Task(String content, Optional<String> place) {
        this.content = content;
        this.isDone = false;
        this.place = place.orElse(null);
    }

    /**
     * Gets the content of this task
     */
    public String getContent() {
        return content;
    }

    /**
     * Get whether this task is done
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets whether this task is done
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Optional<String> getPlace() {
        return Optional.ofNullable(place);
    }

    @Override
    public String toString() {
        var icon = isDone ? "X" : " ";
        var placeStr = getPlace().map(p -> String.format(" (@ %s)", p)).orElse("");
        return String.format("[%s] %s%s", icon, content, placeStr);
    }
}
