package duke.util;

import java.util.Queue;

/**
 * Wrapper class for command functions to hold whitespace-delimited string input-output *and* side-effects
 * (e.g. modifications to execution flow)
 *
 * @see State
 */
public class Stateful {
    private final Queue<String> outputs;
    private final State state;

    /**
     * Creates a new Stateful.
     *
     * @param outputs Lines of string of outputs
     * @param state  (Modified) State of program
     */
    public Stateful(Queue<String> outputs, State state) {
        this.outputs = outputs;
        this.state = state;
    }

    /**
     * Returns a new Stateful with the given changes. Encapsulates the advancement of the program state.
     *
     * @param output    The output to be added to the output queue.
     * @param state     The new state of the program.
     * @return          A new Stateful with the given changes.
     */
    public Stateful next(String output, State state) {
        this.outputs.add(output);
        return new Stateful(this.outputs, state);
    }

    public Stateful next(Queue<String> outputs, State state) {
        this.outputs.addAll(outputs);
        return new Stateful(this.outputs, state);
    }

    public Stateful next(String output) {
        return next(output, this.state);
    }

    public Stateful next(Queue<String> outputs) {
        return next(outputs, this.state);
    }

    public Queue<String> getOutputs() {
        return outputs;
    }

    public State getState() {
        return state;
    }
}