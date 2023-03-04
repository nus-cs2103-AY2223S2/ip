package duke.action;
import java.io.Serializable;

import duke.data.TypeOfTask;
import duke.parser.Parser;

/**
 * Task class that tracks the description, completion status and type of task.
 * This tasks has multiple subclasses: Deadline, Event and Todo
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected TypeOfTask type;

    protected Parser parser;

    /**
     * Default constructor that takes in the description and type of task
     *
     * @param description Description of the task
     * @param type Type of task
     */
    public Task(String description, TypeOfTask type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.parser = new Parser();
    }

    /**
     * Returns the status of task with either X or " ".
     *
     * @return status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task
     *
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the type of task in character format
     *
     * @return character format of task
     */
    public String getTypeOfTask() {
        switch(this.type) {
        case deadline:
            return "D";
        case event:
            return "E";
        case todo:
            return "T";
        default:
            return null;
        }
    }

    /**
     * Returns the parser stored
     *
     * @return parser
     */
    public Parser getParser() {
        return this.parser;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

}
