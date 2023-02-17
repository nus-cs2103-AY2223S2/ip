package kude.processor;

/**
 * Base interface for all commands
 */
public interface Command {
    /**
     * Run the command
     * @param ctx Context for running this command
     */
    void run(Context ctx);
}
