package duke.workflow;

import duke.util.TaskList;

/**
 * A more specific implementation of {@code Event}.
 *
 * Part of the workflow where the chatbot greets the user
 * and ask whether he/ she would like to begin using Duke.
 */

public class Greeting extends Event {
    private int status;

    /**
     * Constructs the {@code Greeting} event that greets the user.
     * The flag status is set to -1, indicating the user has
     * not made any input.
     */
    public Greeting() {
        super(false);
        this.status = -1;
    }

    /**
     * Constructs the {@code Greeting} event that greets the user. The
     * flag status inPlay is decided by the user
     *
     * @param inPlay the flag status to decided whether the program
     *               will end or will continue to run. 0 if the user
     *               doesn't want to use Duke, and 1 if he/ she does.
     */

    public Greeting(int inPlay) {
        super(false);
        this.status = inPlay;
    }

    /**
     * Determine the next event of the workflow based on the user's input.
     *
     * @return {@code Ending} if user doesn't want to play, and
     *          {@code DoTask} if the user wants to use Duke.
     */

    public Event toNext() {
        if (this.status == 0) {
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

    @Override
    public String toString() {
        return "SHALL WE PLAY A GAME?";
    }
}
