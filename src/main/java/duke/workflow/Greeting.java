package duke.workflow;

import duke.util.TaskList;

/**
 * A more specific implementation of {@code Event}.
 *
 * Part of the workflow where the chatbot greets the user
 * and ask whether he/ she would like to begin using Duke.
 */

public class Greeting extends Event {
    private String status;

    /**
     * Constructs the {@code Greeting} event that greets the user.
     * The flag status is set to -1, indicating the user has
     * not made any input.
     */
    public Greeting() {
        super(false);
        this.status = "";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Determine the next event of the workflow based on the user's input.
     *
     * @return {@code Ending} if user doesn't want to play, and
     *          {@code DoTask} if the user wants to use Duke.
     */

    public Event toNextEvent() {
        if (this.status.equals("NOT PLAYING")) {
            return new Ending();
        } else {
            return new DoTask();
        }
    }
    public TaskList getTaskList() {
        return new TaskList();
    }

    @Override
    public Event toNextGui(String nextTask) {
        return this;
    }

    public String greet() {
        return "SHALL WE PLAY A GAME?";
    }
}
