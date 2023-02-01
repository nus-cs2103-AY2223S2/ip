package duke;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public abstract class Task implements Serializable {
    @JsonProperty("description")
    protected String description;
    @JsonProperty("isDone")
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {}

    @JsonIgnore
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getFileRepresentation() {
        return this.isDone + "|" + this.description;
    };

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
