package duke.workflow;

/**
 * A more specific implementation of {@code Event}.
 *
 * Part of the workflow where the chatbot say goodbye to
 * the user and the program ends.
 */

public class Ending extends Event {

    /**
     * Constructs the {@code Ending} event
     */

    public Ending() {
        super(true);
    }
    public Event toNext() {
        return this;
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        toPrintOut += "_".repeat(22) + '\n';
        toPrintOut += "VERY WELL. THE WORLD IS SAFE FROM YOUR PLAN." + '\n';
        toPrintOut += "_".repeat(22) + '\n';
        return toPrintOut;
    }
}